package com.sunbase.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunbase.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByEmail(String email);
	
	Optional<Customer> findByUuid(String uuid);
	
	Page<Customer> findAll(Pageable pageable);

	@Query("SELECT c FROM Customer c WHERE " +
	           "(:searchType = 'name' AND (c.first_name LIKE %:searchValue% OR c.last_name LIKE %:searchValue%)) OR " +
	           "(:searchType = 'city' AND c.city LIKE %:searchValue%) OR " +
	           "(:searchType = 'state' AND c.state LIKE %:searchValue%) OR " +
	           "(:searchType = 'email' AND c.email LIKE %:searchValue%)")
	    List<Customer> searchByType(@Param("searchType") String searchType, @Param("searchValue") String searchValue);


}
