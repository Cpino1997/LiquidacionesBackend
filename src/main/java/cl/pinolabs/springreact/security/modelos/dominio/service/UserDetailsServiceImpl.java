package cl.pinolabs.springreact.security.modelos.dominio.service;

import cl.pinolabs.springreact.security.modelos.percistencia.crud.UserCrud;
import cl.pinolabs.springreact.security.modelos.percistencia.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserCrud userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario: " + username +" No Existe!"));

        return UserDetailsImpl.build(user);
    }

}