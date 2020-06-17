package com.brainboom.matterwiki4jboot.swagger;




import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConfigurationProperties(prefix = "swagger")
@Data
public class Swagger2Props {


    private String title="matter4j api doc";
    private String contact_name="volun";
    private String contact_url="";
    private String basePackage="com.brainboom.matterwikiboot.controller";
    private String contact_email="volun_leung@yahoo.com";
    private String version = "1.0";
    private String path_mapping="";





}
