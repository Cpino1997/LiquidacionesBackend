package cl.pinolabs.springreact.security.modelos.dominio.service;

import cl.pinolabs.springreact.security.modelos.dominio.dto.UserMinimalDTO;
import cl.pinolabs.springreact.security.modelos.dominio.repository.UserDTORepository;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDTOService {
    private UserDTORepository repo;

    public UserDTOService(UserDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<UserMinimalDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<UserMinimalDTO> findByUsername(String username){
        return repo.findByUsername(username);
    }
}
