package pl.ene.springbootrestjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.ene.springbootrestjpa.domain.Customer;

/**
 * 
 * @author neugeeug
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByNameStartingWithIgnoreCase( String name);
	List<Customer> findByName(String name);

}
