package com.testcase.pegasagro.service;

import java.util.List;

import com.testcase.pegasagro.entity.Dealer;

public interface DealerService {
    Dealer createDealer(Dealer dealer);

    Dealer getDealerById(Long dealerId);

    Dealer updateDealer(Dealer dealer);

    void deleteDealer(Long dealerId);

    List<Dealer> getAllDealers();

    List<Dealer> getDealersByOwner(Long ownerId);

    Dealer addOwnerToDealer(Long ownerId, Long dealerId);

    Dealer removeOwnerFromDealer(Long ownerId, Long dealerId);
}
