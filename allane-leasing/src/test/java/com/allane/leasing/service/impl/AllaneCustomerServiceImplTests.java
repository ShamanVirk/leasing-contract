package com.allane.leasing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.allane.leasing.mapper.CustomerMapper;
import com.allane.leasing.model.Customer;
import com.allane.leasing.persistent.entity.CustomerEntity;
import com.allane.leasing.persistent.repository.CustomerRepository;
import com.allane.leasing.service.impl.AllaneCustomerServiceImpl;
import com.allane.leasing.util.CommonTestHelper;
import com.allane.leasing.util.CustomerHelper;

@ExtendWith(MockitoExtension.class)
class AllaneCustomerServiceImplTests {
    
    private AllaneCustomerServiceImpl allaneCustomerService;
    
    @Mock
    private CustomerRepository customerRepository;
    
    @Mock
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    
    @BeforeEach
    void setup() {
        allaneCustomerService = new AllaneCustomerServiceImpl(customerRepository);
        ReflectionTestUtils.setField(allaneCustomerService, "customerRepository", customerRepository);
    }
    
    @Test
    void testCreateWebhook() throws Exception {
        Customer customer = CustomerHelper.createCustomer();
        CustomerEntity expectedCustomerEntity = CustomerHelper.createCustomerEntity();
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(expectedCustomerEntity);
        
        Customer returnedValue = allaneCustomerService.createCustomer(customer);

        ArgumentCaptor<CustomerEntity> actualCustomerEntity = ArgumentCaptor.forClass(CustomerEntity.class);
        verify(customerRepository, times(1)).save(actualCustomerEntity.capture());
        assertEquals(expectedCustomerEntity, actualCustomerEntity.getValue());
        CommonTestHelper.assertStringifyEquals(expectedCustomerEntity, returnedValue);
    }

}
