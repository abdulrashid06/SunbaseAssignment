package com.sunbase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbase.exception.DuplicateDataException;
import com.sunbase.exception.NoRecordFoundException;
import com.sunbase.model.Customer;
import com.sunbase.repository.CustomerRepository;
import com.sunbase.service.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Autowired
    private CustomerRepository customerRepository;
	
	
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
	        Customer cus = customerService.createCustomer(customer);
	        return new ResponseEntity<>(cus, HttpStatus.CREATED);
	   
	}
	
	
	
	@PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.ACCEPTED);
    }
	
	
	
	  @GetMapping("/customer/{id}")
	    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id) {
	        Optional<Customer> customer = customerService.getCustomerById(id);
	        return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.OK);
	    }

	  
	  
	  
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
	        customerService.deleteCustomer(id);
	        return new ResponseEntity<String>(HttpStatus.OK);
	    }

	    
	    
	    
	    @GetMapping("/getCustomerList")
	    public ResponseEntity<List<Customer>> getAllCustomers() {
	        List<Customer> customers = customerService.getAllCustomer();
	        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	    }

	    
	    
	    
	    @GetMapping("/search")
	    public List<Customer> searchCustomers(@RequestParam String searchType, @RequestParam String searchValue) {
	        return customerRepository.searchByType(searchType, searchValue);
	    }
	    
	    
	    
	    @GetMapping("/getCustomerListWithPagination")
	    public ResponseEntity<Page<Customer>> getAllCustomersWithPagination(@RequestParam(defaultValue = "0") int page) {
	        Page<Customer> customers = customerService.getAllCustomersWithPagination(page);
	        if (customers.isEmpty()) {
	            throw new NoRecordFoundException("Customer list is empty!");
	        }
	        return ResponseEntity.ok(customers);
	    }
	    
	    
	    
	    @PostMapping("/sync")
	    public ResponseEntity<?> syncCustomers(@RequestBody List<Customer> customers) {
	        customerService.syncCustomers(customers);
	        return ResponseEntity.ok().body("Customers synchronized successfully");
	    }

}
