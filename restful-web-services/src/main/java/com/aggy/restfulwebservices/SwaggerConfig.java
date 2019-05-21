package com.aggy.restfulwebservices;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Contact DEFAULT_CONTACT = new Contact("Kamil Augustyn", "www.aaa.com", "kamilaugusyn92@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO =
            new ApiInfo("restful-web-services API documentation", "Description of API", "1.0", "http://zasady.it/", DEFAULT_CONTACT,
                    "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(Sets.newHashSet("application/json","application/xml"))
                .consumes(Sets.newHashSet("application/json","application/xml"));
    }
}
