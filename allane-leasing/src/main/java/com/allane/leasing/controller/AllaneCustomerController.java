package com.allane.leasing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.allane.leasing.api.CustomerApi;
import com.allane.leasing.model.Customer;
import com.allane.leasing.service.AllaneCustomerService;

@Controller
public class AllaneCustomerController implements CustomerApi {

    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomerController.class);

    private AllaneCustomerService allaneCustomerService;

    public AllaneCustomerController(AllaneCustomerService allaneCustomerService) {
        this.allaneCustomerService = allaneCustomerService;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        LOG.info("Create new customer");
        
        Customer newCustomer = allaneCustomerService.createCustomer(customer);
        
        LOG.info("New customer is created:\n {}", newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
    
}
