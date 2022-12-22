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

import com.allane.leasing.model.Customer;
import com.allane.leasing.service.AllaneCustomerService;
import com.allane.leasing.util.CustomerHelper;

@ExtendWith(MockitoExtension.class)
public class AllaneCustomerTest {

    @InjectMocks
    private AllaneCustomerController allaneCustomerController;
    
    @Mock
    private AllaneCustomerService allaneCustomerService;
    
    @Test
    void getAllCustomers() throws Exception {
        Customer newCustomer = CustomerHelper.createCustomer();
        doReturn(CustomerHelper.createCustomer()).when(allaneCustomerService).createCustomer(newCustomer);

        ResponseEntity<Customer> response = allaneCustomerController.createCustomer(newCustomer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(allaneCustomerService).createCustomer(newCustomer);
    }
}
