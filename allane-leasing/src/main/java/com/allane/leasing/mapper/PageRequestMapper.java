package com.allane.leasing.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.allane.leasing.model.CustomerPageResponse;
import com.allane.leasing.model.CustomerPageResponse.SortEnum;
import com.allane.leasing.persistent.entity.CustomerEntity;


@Mapper
public interface PageRequestMapper {

    PageRequestMapper INSTANCE = Mappers.getMapper(PageRequestMapper.class);

    @Mapping(target = "sort", ignore = true)
    @Mapping(source = "page.content", target = "overviewItems")
    @Mapping(source = "page.totalElements", target = "numberOfItems")
    @Mapping(source = "page.totalPages", target = "numberOfPages")
    @Mapping(source = "page.number", target = "page")
    CustomerPageResponse toCustomerPageResponse(Page<CustomerEntity> page);

    @AfterMapping
    default void mapSort(Page<CustomerEntity> page, @MappingTarget CustomerPageResponse customerPageResponse) {
        customerPageResponse.setSort(
                page.getSort()
                .stream()
                .findFirst() // Assumption: Sorting is applied only on 1 column (lastName)
                .map(order -> SortEnum.valueOf(order.getDirection().name()))
                .orElse(SortEnum.UNSORTED)
                );
    }
}
