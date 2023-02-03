package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.AFPDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.AFPServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200, http://localhost:8080", maxAge = 3600, allowCredentials="true")
@SecurityRequirement(name = "jwt")
@RequestMapping("/afp")
public class AFPController {
    private final AFPServicio afpServicio;
    public AFPController(AFPServicio afpServicio) {
        this.afpServicio = afpServicio;
    }
    @GetMapping()
    @Operation(summary = "Obtener todas las AFP", description = "Obtenemos un json con todas las afp registradas en el sistema")
    public ResponseEntity<List<AFPDTO>> getAllAfps(){
        return afpServicio.findAll()
                .map(afps -> new ResponseEntity<>(afps, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una AFP", description = "Obtenemos un json una afp especifica segun su id")
    public ResponseEntity<AFPDTO> getAfpById(@PathVariable("id") int id){
        return afpServicio.findById(id)
                .map(afpDTO -> new ResponseEntity<>(afpDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una AFP", description = "Enviamos un json con los datos de la afp a actualizar")
    public ResponseEntity<AFPDTO> updateAFP(@PathVariable(value = "id") int id, @Valid @RequestBody AFPDTO afpdto) {
        AFPDTO reg = afpServicio.findById(id).get();
        reg.setId(id);
        reg.setNombre(afpdto.getNombre());
        reg.setDescuento(afpdto.getDescuento());
        final AFPDTO update = afpServicio.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping("")
    @Operation(summary = "Crear una AFP", description = "Enviamos un json que contiene una afp sin id para crear")
    public ResponseEntity<AFPDTO> save(@Valid @RequestBody AFPDTO afpdto) {
        return new ResponseEntity<>(afpServicio.save(afpdto) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una AFP", description = "Enviamos un id que usaremos para identificar y eliminar una afp")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        if (afpServicio.delete(id)){
            return ResponseEntity.ok("AFP Eliminada con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar la AFP: "+ id);
        }
    }
    @PostMapping("/test")
    @Operation(summary = "Test para buscar por nobre", description = "Aun en creacion!")
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
