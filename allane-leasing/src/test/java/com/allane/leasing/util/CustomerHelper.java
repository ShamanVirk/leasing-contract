package com.allane.leasing.util;

import java.time.LocalDate;

import com.allane.leasing.model.Customer;
import com.allane.leasing.persistent.entity.CustomerEntity;

public class CustomerHelper {

    public static Customer createCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setBirthDate(LocalDate.parse("1989-01-01"));
        newCustomer.setFirstName("Allane");
        newCustomer.setLastName("Leasing");
        newCustomer.setId(10l);
        return newCustomer;
    }
    
    public static CustomerEntity createCustomerEntity() {
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setBirthDate(LocalDate.parse("1989-01-01"));
        newCustomer.setFirstName("Allane");
        newCustomer.setLastName("Leasing");
        newCustomer.setId(10l);
        return newCustomer;
    }
}
