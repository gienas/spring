package pl.ene.springbootrestjpa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	/**
	 * Get user by name
	 * @param name full name 
	 * @return //TODO
	 */
	@GetMapping("/user/{name}")
	ResponseEntity<Customer> getUser(@PathVariable(name = "name") String name) {
		List<Customer> result  = customerRepository.findByName(name);
		if (result.size() > 1) {
			return  ResponseEntity
		            .status(HttpStatus.PAYLOAD_TOO_LARGE)                 
		            .body(null);
		} else if ( result.size()==0) {
			return  ResponseEntity
		            .status(HttpStatus.NOT_FOUND)                 
		            .body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result.get(0));
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
