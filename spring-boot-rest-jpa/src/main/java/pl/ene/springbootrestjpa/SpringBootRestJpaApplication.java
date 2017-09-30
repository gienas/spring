package pl.ene.springbootrestjpa;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import pl.ene.springbootrestjpa.config.PersistenceConfig;

@EnableAutoConfiguration
@SpringBootApplication
@Import(PersistenceConfig.class)
public class SpringBootRestJpaApplication {

	public static void main(String[] args) throws SQLException {
				
		SpringApplication.run(SpringBootRestJpaApplication.class);
//	    //DriverManager dm = DriverManager.getDrivers();
//	    Enumeration<Driver> d  = DriverManager.getDrivers();
//	    while ( d.hasMoreElements()) {
//	       Driver dr =  d.nextElement();
//	       System.out.println(dr);
//	    }		
//	   
//	   Driver driver =  DriverManager.getDriver("jdbc:postgresql:/");
//	   Connection conn = DriverManager.getConnection("");
//	   
//	   
//	   //for (driver.getprop)
//	   
//	   
	}
	//mc3
	
	public static void test(){
	    //DriverManager dm = DriverManager.get
	}
}
