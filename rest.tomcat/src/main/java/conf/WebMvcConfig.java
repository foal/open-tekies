package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
@SuppressWarnings({ "nls" })
@EnableSwagger2
public class WebMvcConfig {
	@Bean
	public static JndiObjectFactoryBean dataSource() {
		final JndiObjectFactoryBean ds = new JndiObjectFactoryBean();
		ds.setJndiName("java:comp/env/jdbc/tekiesDS");
		return ds;
	}

	@Bean
	public Docket swaggerDocket() {
		final Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
		// Omit default http response error codes table
		docket.useDefaultResponseMessages(false);
		// docket.securitySchemes(securitySchemes());
		// docket.securityContexts(Collections.singletonList(securityContext()));
		return docket;
	}

}
