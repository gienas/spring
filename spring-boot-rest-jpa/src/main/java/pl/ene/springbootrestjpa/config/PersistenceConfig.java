package pl.ene.springbootrestjpa.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class PersistenceConfig {

@Bean
public DataSource dataSource(C3P0DataSourceProperties dataSourcePros) throws PropertyVetoException {

    ComboPooledDataSource  pooledDataSource = new ComboPooledDataSource();
    pooledDataSource.setDriverClass(dataSourcePros.getDriverClass());
    pooledDataSource.setUser(dataSourcePros.getUsername());
    pooledDataSource.setPassword(dataSourcePros.getPassword());
    pooledDataSource.setJdbcUrl(dataSourcePros.getUrl());
    pooledDataSource.setMaxPoolSize(dataSourcePros.getMaxPoolSize());
    pooledDataSource.setMaxIdleTime(dataSourcePros.getMaxIdleTime());

    return pooledDataSource;
}

}
