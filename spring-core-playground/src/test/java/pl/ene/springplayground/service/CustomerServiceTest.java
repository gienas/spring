package pl.ene.springplayground.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.ene.springplayground.PlaygroundConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=PlaygroundConfiguration.class)
public class CustomerServiceTest {
	
	@Mock InvoiceService invoiceService;
	
	@InjectMocks
	@Autowired
	CustomerService customerService;

	
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void customerServiceIsNotNull() {
		assertNotNull(customerService);
	}
	
	@Test
	public void payBill_inoviceServiceInjected() {
		
		assertNotNull(customerService.getInvoiceService());
		
	}
	

	@Test
	public void payBill_inoviceShouldBeCallOnce() {
		
		customerService.payBill("123", "1234");
		
		verify(invoiceService).getInvoice("123", "1234");
	}
	

}
