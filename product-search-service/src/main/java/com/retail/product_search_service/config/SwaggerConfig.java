package com.retail.product_search_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//@Configuration tells Spring:“This class contains instructions for setting up objects (beans) that the app will need.”
//Example: SwaggerConfig class is the blueprint for setting up Swagger/OpenAPI.
//Think of @Bean as actually making a dish from the blueprint.
//@Bean tells Spring:“Here is an object (OpenAPI) ready to use. Spring, keep it in your storage and give it to anyone who asks for it.”
//The object returned (OpenAPI) is now managed by Spring — you don’t have to create it manually elsewhere.
//In Swagger/OpenAPI (springdoc), we create an OpenAPI object to configure how our API docs appear in Swagger UI:
//new OpenAPI() → creates the main OpenAPI object.
//.info(...) → sets general information about your API (title, description, version, contact info, etc.).
@Configuration
public class SwaggerConfig {
	
	@Bean
    public OpenAPI productSearchOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Product Search API")
                    .description("API for managing and searching products in retail")
                    .version("1.0"));
	}

}
