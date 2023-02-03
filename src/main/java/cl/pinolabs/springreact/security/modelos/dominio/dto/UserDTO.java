package cl.pinolabs.springreact.security.modelos.dominio.dto;

import org.springframework.http.ResponseCookie;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String email;

    private String token;
    private final List<String> roles;

    public UserDTO(Long id, String username, String email, List<String> roles, ResponseCookie jwtCookie) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.token = String.valueOf(jwtCookie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}