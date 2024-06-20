package org.timofeeva.docs.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.timofeeva.docs.repository",
        entityManagerFactoryRef = "docsEntityManager",
        transactionManagerRef = "docsTransactionManager")
@EnableTransactionManagement
@Profile("!testing")
public class DatabaseConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties docsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.configuration")
    public HikariDataSource docsDataSource() {
        return docsDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "docsEntityManager")
    public LocalContainerEntityManagerFactoryBean docsEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(docsDataSource());
        em.setPackagesToScan("org.timofeeva.docs.domain");
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.default_schema", "docs");
        em.setJpaProperties(jpaProperties);
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager docsTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(docsEntityManager().getObject());
        return transactionManager;
    }
}
