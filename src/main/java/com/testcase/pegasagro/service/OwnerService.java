package com.testcase.pegasagro.service;

import java.util.List;

import com.testcase.pegasagro.entity.Car;
import com.testcase.pegasagro.entity.Dealer;
import com.testcase.pegasagro.entity.Owner;

public interface OwnerService {
    Owner createOwner(Owner owner);

    Owner updateOwner(Owner owner);

    void deleteOwner(Long ownerId);

    Owner getOwnerById(Long id);

    Car assignCarToOwner(Long carId, Long ownerId);

    Car deleteCarFromOwner(Long carId, Long ownerId);

    Dealer removeOwnerFromDealer(Long ownerId, Long dealerId);

    Dealer addOwnerToDealer(Long ownerId, Long dealerId);

    List<Owner> getAllOwners();
}
