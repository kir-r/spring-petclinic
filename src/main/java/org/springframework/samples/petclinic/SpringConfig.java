package org.springframework.samples.petclinic;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Testing Petclinic with 17 Java")
                .description("Testing Petclinic with 17 Java according task 9417 through Swagger UI"));
    }
}
