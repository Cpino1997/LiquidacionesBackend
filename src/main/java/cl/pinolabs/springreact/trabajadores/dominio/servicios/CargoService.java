package cl.pinolabs.springreact.trabajadores.dominio.servicios;

import cl.pinolabs.springreact.trabajadores.dominio.dto.CargoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.repository.CargoDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    private final CargoDTORepository repo;

    public CargoService(CargoDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<CargoDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<CargoDTO> findById(int id){
        return repo.findById(id);
    }
    public CargoDTO save(CargoDTO cargoDTO){
        return repo.save(cargoDTO);
    }
    public boolean delete(int id){
        return repo.findById(id)
                .map(cargoDTO -> {
                    repo.delete(id);
                    return true;})
                .orElse(false);
    }
}
