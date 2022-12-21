package com.allane.leasing.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allane.leasing.persistent.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
