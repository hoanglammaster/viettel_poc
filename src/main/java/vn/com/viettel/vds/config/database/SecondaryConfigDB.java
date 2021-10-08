package vn.com.viettel.vds.config.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Secondary db use for record Orders
 */
@Configuration
@ConditionalOnProperty(
        value = "app.datasource.secondary.enable",
        havingValue = "true")
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager",
        basePackages = {"vn.com.viettel.vds.repository.secondary"})
public class SecondaryConfigDB implements ConfigDB{

    @Override
    @Primary
    @Bean("secondDataSourceProperties")
    @ConfigurationProperties("app.datasource.secondary")
    public DataSourceProperties getDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Override
    @Primary
    @Bean("secondDataSource")
    @ConfigurationProperties("app.datasource.secondary.configuration")
    public DataSource dataSource() {
        return new DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Override
    @Primary
    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("secondDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("vn.com.viettel.vds.model")
                .persistenceUnit("secondary")
                .build();
    }

    @Override
    @Primary
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager getPlatformTransactionManager(
            @Qualifier("secondEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
