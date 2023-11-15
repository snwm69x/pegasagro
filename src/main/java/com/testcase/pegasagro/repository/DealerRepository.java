package com.testcase.pegasagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testcase.pegasagro.entity.Dealer;
import java.util.List;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    List<Dealer> findByOwners_Id(Long ownerId);
}
