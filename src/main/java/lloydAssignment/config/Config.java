package lloydAssignment.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaRepositories(basePackages = "lloydAssignment.repository")
@EnableSwagger2
@EnableCaching
@Configuration
public class Config {
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(cacheManagerFactory().getObject());
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo metaInfo() {
		
		ApiInfo apiInfo = new ApiInfo("Spring Boot Swagger", 
				"Spring Boot Swagger API  for Employee CRUD operations",
				"1.0", 
				"for a year", 
				"contact support", 
				"1 year", "https:google.com");
		return apiInfo;
	}

	private EhCacheManagerFactoryBean cacheManagerFactory() {
		EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
		bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		bean.setShared(true);
		return bean;
	}
}
