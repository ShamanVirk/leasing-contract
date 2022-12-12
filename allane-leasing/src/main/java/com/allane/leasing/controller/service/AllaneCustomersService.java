package com.allane.leasing.controller.service;

import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;

public interface AllaneCustomersService {
    
    /**
     * Retrieves all customers.
     *
     * @param page paged request to retrieve customers
     * @return CustomerPageResponse return the paged response for all customers
     */
    CustomerPageResponse getAllCustomers(PageRequest page);

}
