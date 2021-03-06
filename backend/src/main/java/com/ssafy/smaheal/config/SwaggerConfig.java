package com.ssafy.smaheal.config;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket swaggerSpringfoxDocket() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiEndPointsInfo()).pathMapping("/")
				.forCodeGeneration(true).genericModelSubstitutes(ResponseEntity.class)
				.ignoredParameterTypes(java.sql.Date.class)
				.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
				.directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
				.directModelSubstitute(java.time.LocalDateTime.class, Date.class)
				.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false);
		docket = docket.select().paths(PathSelectors.any()).build();
		return docket;
	}

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Decide REST API")
				.description("This documents describes about decide version 7 REST API")
				.contact(new Contact("Kim1oi", "kimy1ry.com", "kim1.com")).license("Apache 2.0")
				.version("v1").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
		return springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.any()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}

}