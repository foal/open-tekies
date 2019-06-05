package conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "org.jresearch.tekies.resources.api")
@EnableTransactionManagement
@SuppressWarnings("static-method")
@Configuration
public class PersistenceConfig {

	@Bean
	public Flyway dbMigration(final DataSource dataSource) {
		final Flyway flyway = new Flyway(configure(dataSource));
		flyway.migrate();
		return flyway;
	}

	private static FluentConfiguration configure(final DataSource dataSource) {
		final FluentConfiguration configuration = new FluentConfiguration();
		configuration.dataSource(dataSource);
		return configuration;
	}

	@Bean
	@DependsOn("dbMigration")
	public DefaultPersistenceUnitManager persistenceUnitManager(final DataSource dataSource) {
		final DefaultPersistenceUnitManager pm = new DefaultPersistenceUnitManager();
		pm.setDefaultDataSource(dataSource);
		return pm;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DefaultPersistenceUnitManager persistenceUnitManager, @Qualifier("jpaProperties") Properties jpaProperties, JpaVendorAdapter jpaVendorAdapter) {
		final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPersistenceUnitManager(persistenceUnitManager);
		bean.setJpaProperties(jpaProperties);
		bean.setJpaVendorAdapter(jpaVendorAdapter);
		return bean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		final JpaTransactionManager bean = new JpaTransactionManager();
		bean.setEntityManagerFactory(emf);
		return bean;
	}

	/*
	 * <bean class=
	 * "org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor"
	 * /> <bean id="jrs_domain_persistenceUnitManager" class=
	 * "org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager">
	 * <property name="defaultPersistenceUnitName" value="Persistent" />
	 * <property name="persistenceXmlLocation"
	 * value="classpath*:META-INF/persistence.xml" /> <property
	 * name="defaultDataSource" ref="jrs_domain_dataSource" /> <property
	 * name="persistenceUnitPostProcessors"> <bean class=
	 * "org.jresearch.commons.base.utils.MergingPersistenceUnitPostProcessor"
	 * p:defaultPersistenceUnitName = "Persistent"/> </property> </bean> <bean
	 * id="jrs_domain_entityManagerFactory" class=
	 * "org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
	 * <property name="persistenceUnitManager"
	 * ref="jrs_domain_persistenceUnitManager" /> <property
	 * name="loadTimeWeaver"> <bean class=
	 * "org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver"
	 * /> </property> <property name="jpaVendorAdapter"
	 * ref="jrs_domain_jpaVendorAdapter" /> <property name="jpaPropertyMap"
	 * ref="jrs_domain_jpaPropertyMap" /> </bean>
	 */
	//
	// public class HibernatePersistenceUnitInfo implements PersistenceUnitInfo
	// {
	//
	// public static String JPA_VERSION = "2.1";
	// private String persistenceUnitName;
	// private PersistenceUnitTransactionType transactionType
	// = PersistenceUnitTransactionType.RESOURCE_LOCAL;
	// private List<String> managedClassNames;
	// private List<String> mappingFileNames = new ArrayList<>();
	// private Properties properties;
	// private DataSource jtaDataSource;
	// private DataSource nonjtaDataSource;
	// private List<ClassTransformer> transformers = new ArrayList<>();
	//
	// public HibernatePersistenceUnitInfo(
	// String persistenceUnitName, List<String> managedClassNames, Properties
	// properties) {
	// this.persistenceUnitName = persistenceUnitName;
	// this.managedClassNames = managedClassNames;
	// this.properties = properties;
	// }
	//
	// // standard setters / getters
	// }
	/// ...
}
