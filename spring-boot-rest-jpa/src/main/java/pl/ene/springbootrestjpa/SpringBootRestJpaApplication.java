package pl.ene.springbootrestjpa;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import pl.ene.springbootrestjpa.config.C3P0DataSourceProperties;
import pl.ene.springbootrestjpa.config.PersistenceConfig;

//@EnableAutoConfiguration
@SpringBootApplication
@Import(PersistenceConfig.class)
//@EnableConfigurationProperties(C3P0DataSourceProperties.class)
public class SpringBootRestJpaApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(SpringBootRestJpaApplication.class);
	}
	//mc3
	
	public static void test(){
	    //DriverManager dm = DriverManager.get
	}
}
