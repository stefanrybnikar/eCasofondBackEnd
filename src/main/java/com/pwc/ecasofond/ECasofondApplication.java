package com.pwc.ecasofond;

import com.pwc.ecasofond.configuration.RsaKeyProperties;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ECasofondApplication {
    private final String basicAuth = "basicAuth";
    private final String bearerAuth = "bearerAuth";
    private final Info apiInfo = new Info()
            .version("0.8.1")
            .description("ECasofond endpoint documentation");

    public static void main(String[] args) {
        SpringApplication.run(ECasofondApplication.class, args);
    }

    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .addOpenApiCustomizer(
                        openApi -> {
                            openApi.setInfo(apiInfo.title("v1"));
                            openApi.getComponents()
                                    .addSecuritySchemes(basicAuth, new SecurityScheme()
                                            .name(basicAuth)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("basic"))
                                    .addSecuritySchemes(bearerAuth, new SecurityScheme()
                                            .name(bearerAuth)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT"));
                        })
                .build();
    }
}