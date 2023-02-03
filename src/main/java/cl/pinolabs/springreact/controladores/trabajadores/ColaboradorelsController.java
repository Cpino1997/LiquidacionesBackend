package cl.pinolabs.springreact.controladores.trabajadores;

import cl.pinolabs.springreact.trabajadores.dominio.dto.ColaboradoresDTO;
import cl.pinolabs.springreact.trabajadores.dominio.servicios.ColaboradoresService;
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
@RequestMapping("/colaboradores")
public class ColaboradorelsController {
    private final ColaboradoresService service;
    public ColaboradorelsController(ColaboradoresService service) {
        this.service = service;
    }
    @GetMapping()
    @Operation(summary = "Obtener todos los colaboradores", description = "Obtenemos un json con todos los colaboradores registrados en la bd")
    public ResponseEntity<List<ColaboradoresDTO>> getColaboradores(){
        return service.findAll()
                .map(colaboradores -> new ResponseEntity<>(colaboradores, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un solo colaborador", description = "Obtenemos un json con un colaborador segun su id")
    public ResponseEntity<ColaboradoresDTO> getCargoById(@PathVariable("id") int id){
        return service.findById(id)
                .map(colaboradoresDTO -> new ResponseEntity<>(colaboradoresDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un Colaborador", description = "Nos permite cambiar el Colaborador ")
    public ResponseEntity<ColaboradoresDTO> updateBanco(@PathVariable(value = "id") int id, @Valid @RequestBody ColaboradoresDTO colaboradoresDTO) {
        ColaboradoresDTO reg = service.findById(id).get();
        reg.setId(id);
        reg.setIdBanco(colaboradoresDTO.getIdBanco());
        reg.setIdAFP(colaboradoresDTO.getIdAFP());
        reg.setIdSalud(colaboradoresDTO.getIdSalud());
        reg.setIdCargo(colaboradoresDTO.getIdCargo());
        reg.setIdTipoCuenta(colaboradoresDTO.getIdTipoCuenta());
        reg.setRut(colaboradoresDTO.getRut());
        reg.setNombre(colaboradoresDTO.getNombre());
        reg.setCorreo(colaboradoresDTO.getCorreo());
        reg.setDireccion(colaboradoresDTO.getDireccion());
        reg.setFecha_ingreso(colaboradoresDTO.getFecha_ingreso());
        reg.setFecha_nacimiento(colaboradoresDTO.getFecha_nacimiento());
        reg.setEstado(colaboradoresDTO.isEstado());
        final ColaboradoresDTO update = service.save(reg);
        return ResponseEntity.ok(update);
    }
    @PostMapping()
    @Operation(summary = "Crear un Colaborador", description = "Desde aqui podemos crear un nuevo Colaborador, necesitamos de un archivo json con las propiedades para este")
    public ResponseEntity<ColaboradoresDTO> saveColaborador(@Valid @RequestBody ColaboradoresDTO colaboradoresDTO) {
        return new ResponseEntity<>(service.save(colaboradoresDTO) ,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Colaborador", description = "Nos permite Eliminar un Colaborador segun su id.")
    public ResponseEntity<String> deleteColaboradores(@PathVariable("id") int id){
        if (service.delete(id)){
            return ResponseEntity.ok("Colaborador Eliminado con Exito!");
        } else {
            return ResponseEntity.badRequest().body("No se a podido eliminar el Colaborador: "+ id);
        }
    }
}

