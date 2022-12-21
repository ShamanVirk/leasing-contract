package com.allane.leasing.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.persistent.entity.CustomerEntity;
import com.allane.leasing.persistent.repository.CustomerRepository;
import com.allane.leasing.util.CustomerHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
@Transactional
class AllaneCustomersIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @DisplayName("TestCase: Get all customers - pagination")
    @ParameterizedTest(name = "Given page: {0} and size: {1} then expect numberOfItemsOnCurrentPage: {2}, numberOfItems:{3}, numberOfPages: {4}")
    @CsvSource({
        "0, 5, 5, 5, 1",
        "1, 5, 5, 10, 2"
    })
    void getAllCustomers(String page, String size, int numberOfItemsOnCurrentPage, int numberOfItems, int numberOfPages) throws Exception {
        for (int i = 0; i < numberOfItems; i++) {
            createCustomerEntity();
        }

        MvcResult mvcResult = mockMvc.perform(
                get("/customers")
                .param("page", page)
                .param("size", size))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        // validate pagination
        String responseBody = mvcResult.getResponse().getContentAsString();
        CustomerPageResponse customerPageResponse = new ObjectMapper().registerModule(new JavaTimeModule())
                .readerFor(new TypeReference<CustomerPageResponse>() {
                }).readValue(responseBody);
        assertEquals(numberOfItemsOnCurrentPage, customerPageResponse.getOverviewItems().size());
        assertEquals(numberOfItems, customerPageResponse.getNumberOfItems());
        assertEquals(numberOfPages, customerPageResponse.getNumberOfPages());
    }

    /*
     * Private methods below are helpers methods for AllaneCustomersIntegrationTests only
     * */
    private CustomerEntity createCustomerEntity() {
        CustomerEntity newCustomer = CustomerHelper.createCustomerEntity();
        return customerRepository.save(newCustomer);
    }
}
