package cl.pinolabs.springreact.trabajadores.dominio.servicios;

import cl.pinolabs.springreact.trabajadores.dominio.dto.TipoCuentaDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.TipoCtaDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCtaService {
    private final TipoCtaDTORepository repo;
    public TipoCtaService(TipoCtaDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<TipoCuentaDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<TipoCuentaDTO> findById(int id){
        return repo.findById(id);
    }
    public TipoCuentaDTO save(TipoCuentaDTO tipoCtaDTO){
        return repo.save(tipoCtaDTO);
    }
    public boolean delete(int id){
        return repo.findById(id)
                .map(tipoCtaDTO -> {
                    repo.delete(id);
                    return true;})
                .orElse(false);
    }
}
