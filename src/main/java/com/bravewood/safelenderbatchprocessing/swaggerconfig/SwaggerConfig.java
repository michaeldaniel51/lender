package com.bravewood.safelenderbatchprocessing.swaggerconfig;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .contact(new Contact().name("Bravewood Finance"))
                        .version("2.0")
                        .termsOfService("this is my terms of service")
                        .title("SafeLenderBatchProcess"));

    }




}
