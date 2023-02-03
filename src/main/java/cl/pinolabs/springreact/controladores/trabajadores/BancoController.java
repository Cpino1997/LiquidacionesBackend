package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.BancoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.BancoServicio;
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
@RequestMapping("/banco")
public class BancoController {
    private final BancoServicio bancoServicio;
    public BancoController(BancoServicio bancoServicio) {
        this.bancoServicio = bancoServicio;
    }
    @GetMapping()
    @Operation(summary = "Obtener todos los bancos", description = "Obtenemos un json con todos los bancos registrados en la bd")
    public ResponseEntity<List<BancoDTO>> getBancos(){
        return bancoServicio.findAll()
                .map(bancos -> new ResponseEntity<>(bancos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un solo bancos", description = "Obtenemos un json con un banco segun su id")
    public ResponseEntity<BancoDTO> getBancoById(@PathVariable("id") int id){
        return bancoServicio.findById(id)
                .map(bancoDTO -> new ResponseEntity<>(bancoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un banco", description = "Nos permite cambiar el nombre del banco en caso de ser necesario")
    public ResponseEntity<BancoDTO> updateBanco(@PathVariable(value = "id") int id, @Valid @RequestBody BancoDTO bancoDTO) {
        BancoDTO reg = bancoServicio.findById(id).get();
        reg.setId(id);
        reg.setNombre(bancoDTO.getNombre());
        final BancoDTO update = bancoServicio.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping()
    @Operation(summary = "Crear un banco", description = "Desde aqui podemos crear un nuevo banco, necesitamos de un archivo json con las propiedades para este")
    public ResponseEntity<BancoDTO> saveBanco(@Valid @RequestBody BancoDTO bancoDTO) {
        return new ResponseEntity<>(bancoServicio.save(bancoDTO) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un banco", description = "Nos permite Eliminar un banco segun su id.")
    public ResponseEntity<String> deleteBanco(@PathVariable("id") int id){
        if (bancoServicio.delete(id)){
            return ResponseEntity.ok("Banco Eliminado con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar el Banco: "+ id);
        }
    }
}
