package com.allane.leasing.controller.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allane.leasing.controller.service.AllaneCustomersService;
import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;

@Service
public class AllaneCustomersServiceImpl implements AllaneCustomersService {
    
    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomersServiceImpl.class);
    
    @Override
    public CustomerPageResponse getAllCustomers(PageRequest page) {
        LOG.info("Enter getAllCustomers");
        return new CustomerPageResponse();
    }
}
