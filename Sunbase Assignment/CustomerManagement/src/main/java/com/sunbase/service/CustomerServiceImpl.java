package com.sunbase.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sunbase.exception.DuplicateDataException;
import com.sunbase.exception.NoRecordFoundException;
import com.sunbase.model.Customer;
import com.sunbase.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	
	
	@Override
	public Customer createCustomer(Customer customer) {
		if (customerRepository.findByUuid(customer.getUuid()).isPresent()) {
	        throw new DuplicateDataException("Customer with this UUID already exists!");
	    }
	    if (customer.getUuid() == null) {
	        customer.setUuid(UUID.randomUUID().toString());
	    } else {
	        customer.formatUuid(); // Format the UUID if it's not in the correct format
	    }
	    return customerRepository.save(customer);
	}

	
	@Override
	public Customer updateCustomer(Long id, Customer customerDetails) {
		Customer customer = customerRepository.findById(id)
		        .orElseThrow(() -> new NoRecordFoundException("Customer not found with id " + id));

		if (customerDetails.getFirst_name() != null) {
		    customer.setFirst_name(customerDetails.getFirst_name());
		}
		if (customerDetails.getLast_name() != null) {
		    customer.setLast_name(customerDetails.getLast_name());
		}
		if (customerDetails.getStreet() != null) {
		    customer.setStreet(customerDetails.getStreet());
		}
		if (customerDetails.getAddress() != null) {
		    customer.setAddress(customerDetails.getAddress());
		}
		if (customerDetails.getCity() != null) {
		    customer.setCity(customerDetails.getCity());
		}
		if (customerDetails.getState() != null) {
		    customer.setState(customerDetails.getState());
		}
		    
		if (customerDetails.getPhone() != null) {
		    customer.setPhone(customerDetails.getPhone());
		}

	    return customerRepository.save(customer);
	}

	
	@Override
	public Optional<Customer> getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		
		if(customer.isEmpty()) {
			throw new NoRecordFoundException("Customer not found with given id "+id);
		}
		
		return customer;
	}

	
	@Override
	public String deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
		
		if(customer.isEmpty()) {
			throw new NoRecordFoundException("Customer not found with given id "+id);
		}
		
		customerRepository.deleteById(id);
		
		return "Customer deleted successfully";
	}

	
	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customerList = customerRepository.findAll();
		
		if(customerList.isEmpty()) {
			throw new NoRecordFoundException("Customer list is empty!");
		}
		
		return customerList;
	}

	
	@Override
	public List<Customer> getCustomerById(String name) {
//		List<Customer> customer = customerRepository.findByName(name);
//		
//		if(customer.isEmpty()) {
//			throw new NoRecordFoundException("Customer not found");
//		}
		
		return null;
	}


	@Override
	public Page<Customer> getAllCustomersWithPagination(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return customerRepository.findAll(pageable);
    }
	
	
	public void syncCustomers(List<Customer> customers) {
	    for (Customer customer : customers) {
	        Optional<Customer> existingCustomerOptional = customerRepository.findByUuid(customer.getUuid());

	        if (existingCustomerOptional.isPresent()) {
	            // Retrieve the existing Customer object
	            Customer existingCustomer = existingCustomerOptional.get();
	            // Update the existing Customer fields with new values
	            existingCustomer.setFirst_name(customer.getFirst_name());
	            existingCustomer.setLast_name(customer.getLast_name());
	            existingCustomer.setStreet(customer.getStreet());
	            existingCustomer.setAddress(customer.getAddress());
	            existingCustomer.setCity(customer.getCity());
	            existingCustomer.setState(customer.getState());
	            existingCustomer.setPhone(customer.getPhone());
	            // Save the updated Customer object
	            customerRepository.save(existingCustomer);
	        } else {
	            // Set UUID if it's not already set
	            if (customer.getUuid() == null) {
	                customer.setUuid(UUID.randomUUID().toString());
	            }
	            // Save the new Customer object
	            customerRepository.save(customer);
	        }
	    }
	}


}
