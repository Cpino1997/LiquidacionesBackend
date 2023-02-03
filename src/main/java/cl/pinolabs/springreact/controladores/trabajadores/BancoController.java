package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.BancoDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.BancoServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/banco")
public class BancoController {
    private final BancoServicio bancoServicio;
    public BancoController(BancoServicio bancoServicio) {
        this.bancoServicio = bancoServicio;
    }
    @GetMapping()
    public ResponseEntity<List<BancoDTO>> getBancos(){
        return bancoServicio.findAll()
                .map(bancos -> new ResponseEntity<>(bancos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BancoDTO> getBancoById(@PathVariable("id") int id){
        return bancoServicio.findById(id)
                .map(bancoDTO -> new ResponseEntity<>(bancoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BancoDTO> updateBanco(@PathVariable(value = "id") int id, @Valid @RequestBody BancoDTO bancoDTO) {
        BancoDTO reg = bancoServicio.findById(id).get();
        reg.setId(id);
        reg.setNombre(bancoDTO.getNombre());
        final BancoDTO update = bancoServicio.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping()
    public ResponseEntity<BancoDTO> saveBanco(@Valid @RequestBody BancoDTO bancoDTO) {
        return new ResponseEntity<>(bancoServicio.save(bancoDTO) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBanco(@PathVariable("id") int id){
        if (bancoServicio.delete(id)){
            return ResponseEntity.ok("Banco Eliminado con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar el Banco: "+ id);
        }
    }
}
