package pl.ene.springbootrestjpa.repository;

import java.util.List;

import pl.ene.springbootrestjpa.domain.Customer;

public interface  CustomerManualRespository {

	List<Customer> getByName(String name);

}