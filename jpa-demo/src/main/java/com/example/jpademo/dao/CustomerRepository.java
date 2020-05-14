package com.example.jpademo.dao;

import com.example.jpademo.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lasName);

    Optional<Customer> findById(Long id);
}
