package cl.pinolabs.springreact.security.modelos.dominio.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import cl.pinolabs.springreact.security.modelos.percistencia.crud.RefreshTokenRepository;
import cl.pinolabs.springreact.security.modelos.percistencia.crud.UserCrud;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.RefreshToken;
import cl.pinolabs.springreact.security.config.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RefreshTokenService {
    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;


    private final RefreshTokenRepository refreshTokenRepository;


    private final UserCrud userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserCrud userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}