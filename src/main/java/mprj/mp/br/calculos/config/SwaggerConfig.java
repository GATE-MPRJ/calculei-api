package mprj.mp.br.calculos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger_host}")
    private String SwaggerH;
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(SwaggerH)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("mprj.mp.br.calculos.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("REST API GATE-MPRJ")
                .description("\"REST API - CÁLCULOS JUDICIAIS GATE-MPRJ \"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://swagger.io/license")
                .contact(new Contact("GATE - MPRJ", "https://www.mprj.mp.br", "secgate@mprj.mp.br"))
                .build();
    }


}
