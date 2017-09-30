package pl.ene.springbootrestjpa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.repository.CustomerManualRespository;
import pl.ene.springbootrestjpa.repository.CustomerRepository;

@RestController
public class UserController {

	private CustomerRepository customerRepository;
	private CustomerManualRespository customerManualRespository;
	
	
	public UserController(CustomerRepository userRepository, CustomerManualRespository customerManualRespository) {
		// TODO Auto-generated constructor stub
		this.customerRepository = userRepository;
		this.customerManualRespository = customerManualRespository;
	}
	
	@GetMapping("/user/{name}")
	Customer getUser(@PathVariable(name = "name") String name) {
		//TODO implementation
		return null;
	}

	@GetMapping("/users")
	public List<Customer> users() {
		List<Customer> result = new ArrayList<>();
		customerRepository.findAll().forEach(result::add);
		return result;
	}

	@GetMapping("/find")
	public List<Customer> find( @RequestParam String name) {
		List<Customer> result = new ArrayList<>();
		customerRepository.findByNameStartingWithIgnoreCase(name).forEach(result::add);
		return result;
	}

	@GetMapping("/findByName")
	public List<Customer> findByName( @RequestParam String name) {
		List<Customer> result = new ArrayList<>();
		customerManualRespository.getByName(name).forEach(result::add);
		return result;
	}

}
