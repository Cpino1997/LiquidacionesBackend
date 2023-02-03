package cl.pinolabs.springreact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@SpringBootApplication
@EnableWebMvc
public class SpringReactApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringReactApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringReactApplication.class, args);
        logger.info("Estoy corriendo en http://localhost:8080/api");
    }

}
