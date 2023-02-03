package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.SaludDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.SaludService;
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
@RequestMapping("/salud")
public class SaludController {
    private final SaludService saludService;
    public SaludController(SaludService saludService) {
        this.saludService = saludService;
    }
    @GetMapping()
    @Operation(summary = "Obtener todos los Salud", description = "Obtenemos un json con todos los Salud registrados en la bd")
    public ResponseEntity<List<SaludDTO>> getSaluds(){
        return saludService.findAll()
                .map(saluds -> new ResponseEntity<>(saluds, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un solo Salud", description = "Obtenemos un json con un Salud segun su id")
    public ResponseEntity<SaludDTO> getSaludById(@PathVariable("id") int id){
        return saludService.findById(id)
                .map(saludDTO -> new ResponseEntity<>(saludDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Salud", description = "Nos permite cambiar el descuento de la Salud y el nombre en caso de ser necesario")
    public ResponseEntity<SaludDTO> updateSalud(@PathVariable(value = "id") int id, @Valid @RequestBody SaludDTO saludDTO) {
        SaludDTO reg = saludService.findById(id).get();
        reg.setId(id);
        reg.setNombre(saludDTO.getNombre());
        reg.setDescuento(saludDTO.getDescuento());
        final SaludDTO update = saludService.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping()
    @Operation(summary = "Crear una Salud", description = "Desde aqui podemos crear un nuevo Salud, necesitamos de un archivo json con las propiedades para este")
    public ResponseEntity<SaludDTO> saveSalud(@Valid @RequestBody SaludDTO saludDTO) {
        return new ResponseEntity<>(saludService.save(saludDTO) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Salud ", description = "Nos permite Eliminar una Salud segun su id.")
    public ResponseEntity<String> deleteSalud(@PathVariable("id") int id){
        if (saludService.delete(id)){
            return ResponseEntity.ok("Cargo Eliminado con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar el cargo: "+ id);
        }
    }
}
