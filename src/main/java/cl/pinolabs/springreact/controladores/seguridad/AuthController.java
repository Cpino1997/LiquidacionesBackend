package cl.pinolabs.springreact.controladores.seguridad;

import cl.pinolabs.springreact.security.modelos.dominio.dto.LoginDTO;
import cl.pinolabs.springreact.security.modelos.dominio.dto.MessageResponse;
import cl.pinolabs.springreact.security.modelos.dominio.dto.RegistroDTO;
import cl.pinolabs.springreact.security.modelos.dominio.dto.UserDTO;
import cl.pinolabs.springreact.security.modelos.dominio.service.RefreshTokenService;
import cl.pinolabs.springreact.security.modelos.dominio.service.UserDetailsImpl;
import cl.pinolabs.springreact.security.modelos.percistencia.crud.RoleCrud;
import cl.pinolabs.springreact.security.modelos.percistencia.crud.UserCrud;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.ERole;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.RefreshToken;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.Role;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.User;
import cl.pinolabs.springreact.security.config.TokenRefreshException;
import cl.pinolabs.springreact.security.config.jwt.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@Order(1)
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserCrud userRepository;
    private final RoleCrud roleCrud;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserCrud userRepository, RoleCrud roleCrud, PasswordEncoder encoder, JwtUtils jwtUtils, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleCrud = roleCrud;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    @Order(1)
    @Operation(summary = "Login", description = "Login")
    public ResponseEntity<UserDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            String authority = grantedAuthority.getAuthority();
            roles.add(authority);
        }

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserDTO(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/registro")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegistroDTO signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: El nombre de usuario se encuentra registrado!"));
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: El email ingresado es invalido!!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        String error = "Error: Role is not found.";
        if (strRoles == null) {
            Role userRole = roleCrud.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(error));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleCrud.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(error));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleCrud.findByName(ERole.ROLE_MOD)
                                .orElseThrow(() -> new RuntimeException(error));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleCrud.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(error));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario Registrado con Exito!"));
    }

    @PostMapping("/salir")
    public ResponseEntity<MessageResponse> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(principle.toString(), "anonymousUser")) {
            Long userId = ((UserDetailsImpl) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }

        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("Has salido con exito de la app!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<MessageResponse> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("El refrezco esta listo!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "El refrezco ingresado no esta en nuestra bd!"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("No tienes refrezcos, no puedes ingresar aqui!!"));
    }
}