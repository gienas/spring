package pl.ene.springbootrestjpa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.repository.CustomerManualRespository;
import pl.ene.springbootrestjpa.repository.CustomerRepository;
import pl.ene.springbootrestjpa.services.CustomerService;

@RestController
public class CustomerController {

	private CustomerRepository customerRepository;
	private CustomerManualRespository customerManualRespository;
	private CustomerService service;
	
	public CustomerController(CustomerRepository userRepository, CustomerManualRespository customerManualRespository, CustomerService service) {
		// TODO Auto-generated constructor stub
		this.customerRepository = userRepository;
		this.customerManualRespository = customerManualRespository;
		this.service = service;
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

	@GetMapping(value={"/","/users"})
	public List<Customer> users() {
		List<Customer> result = new ArrayList<>();
		customerRepository.findAll().forEach(result::add);
		if (result.size() > 0 )  System.out.println("result greater than 0");
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

	
	@PostMapping({"/add", "/update"})
	public  ResponseEntity<?> addOrUpdateCustomer(@RequestBody Customer c) {
		if ( c == null ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
		try {
		service.addOrUpdateCustomer(c);
		} catch ( Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
	
}
