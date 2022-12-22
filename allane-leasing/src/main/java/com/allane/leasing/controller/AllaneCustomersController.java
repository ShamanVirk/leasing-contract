package com.allane.leasing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.allane.leasing.api.CustomersApi;
import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;
import com.allane.leasing.service.AllaneCustomersService;

@RestController
public class AllaneCustomersController implements CustomersApi {
    
    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomersController.class);
    
    private AllaneCustomersService allaneCustomersService;
    
    public AllaneCustomersController(AllaneCustomersService allaneCustomersService) {
        this.allaneCustomersService = allaneCustomersService;
    }
    
    @Override
    public ResponseEntity<CustomerPageResponse> getAllCustomers(PageRequest page) {
        LOG.info("Enter getAllCustomers");
        CustomerPageResponse customers = allaneCustomersService.getAllCustomers(page);
        LOG.info("Exit getAllCustomers");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
