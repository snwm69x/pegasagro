package com.testcase.pegasagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testcase.pegasagro.entity.Car;
import com.testcase.pegasagro.entity.Owner;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByOwner(Owner owner);
}
