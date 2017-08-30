package pl.ene.springplayground;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import pl.ene.springplayground.service.CustomerService;

/**
 * Hello world!
 *
 */
public class Playground 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(PlaygroundConfiguration.class);
        CustomerService bean = (CustomerService) context.getBean(CustomerService.class);
        if ( bean != null ) {
        	System.out.println("SUCCESS");
        	bean.payBill("123", "123");
        }
        else {
        	System.out.println("BEAN IS NULL");
        }
        
        context.close();
    }
}
