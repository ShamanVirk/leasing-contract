package com.allane.leasing.controller.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allane.leasing.controller.service.AllaneCustomerService;
import com.allane.leasing.model.Customer;

@Service
public class AllaneCustomerServiceImpl implements AllaneCustomerService {
    
    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomerServiceImpl.class);

    @Override
    public Customer createCustomer(Customer customer) {
        LOG.info("Enter createCustomer");
        return customer;
    }

}
