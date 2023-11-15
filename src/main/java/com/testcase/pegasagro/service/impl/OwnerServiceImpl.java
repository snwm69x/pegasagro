package com.testcase.pegasagro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcase.pegasagro.entity.Car;
import com.testcase.pegasagro.entity.Dealer;
import com.testcase.pegasagro.entity.Owner;
import com.testcase.pegasagro.exception.ResourceNotFoundException;
import com.testcase.pegasagro.repository.CarRepository;
import com.testcase.pegasagro.repository.DealerRepository;
import com.testcase.pegasagro.repository.OwnerRepository;
import com.testcase.pegasagro.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DealerRepository dealerRepository;

    @Override
    public Owner createOwner(Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return savedOwner;
    }

    @Override
    public Owner updateOwner(Owner owner) {
        Owner updatedOwner = ownerRepository.save(owner);
        return updatedOwner;
    }

    @Override
    public void deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        ownerRepository.delete(owner);
    }

    @Override
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
    }

    @Override
    public Car assignCarToOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        car.setOwner(owner);
        return carRepository.save(car);
    }

    @Override
    public Dealer removeOwnerFromDealer(Long ownerId, Long dealerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id " + dealerId));
        dealer.getOwners().remove(owner);
        return dealerRepository.save(dealer);
    }

    @Override
    public Car deleteCarFromOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        if (car.getOwner().equals(owner)) {
            car.setOwner(null);
            return carRepository.save(car);
        }
        throw new IllegalStateException("Car does not belong to the owner");
    }

    @Override
    public Dealer addOwnerToDealer(Long ownerId, Long dealerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id " + dealerId));
        dealer.getOwners().add(owner);
        return dealerRepository.save(dealer);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

}
