package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.AFPDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.AFPServicio;
import jakarta.validation.Valid;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Order(2)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/afp")
public class AFPController {
    private final AFPServicio afpServicio;
    public AFPController(AFPServicio afpServicio) {
        this.afpServicio = afpServicio;
    }
    @GetMapping()
    public ResponseEntity<List<AFPDTO>> getAllAfps(){
        return afpServicio.findAll()
                .map(afps -> new ResponseEntity<>(afps, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AFPDTO> getAfpById(@PathVariable("id") int id){
        return afpServicio.findById(id)
                .map(afpDTO -> new ResponseEntity<>(afpDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AFPDTO> updateAFP(@PathVariable(value = "id") int id, @Valid @RequestBody AFPDTO afpdto) {
        AFPDTO reg = afpServicio.findById(id).get();
        reg.setId(id);
        reg.setNombre(afpdto.getNombre());
        reg.setDescuento(afpdto.getDescuento());
        final AFPDTO update = afpServicio.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping("")
    public ResponseEntity<AFPDTO> save(@Valid @RequestBody AFPDTO afpdto) {
        return new ResponseEntity<>(afpServicio.save(afpdto) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        if (afpServicio.delete(id)){
            return ResponseEntity.ok("AFP Eliminada con Exito!");
        } else {
            return ResponseEntity.badRequest().body("no se a podido eliminar la AFP: "+ id);
        }
    }
    @PostMapping("/test")
    public String postBody(@RequestBody String nombre) {
        String resultado ="";
        System.out.println(nombre);
        if(afpServicio.exitsByNombre(nombre)){
            resultado = "existe";
        }else{
            resultado = "no existe";
        }
        return resultado;
    }
}
