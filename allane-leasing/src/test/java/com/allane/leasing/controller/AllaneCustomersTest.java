package com.allane.leasing.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.allane.leasing.controller.service.AllaneCustomersService;
import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;
import com.allane.leasing.util.CustomersHelper;

@ExtendWith(MockitoExtension.class)
public class AllaneCustomersTest {

    @InjectMocks
    private AllaneCustomersController allaneCustomersController;
    
    @Mock
    private AllaneCustomersService allaneCustomersService;
    
    @Test
    void getAllCustomers() throws Exception {
        PageRequest testPageRequest = CustomersHelper.getPageRequest();
        doReturn(CustomersHelper.getCustomerPageResponse()).when(allaneCustomersService).getAllCustomers(testPageRequest);

        ResponseEntity<CustomerPageResponse> response = allaneCustomersController.getAllCustomers(CustomersHelper.getPageRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(allaneCustomersService).getAllCustomers(testPageRequest);
    }
}
