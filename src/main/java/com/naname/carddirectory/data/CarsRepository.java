package com.naname.carddirectory.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CarsRepository extends PagingAndSortingRepository<Car, String> {
    Page<Car> findCarsByBody(String type, Pageable pageable);

    @Query(value = "SELECT min(date) FROM car", nativeQuery = true)
    LocalDateTime findMinimalDate();

    @Query(value = "SELECT max(date) FROM car", nativeQuery = true)
    LocalDateTime findMaxDate();
}
