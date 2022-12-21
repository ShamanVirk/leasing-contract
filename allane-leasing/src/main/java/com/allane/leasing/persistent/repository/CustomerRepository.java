package com.allane.leasing.persistent.repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allane.leasing.model.PageRequest.SortEnum;
import com.allane.leasing.persistent.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Page<CustomerEntity> findAll(Pageable pageable);

    default Sort getSort(SortEnum sortEnum) {
        if (Objects.isNull(sortEnum) || sortEnum.equals(SortEnum.UNSORTED)) {
            return Sort.by(Collections.emptyList());
        }
        // Assumption: Sorting is applied only on 1 column (lastName)
        return Sort.by(Arrays.asList(new Order(Sort.Direction.valueOf(sortEnum.toString()), "lastName")));
    }

}
