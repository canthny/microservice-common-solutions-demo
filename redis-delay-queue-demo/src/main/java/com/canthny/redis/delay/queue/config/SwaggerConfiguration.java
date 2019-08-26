package com.canthny.redis.delay.queue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 * @author bingbing
 * @create 2017/9/20 15:01
 */
@Configuration
@EnableConfigurationProperties(SwaggerConfiguration.SwaggerProperties.class)
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware {
	private String basePackage;
	private String creatName;
	private String serviceName;
	private String description;
	@Autowired
	private SwaggerProperties swaggerProperties;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(this.basePackage))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(this.serviceName+" Restful APIs")
				.description(this.description)
				.contact(this.creatName).version("1.0").build();
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.basePackage = swaggerProperties.getBasePackage();
		this.creatName = swaggerProperties.getCreatName();
		this.serviceName = swaggerProperties.getServiceName();
		this.description = swaggerProperties.getDescription();
	}

	@ConfigurationProperties(prefix = "swagger")
	public class SwaggerProperties{

		private String creatName;

		private String serviceName;

		private String description;

		private String basePackage;

		public String getCreatName() {
			return creatName;
		}

		public void setCreatName(String creatName) {
			this.creatName = creatName;
		}

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getBasePackage() {
			return basePackage;
		}

		public void setBasePackage(String basePackage) {
			this.basePackage = basePackage;
		}
	}
}
