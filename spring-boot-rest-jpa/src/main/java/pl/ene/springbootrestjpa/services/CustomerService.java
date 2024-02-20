package pl.ene.springbootrestjpa.services;

import org.springframework.stereotype.Service;
import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.domain.Item;
import pl.ene.springbootrestjpa.repository.CustomerRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    public Customer updateCustomer(Customer c) {
        Customer saveCustomer = c;
        Customer existingCustomer = null;

        existingCustomer = checkIfCustomerAlreadyExists(c.getId()); //throws NoSuchElementException
        existingCustomer.getItems().clear();
        existingCustomer.getItems().addAll(c.getItems());
        existingCustomer.setName(c.getName());
        saveCustomer = existingCustomer;
        //fix references
        for (Item i : saveCustomer.getItems()) {
            i.setCustomer(saveCustomer);
        }

        return customerRepository.save(saveCustomer);
    }

    public Customer addCustomer(Customer c) {
        Customer saveCustomer = c;
        Customer existingCustomer = checkIfCustomerAlreadyExists(c);
        if (existingCustomer != null) {
            throw new EntityExistsException("Customer already exists" + c.toString());
        }
        saveCustomer = c;
        for (Item i : saveCustomer.getItems()) {
            i.setCustomer(saveCustomer);
        }

        return customerRepository.save(saveCustomer);
    }

    /**
     * @param customer
     * @return managed object or null
     */
    private Customer checkIfCustomerAlreadyExists(Customer customer) {
        Customer c = null;
        if (customer.getId() != null) {
            //c = customerRepository.findOne(customer.getId());
            try {
                c = customerRepository.findById(customer.getId()).get();
            } catch (NoSuchElementException e) {
            }
        } else if (customer.getName() != null) {
            c = customerRepository.findByName(customer.getName()).stream().findFirst().orElse(null);
        }
        return c;
    }

    public Customer checkIfCustomerAlreadyExists(Long id) throws EntityNotFoundException {

        return customerRepository.findById(id).get();
    }


}
