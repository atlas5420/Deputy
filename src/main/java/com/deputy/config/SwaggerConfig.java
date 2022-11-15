package com.deputy.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	String API_NAME = "Deputy API";
	String API_VERSION = "0.0.1";
	String API_DESCRIPTION = "Deputy API ¸í¼¼¼­";
	
	public Docket api() {
		Parameter parameterBuilder = new ParameterBuilder()
				.name(HttpHeaders.AUTHORIZATION)
				.description("Access Token")
				.modelRef(new ModelRef("String"))
				.parameterType("header")
				.required(false)
				.build();
		
		List<Parameter> globalParameters = new ArrayList<Parameter>();
		globalParameters.add(parameterBuilder);
		
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(globalParameters)
				.apiInfo(apiinfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.deputy.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	public ApiInfo apiinfo() {
		return new ApiInfoBuilder()
				   .title(API_NAME)
				   .version(API_VERSION)
				   .description(API_DESCRIPTION)
				   .build();
	}
	
}