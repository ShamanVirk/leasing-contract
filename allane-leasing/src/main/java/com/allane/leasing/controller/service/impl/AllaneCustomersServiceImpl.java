package com.allane.leasing.controller.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.allane.leasing.controller.service.AllaneCustomersService;
import com.allane.leasing.mapper.PageRequestMapper;
import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.persistent.entity.CustomerEntity;
import com.allane.leasing.persistent.repository.CustomerRepository;

@Service
public class AllaneCustomersServiceImpl implements AllaneCustomersService {
    

    private static final Logger LOG = LoggerFactory.getLogger(AllaneCustomersServiceImpl.class);

    private PageRequestMapper pageRequestMapper = PageRequestMapper.INSTANCE;
    
    private CustomerRepository customerRepository;
    
    public AllaneCustomersServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @Override
    public CustomerPageResponse getAllCustomers(com.allane.leasing.model.PageRequest page) {
        LOG.debug("Enter getAllCustomers");
        final Pageable pageable = PageRequest.of(page.getPage(), page.getSize(), customerRepository.getSort(page.getSort()));
        Page<CustomerEntity> customers = customerRepository.findAll(pageable);
        LOG.debug("Found customers in db totalElements: {}", customers.getTotalElements());
        return pageRequestMapper.toCustomerPageResponse(customers);
    }
}
