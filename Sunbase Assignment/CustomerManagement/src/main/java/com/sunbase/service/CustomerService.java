package com.sunbase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.sunbase.model.Customer;

public interface CustomerService {
	
	public Customer createCustomer(Customer customer);
	
	public Customer updateCustomer(Long id, Customer customerDetails);

    public List<Customer> getAllCustomer();

    public Optional<Customer> getCustomerById(Long id);
    
    public List<Customer> getCustomerById(String name);

    public String deleteCustomer(Long id);
    
    public Page<Customer> getAllCustomersWithPagination(int page);

}
