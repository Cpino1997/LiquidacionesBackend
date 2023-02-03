package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.CargoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@SecurityRequirement(name = "jwt")
@RequestMapping("/cargo")
public class CargoController {
    private final CargoService cargoService;
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }
    @GetMapping()
    @Operation(summary = "Obtener todos los cargos", description = "Obtenemos un json con todos los cargos registrados en la bd")
    public ResponseEntity<List<CargoDTO>> getCargos(){
        return cargoService.findAll()
                .map(cargos -> new ResponseEntity<>(cargos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un solo cargo", description = "Obtenemos un json con un cargo segun su id")
    public ResponseEntity<CargoDTO> getCargoById(@PathVariable("id") int id){
        return cargoService.findById(id)
                .map(cargoDTO -> new ResponseEntity<>(cargoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Cargo", description = "Nos permite cambiar el nombre del Cargo y el sueldo en caso de ser necesario")
    public ResponseEntity<CargoDTO> updateBanco(@PathVariable(value = "id") int id, @Valid @RequestBody CargoDTO cargoDTO) {
        CargoDTO reg = cargoService.findById(id).get();
        reg.setId(id);
        reg.setCargo(cargoDTO.getCargo());
        reg.setSueldo(cargoDTO.getSueldo());
        final CargoDTO update = cargoService.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping()
    @Operation(summary = "Crear un Cargo", description = "Desde aqui podemos crear un nuevo Cargo, necesitamos de un archivo json con las propiedades para este")
    public ResponseEntity<CargoDTO> saveBanco(@Valid @RequestBody CargoDTO cargoDTO) {
        return new ResponseEntity<>(cargoService.save(cargoDTO) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Cargo", description = "Nos permite Eliminar un Cargo segun su id.")
    public ResponseEntity<String> deleteBanco(@PathVariable("id") int id){
        if (cargoService.delete(id)){
            return ResponseEntity.ok("Cargo Eliminado con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar el cargo: "+ id);
        }
    }
}
