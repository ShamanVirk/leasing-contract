package com.allane.leasing.integration.util;

import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;

public class TestHelper {
    
    public static PageRequest getPageRequest() {
        return new PageRequest();
    }
    
    public static CustomerPageResponse getCustomerPageResponse() {
        return new CustomerPageResponse();
    }

}
