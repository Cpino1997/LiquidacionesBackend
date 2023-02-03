package cl.pinolabs.springreact;

import cl.pinolabs.springreact.trabajadores.percistencia.crud.TestCrud;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringReactApplicationTests {
    @Autowired
    TestCrud testCrud;

    @Test
    @Order(1)
    void create() {
        System.out.println("----- Cargando el Test 1 -----");
        cl.pinolabs.springreact.trabajadores.percistencia.entity.Test test = new cl.pinolabs.springreact.trabajadores.percistencia.entity.Test();
        test.setId(2);
        test.setMensaje("----- Funcionando -----");
        testCrud.save(test);
        System.out.println("----- Se ha creado un usuario -----");
        Optional<cl.pinolabs.springreact.trabajadores.percistencia.entity.Test> t = testCrud.findById(test.getId());
        if(t.isPresent()){
            System.out.println("----- Funcionando! -----");
        }else{
            System.out.println("----- No Funcionando =( -----");
        }
    }

    @Test
    @Order(2)
    void delete(){
        System.out.println("----- Cargando el Test 2 -----");
        Integer id = 2;
        testCrud.deleteById(id);
        Optional<cl.pinolabs.springreact.trabajadores.percistencia.entity.Test> t = testCrud.findById(id);
        if (t.isPresent()){
            System.out.println("----- No Se ha podido eliminar -----");
        }else{
            System.out.println("----- Eliminado con exito! -----");
        }
    }


}
