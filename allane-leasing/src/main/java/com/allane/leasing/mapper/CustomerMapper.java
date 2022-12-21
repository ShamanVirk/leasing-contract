package com.allane.leasing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.allane.leasing.model.Customer;
import com.allane.leasing.persistent.entity.CustomerEntity;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toCustomerDao(Customer customer);
    
    Customer toCustomerDto(CustomerEntity customer);

}
