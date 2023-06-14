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
            .version("0.6.9")
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
/*
    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi
                .builder()
                .group("auth")
                .pathsToMatch("/auth/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Authentication endpoints"));
                    openApi.getComponents().addSecuritySchemes(basicAuth, new SecurityScheme()
                            .name(basicAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("basic"));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi companyApi() {
        return GroupedOpenApi
                .builder()
                .group("company")
                .pathsToMatch("/company/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Company endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi
                .builder()
                .group("user")
                .pathsToMatch("/user/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("User endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi entryApi() {
        return GroupedOpenApi
                .builder()
                .group("entry")
                .pathsToMatch("/entry/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Entry endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi roleApi() {
        return GroupedOpenApi
                .builder()
                .group("role")
                .pathsToMatch("/role/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Role endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi entryTypeApi() {
        return GroupedOpenApi
                .builder()
                .group("entrytype")
                .pathsToMatch("/entrytype/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Entry type endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi professionTypeApi() {
        return GroupedOpenApi
                .builder()
                .group("professiontype")
                .pathsToMatch("/professiontype/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Profession type endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }


    @Bean
    public GroupedOpenApi professionTypeEntryTypeApi() {
        return GroupedOpenApi
                .builder()
                .group("professiontypeentrytype")
                .pathsToMatch("/professiontypeentrytype/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.setInfo(apiInfo.title("Profession type X entry type relation endpoints"));
                    openApi.getComponents().addSecuritySchemes(bearerAuth, new SecurityScheme()
                            .name(bearerAuth)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"));
                })
                .build();
    }*/
}