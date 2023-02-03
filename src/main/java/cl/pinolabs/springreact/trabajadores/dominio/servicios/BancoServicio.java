package cl.pinolabs.springreact.trabajadores.dominio.servicios;

import cl.pinolabs.springreact.trabajadores.dominio.dto.BancoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.BancoDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoServicio {
    private final BancoDTORepository repo;

    public BancoServicio(BancoDTORepository repo) {
        this.repo = repo;
    }

    public Optional<List<BancoDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<BancoDTO> findById(int id){
        return repo.findById(id);
    }
    public BancoDTO save(BancoDTO bancoDTO){
        return repo.save(bancoDTO);
    }
    public boolean delete(int id){
        return repo.findById(id)
                .map(bancoDTO -> {
                    repo.delete(id);
                    return true;})
                .orElse(false);
    }
}
