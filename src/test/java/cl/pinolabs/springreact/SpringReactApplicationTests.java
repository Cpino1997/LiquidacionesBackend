package cl.pinolabs.springreact;

import cl.pinolabs.springreact.trabajadores.percistencia.crud.TestCrud;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringReactApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(SpringReactApplicationTests.class);
    @Autowired
    TestCrud testCrud;

    @Test
    @Order(1)
    void testCrearTest() {
        logger.info("Creando la primera prueba!");
        cl.pinolabs.springreact.trabajadores.percistencia.entity.Test test = new cl.pinolabs.springreact.trabajadores.percistencia.entity.Test();
        test.setId(1);
        test.setMensaje("----- Funcionando -----");
        cl.pinolabs.springreact.trabajadores.percistencia.entity.Test result = testCrud.save(test);
        logger.info("Se ha creado el usuario test!");
        assertNotNull(result);
        Optional<cl.pinolabs.springreact.trabajadores.percistencia.entity.Test> t = testCrud.findById(test.getId());
        assertTrue(t.isPresent());
    }

    @Test
    @Order(2)
    void testEliminarTest() {
        logger.info("Creando la Segunda prueba!");
        testCrud.deleteById(1);
        logger.info("Se ha eliminado el usuario test!");
        Optional<cl.pinolabs.springreact.trabajadores.percistencia.entity.Test> t = testCrud.findById(1);
        assertFalse(t.isPresent());
    }
}

