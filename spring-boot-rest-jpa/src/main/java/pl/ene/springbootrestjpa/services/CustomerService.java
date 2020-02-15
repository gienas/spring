package pl.ene.springbootrestjpa.services;

import org.springframework.stereotype.Service;
import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.domain.Item;
import pl.ene.springbootrestjpa.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public void addOrUpdateCustomer(Customer c) {		
		Customer saveCustomer = c;
		Customer existingCustomer =  checkIfCustomerAlreadyExists(c);
		if ( existingCustomer != null) {
			existingCustomer.getItems().clear();
			existingCustomer.getItems().addAll(c.getItems());
			existingCustomer.setName(c.getName());
			saveCustomer = existingCustomer;
		}
		
		//fix references
		for (Item i: saveCustomer.getItems()) {
			i.setCustomer(saveCustomer);
		}
		
		customerRepository.save(saveCustomer);
	}

	/**
	 * 
	 * @param customer
	 * @return managed object or null
	 */
	private Customer checkIfCustomerAlreadyExists(Customer customer) {
		Customer c = null;
		if (customer.getId() != null) {
			//c = customerRepository.findOne(customer.getId());
			c = customerRepository.findById(customer.getId()).get();
		} else if (customer.getName() != null) {
			c = customerRepository.findByName(customer.getName()).stream().findFirst().orElse(null);
		}
		return c;
	}

	
}
