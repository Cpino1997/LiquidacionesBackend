package cl.pinolabs.springreact;

import cl.pinolabs.springreact.trabajadores.percistencia.crud.TestCrud;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringReactApplicationTests {
    @Autowired
    TestCrud testCrud;

    @Test
    @Order(1)
    void testCrearTest() {
        cl.pinolabs.springreact.trabajadores.percistencia.entity.Test test = new cl.pinolabs.springreact.trabajadores.percistencia.entity.Test();
        test.setId(1);
        test.setMensaje("----- Funcionando -----");
        cl.pinolabs.springreact.trabajadores.percistencia.entity.Test result = testCrud.save(test);
        assertNotNull(result);

        Optional<cl.pinolabs.springreact.trabajadores.percistencia.entity.Test> t = testCrud.findById(test.getId());
        assertTrue(t.isPresent());
    }

    @Test
    @Order(2)
    void testEliminarTest() {
        testCrud.deleteById(1);
        Optional<cl.pinolabs.springreact.trabajadores.percistencia.entity.Test> t = testCrud.findById(1);
        assertFalse(t.isPresent());
    }
}

