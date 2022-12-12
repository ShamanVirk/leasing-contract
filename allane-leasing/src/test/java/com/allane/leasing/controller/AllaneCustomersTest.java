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
import com.allane.leasing.integration.util.TestHelper;
import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.PageRequest;

@ExtendWith(MockitoExtension.class)
public class AllaneCustomersTest {

    @InjectMocks
    private AllaneCustomersController allaneCustomersController;
    
    @Mock
    private AllaneCustomersService allaneCustomersService;
    
    @Test
    void getAllCustomers() throws Exception {
        PageRequest testPageRequest = TestHelper.getPageRequest();
        doReturn(TestHelper.getCustomerPageResponse()).when(allaneCustomersService).getAllCustomers(testPageRequest);

        ResponseEntity<CustomerPageResponse> response = allaneCustomersController.getAllCustomers(TestHelper.getPageRequest());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(allaneCustomersService).getAllCustomers(testPageRequest);
    }
}
