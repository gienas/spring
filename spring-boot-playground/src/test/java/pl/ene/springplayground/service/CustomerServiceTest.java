package pl.ene.springplayground.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	
	//used because of problems with mockito and aop
	//https://github.com/mockito/mockito/issues/209
	@MockBean InvoiceService invoiceService;
		
	@InjectMocks
	@Autowired
	CustomerService customerService;
	
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
		verify(invoiceService).getInvoice( any() , any());
	}
	

}
