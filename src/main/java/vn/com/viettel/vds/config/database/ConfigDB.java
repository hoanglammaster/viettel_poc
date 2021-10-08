package vn.com.viettel.vds.config.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public interface ConfigDB {

    DataSourceProperties getDataSourceProperties();

    DataSource dataSource();

    LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            DataSource dataSource
    );

    PlatformTransactionManager getPlatformTransactionManager(EntityManagerFactory entityManagerFactory);
}
