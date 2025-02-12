package com.allane.leasing.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allane.leasing.mapper.CustomerMapper;
import com.allane.leasing.model.Customer;
import com.allane.leasing.persistent.entity.CustomerEntity;
import com.allane.leasing.persistent.repository.CustomerRepository;
import com.allane.leasing.service.AllaneCustomerService;

@Service
public class AllaneCustomerServiceImpl implements AllaneCustomerService {
    
    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomerServiceImpl.class);
    
    private CustomerRepository customerRepository;
    
    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    
    public AllaneCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        LOG.debug("Enter createCustomer");
        CustomerEntity customerEntity = customerRepository.save(customerMapper.toCustomerDao(customer));
        LOG.debug("Exit createCustomer");
        return customerMapper.toCustomerDto(customerEntity);
    }

}
