package me.norrapat.employer.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@ConfigurationProperties("swagger")
public class Swagger2Config {

    @Autowired
    private TypeResolver typeResolver;

    @Setter
    private String title;

    @Setter
    private String description;

    @Setter
    private String version;

    @Setter
    private String team;

    @Setter
    private String email;

    @Setter
    private String url;

    @Bean
    public Docket employerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(apis())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(appInfo())
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .securitySchemes(singletonList(apiKey()))
                .securityContexts(singletonList(securityContext()))
                //.enableUrlTemplating(true)
                ;
    }

    private Predicate<RequestHandler> apis() {
        return withClassAnnotation(Api.class);
    }

    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(new Contact(team, url, email))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT Token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("read", "access read");
        authorizationScopes[1] = new AuthorizationScope("write", "access write");
        return singletonList(new SecurityReference("JWT Token", authorizationScopes));
    }

    @Bean
    SecurityConfiguration security(
            @Value("${jwt.clientId:myEmployer}") String clientId,
            @Value("${jwt.client-secret:secret}") String clientSecret
    ) {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .realm("employer-app-realm")
                .appName("employer-app")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .enableCsrfSupport(false)
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .showCommonExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

}
