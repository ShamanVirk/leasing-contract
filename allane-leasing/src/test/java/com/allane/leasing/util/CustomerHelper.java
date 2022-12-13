package com.allane.leasing.util;

import java.time.LocalDate;

import com.allane.leasing.model.Customer;

public class CustomerHelper {

    public static Customer createCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setBirthDate(LocalDate.parse("1989-01-01"));
        newCustomer.setFirstName("Allane");
        newCustomer.setLastName("Leasing");
        newCustomer.setId(10l);
        return newCustomer;
    }
}
