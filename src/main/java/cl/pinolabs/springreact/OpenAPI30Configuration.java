package cl.pinolabs.springreact;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
@Configuration
@SecurityScheme(
        name = "jwt",
        type = SecuritySchemeType.APIKEY,
        scheme = "Cookie",
        in = SecuritySchemeIn.COOKIE
)
public class OpenAPI30Configuration {

}