package com.allane.leasing.util;

import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;

public class CustomersHelper {
    
    public static PageRequest getPageRequest() {
        return new PageRequest();
    }
    
    public static CustomerPageResponse getCustomerPageResponse() {
        return new CustomerPageResponse();
    }

}
