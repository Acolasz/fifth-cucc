package hu.kukutyin.fifth.bean.config;

import java.util.ArrayList;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see "https://github.com/eugenp/tutorials/blob/master/spring-boot-modules/spring-boot-mvc/src/main/java/com/baeldung/swagger2boot/configuration/SpringFoxConfig.java"
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    BuildProperties buildProperties;

    private static final String BASE_PACKAGE_CONTROLLER = "hu.kukutyin.fifth";

    @Autowired
    public SwaggerConfig(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_CONTROLLER))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                buildProperties.getArtifact(),
                String.format("%s %s", buildProperties.getArtifact(), "some custom description of API."),
                buildProperties.getVersion(),
                "Terms of service",
                ApiInfo.DEFAULT_CONTACT,
                "app.swaggerhub.com",
                "https://app.swaggerhub.com/apis/Coinfirm-swagger/API/3.0.5#/",
                new ArrayList()
        );
    }
}
