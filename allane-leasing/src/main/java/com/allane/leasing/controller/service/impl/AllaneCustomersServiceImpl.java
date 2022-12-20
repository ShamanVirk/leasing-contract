package com.allane.leasing.controller.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allane.leasing.controller.service.AllaneCustomersService;
import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;
import com.allane.leasing.persistent.entity.Customer;
import com.allane.leasing.persistent.repository.CustomerRepository;

@Service
public class AllaneCustomersServiceImpl implements AllaneCustomersService {
    

    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomersServiceImpl.class);
    
    private CustomerRepository customerRepository;
    
    public AllaneCustomersServiceImpl(CustomerRepository customerRepository) {
        customerRepository = customerRepository;
    }

    @Override
    public CustomerPageResponse getAllCustomers(PageRequest page) {
        LOG.info("Enter getAllCustomers");
        List<Customer> customers = customerRepository.findAll();
        CustomerPageResponse customerPageResponse = new CustomerPageResponse();
        customerPageResponse.setOverviewItems(customers);
        return customerPageResponse;
    }
}
