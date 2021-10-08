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
 * default db use for User information, role, permission, ...
 */
@Configuration
@ConditionalOnProperty(
        value = "app.datasource.default.enable",
        havingValue = "true",
        matchIfMissing = true)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainTransactionManager",
        basePackages = {"vn.com.viettel.vds.repository.main"})
public class DefaultConfigDB implements ConfigDB {

    @Override
    @Primary
    @Bean("mainDataSourceProperties")
    @ConfigurationProperties("app.datasource.default")
    public DataSourceProperties getDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Override
    @Primary
    @Bean("mainDataSource")
    @ConfigurationProperties("app.datasource.default.configuration")
    public DataSource dataSource() {
        return new DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Override
    @Primary
    @Bean(name = "mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mainDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("vn.com.viettel.vds.model")
                .persistenceUnit("main")
                .build();
    }

    @Override
    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager getPlatformTransactionManager(
            @Qualifier("mainEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
