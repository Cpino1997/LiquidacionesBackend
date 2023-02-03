package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.TipoCuentaDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.TipoCtaService;
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
@RequestMapping("/tipo")
public class TipoCuentasController {
    private final TipoCtaService tipoService;
    public TipoCuentasController(TipoCtaService tipoService) {
        this.tipoService = tipoService;
    }
    @GetMapping()
    @Operation(summary = "Obtener todos los tipos de cuentas", description = "Obtenemos un json con todos los tipos de cuentas registrados en la bd")
    public ResponseEntity<List<TipoCuentaDTO>> getTipos(){
        return tipoService.findAll()
                .map(tipos -> new ResponseEntity<>(tipos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un solo Tipo de cuenta", description = "Obtenemos un json con un TIpo de cuenta segun su id")
    public ResponseEntity<TipoCuentaDTO> getTipoCtaById(@PathVariable("id") int id){
        return tipoService.findById(id)
                .map(tipoDTO -> new ResponseEntity<>(tipoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Tipo DE cuenta", description = "Nos permite cambiar el Tipo de cuenta")
    public ResponseEntity<TipoCuentaDTO> updateSalud(@PathVariable(value = "id") int id, @Valid @RequestBody TipoCuentaDTO tipoDTO) {
        TipoCuentaDTO reg = tipoService.findById(id).get();
        reg.setId(id);
        reg.setNombre(tipoDTO.getNombre());
        final TipoCuentaDTO update = tipoService.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping()
    @Operation(summary = "Crear una Tipo De Cuenta", description = "Desde aqui podemos crear un nuevo Tipo de cuenta, necesitamos de un archivo json con las propiedades para este")
    public ResponseEntity<TipoCuentaDTO> saveSalud(@Valid @RequestBody TipoCuentaDTO tipoDTO) {
        return new ResponseEntity<>(tipoService.save(tipoDTO) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Tipo de cuenta ", description = "Nos permite Eliminar un Tipo de cuenta segun su id.")
    public ResponseEntity<String> deleteSalud(@PathVariable("id") int id){
        if (tipoService.delete(id)){
            return ResponseEntity.ok("Tipo de cuenta Eliminado con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar el Tipo de cuenta: "+ id);
        }
    }
}
