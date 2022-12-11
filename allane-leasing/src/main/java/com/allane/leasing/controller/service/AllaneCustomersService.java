package com.allane.leasing.controller.service;

import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;

public interface AllaneCustomersService {
    
    CustomerPageResponse getAllCustomers(PageRequest page);

}
