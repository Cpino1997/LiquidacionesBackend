package cl.pinolabs.springreact.trabajadores.dominio.servicios;

import cl.pinolabs.springreact.trabajadores.dominio.dto.AFPDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.AFPDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AFPServicio {
    private final AFPDTORepository repo;

    public AFPServicio(AFPDTORepository repo) {
        this.repo = repo;
    }

    public Optional<List<AFPDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<AFPDTO> findById(int id){
        return repo.findById(id);
    }
    public AFPDTO save(AFPDTO afpDTO){
        return repo.save(afpDTO);
    }
    public boolean delete(int id){
        return repo.findById(id)
                .map(afpdto -> {
                    repo.delete(id);
                    return true;})
                .orElse(false);
    }
    public boolean exitsByNombre(String nombre){
        return repo.exitsByNombre(nombre);
    }
}
