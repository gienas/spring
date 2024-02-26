package pl.ene.springbootrestjpa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.ene.springbootrestjpa.domain.Customer;
import pl.ene.springbootrestjpa.domain.ReturnBody;
import pl.ene.springbootrestjpa.repository.CustomerRepository;
import pl.ene.springbootrestjpa.services.CustomerService;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private CustomerRepository customerRepository;
    //private CustomerManualRespository customerManualRespository;
    private CustomerService service;

    public CustomerController(CustomerRepository userRepository, CustomerService service) {
        // TODO Auto-generated constructor stub
        this.customerRepository = userRepository;
        //this.customerManualRespository = customerManualRespository;
        this.service = service;
    }

    /**
     * Get user by name
     *
     * @param name full name
     * @return //TODO
     */
    @Operation(summary = "Get customer by name")
    @GetMapping("/{name}")
    ResponseEntity<Customer> getUser(@PathVariable(name = "name") String name) {
        List<Customer> result = customerRepository.findByName(name);
        if (result.size() > 1) {
            return ResponseEntity
                    .status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body(null);
        } else if (result.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get(0));
    }

    @Operation(summary = "List all customers")
    @GetMapping()
    public List<Customer> users() {
        List<Customer> result = new ArrayList<>();
        customerRepository.findAll().forEach(result::add);
        if (result.size() > 0) System.out.println("result greater than 0");
        return result;
    }
    @Operation(summary = "Find customer by name / ignore case")
    @GetMapping("/ignore-case")
    public List<Customer> find(@RequestParam String name) {
        List<Customer> result = new ArrayList<>();
        customerRepository.findByNameStartingWithIgnoreCase(name).forEach(result::add);
        return result;
    }

    @Operation(summary = "Add a new customer")
    @PostMapping()
    public ResponseEntity<?> addCustomer(@RequestBody Customer c) {
        Customer added = null;
        if (c == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        try {
            added = service.addCustomer(c);
        }
        catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new ReturnBody("Object already exists", "Consider to use PUT method if you need to update"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }

    @Operation(summary = "Delete customer by name")
    @DeleteMapping({"/byName/{name}"})
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "name") String name) {

        List<Customer> result = customerRepository.findByName(name);
        if (result.size() > 1) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ReturnBody("Not unique entity", ""));
        } else if (result.size() == 0) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        Customer c = result.get(0);
        customerRepository.delete(c);
        return ResponseEntity.status(HttpStatus.OK).body(new ReturnBody("Deleted successfully", ""));
    }

    @Operation(summary = "Delete customer by id")
    @DeleteMapping({"/byId/{id}"})
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnBody("Not found", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ReturnBody("Deleted successfully", ""));
    }

    @Operation(summary = "Update a customer")
    @PutMapping()
    public ResponseEntity<?> updateCustomer(@RequestBody Customer c) {
        Customer updated = null;
        if (c.getId() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ReturnBody("Id required", ""));
        try {
            updated = service.updateCustomer(c);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnBody("Didn't find object to be updated Id " + c.getId(), "Consider to use POST method for adding a new object") );
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


}
