package pl.ene.springbootrestjpa.controllers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.repository.CustomerRepository;

//@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	@MockBean
	private CustomerRepository customerRepository;

	@InjectMocks
	@Autowired
	private CustomerController controller;

//	@Test
//	public void getUser_shouldReturnPayloadToLarge() throws Exception {
//		List<Customer> list = Stream.of(new Customer(), new Customer()).collect(Collectors.toList());
//		when(customerRepository.findByName(anyString())).thenReturn(list);
//		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//		mockMvc.perform(get("/user/Abc")).andExpect(status().isPayloadTooLarge());
//	}

	@Test
	public void getUser_shouldReturnNotFound() throws Exception {
		List<Customer> list = Lists.emptyList();
		when(customerRepository.findByName(anyString())).thenReturn(list);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvc.perform(get("/user/Abc")).andExpect(status().isNotFound());
	}

}
