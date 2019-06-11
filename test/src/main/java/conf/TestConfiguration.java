package conf;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@SuppressWarnings("static-method")
@ComponentScan("conf")
public class TestConfiguration {

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5433/sa");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		final Flyway flyway = new Flyway(configure(dataSource));
		flyway.clean();

		return dataSource;
	}

	private static FluentConfiguration configure(final DataSource dataSource) {
		final FluentConfiguration configuration = new FluentConfiguration();
		configuration.dataSource(dataSource);
		return configuration;
	}

}
