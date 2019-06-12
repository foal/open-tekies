package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@SuppressWarnings({ "nls" })
public class WebMvcConfig {
	@Bean
	public static JndiObjectFactoryBean dataSource() {
		final JndiObjectFactoryBean ds = new JndiObjectFactoryBean();
		ds.setJndiName("java:comp/env/jdbc/tekiesDS");
		return ds;
	}

}
