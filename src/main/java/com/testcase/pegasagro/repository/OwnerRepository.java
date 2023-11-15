package com.testcase.pegasagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testcase.pegasagro.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
