package com.pwc.ecasofond;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECasofondApplication {
    public static void main(String[] args) {
        SpringApplication.run(ECasofondApplication.class, args);
    }

    private static final String VERSION = "0.6.0";
    private static final String DESCRIPTION = "ECasofond endpoint documentation";

    @Bean
    public GroupedOpenApi companyApi() {
        Info apiInfo = new Info()
                .title("Company endpoints")
                .version(VERSION)
                .description(DESCRIPTION);

        return GroupedOpenApi
                .builder()
                .group("company")
                .pathsToMatch("/company/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(apiInfo))
                .build();
    }

    @Bean
    public GroupedOpenApi employeeApi() {
        Info apiInfo = new Info()
                .title("User endpoints")
                .version(VERSION)
                .description(DESCRIPTION);

        return GroupedOpenApi
                .builder()
                .group("user")
                .pathsToMatch("/user/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(apiInfo))
                .build();
    }

    @Bean
    public GroupedOpenApi entryApi() {
        Info apiInfo = new Info()
                .title("Entry endpoints")
                .version(VERSION)
                .description(DESCRIPTION);

        return GroupedOpenApi
                .builder()
                .group("entry")
                .pathsToMatch("/entry/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(apiInfo))
                .build();
    }

    @Bean
    public GroupedOpenApi roleApi() {
        Info apiInfo = new Info()
                .title("Role endpoints")
                .version(VERSION)
                .description(DESCRIPTION);

        return GroupedOpenApi
                .builder()
                .group("role")
                .pathsToMatch("/role/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(apiInfo))
                .build();
    }

    @Bean
    public GroupedOpenApi entryTypeApi() {
        Info apiInfo = new Info()
                .title("Entry type endpoints")
                .version(VERSION)
                .description(DESCRIPTION);

        return GroupedOpenApi
                .builder()
                .group("entry-type")
                .pathsToMatch("/entry-type/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(apiInfo))
                .build();
    }

    @Bean
    public GroupedOpenApi professionApi() {
        Info apiInfo = new Info()
                .title("Profession endpoints")
                .version(VERSION)
                .description(DESCRIPTION);

        return GroupedOpenApi
                .builder()
                .group("profession")
                .pathsToMatch("/profession/**")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(apiInfo))
                .build();
    }
}