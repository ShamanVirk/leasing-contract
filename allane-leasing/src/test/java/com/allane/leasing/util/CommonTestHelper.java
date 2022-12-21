package com.allane.leasing.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CommonTestHelper {
    
    /**
     * Strict comparison by stringifying both, expected and actual object and compare.
     */
    public static void assertStringifyEquals(Object expected, Object actual) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        assertEquals(objectMapper.writeValueAsString(actual), objectMapper.writeValueAsString(expected));
    }

}
