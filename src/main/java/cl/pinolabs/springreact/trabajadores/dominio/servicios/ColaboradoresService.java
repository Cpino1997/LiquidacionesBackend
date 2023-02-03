package cl.pinolabs.springreact.trabajadores.dominio.servicios;

import cl.pinolabs.springreact.trabajadores.dominio.dto.ColaboradoresDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.ColaboradoresDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradoresService {
    private final ColaboradoresDTORepository repo;

    public ColaboradoresService(ColaboradoresDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<ColaboradoresDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<ColaboradoresDTO> findById(int id){
        return repo.findById(id);
    }
    public ColaboradoresDTO save(ColaboradoresDTO colaboradoresDTO){
        return repo.save(colaboradoresDTO);
    }
    public boolean delete(int id){
        return repo.findById(id)
                .map(colaboradoresDTO -> {
                    repo.delete(id);
                    return true;})
                .orElse(false);
    }
}
