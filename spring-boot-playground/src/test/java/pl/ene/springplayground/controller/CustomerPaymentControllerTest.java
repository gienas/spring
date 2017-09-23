package pl.ene.springplayground.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.ene.springplayground.service.CustomerService;
import pl.ene.springplayground.service.InvoiceService;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerPaymentControllerTest {
	
	@Mock InvoiceService invoiceService;
	
	@Mock CustomerService customerService;
	
	@InjectMocks
	@Autowired
	CustomerPaymentController controller;
	
	@Test
	public void pay_payBillShouldBeCallOnce() {
		controller.pay("123", "1234");
		verify(customerService).payBill("123", "1234");
	}
	
	@Test
	public void pay_shouldBeSuccessfully() throws Exception {
		Mockito.when(customerService.payBill(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		MvcResult result =  mockMvc.perform(get("/pay")).andReturn();
		assertEquals("{\"status\":\"true\"}", result.getResponse().getContentAsString());
	}

	
}
