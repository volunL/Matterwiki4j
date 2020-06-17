package com.brainboom.matterwiki4jboot.swagger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Component
public class Swagger2Config {

    @Autowired
    private Swagger2Props swagger2Props;

    @Bean
    public Docket docAPi() {
        Docket d = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger2Props.getBasePackage()))
                .build();
        //.pathMapping(this.path_mapping);
        return d;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swagger2Props.getTitle())
                .contact(new Contact(swagger2Props.getContact_name(), swagger2Props.getContact_url(), swagger2Props.getContact_email()))
                .version(swagger2Props.getVersion())
                .build();

    }
}
