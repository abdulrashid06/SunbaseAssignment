package com.sunbase.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sunbase.model.Customer;
import com.sunbase.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

//	@Query("SELECT c FROM Customer c WHERE c.firstName LIKE %?1% OR c.lastName LIKE %?1%")
//    List<User> findByName(String name);

}
