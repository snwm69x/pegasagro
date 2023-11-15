package com.testcase.pegasagro.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcase.pegasagro.entity.Dealer;
import com.testcase.pegasagro.entity.Owner;
import com.testcase.pegasagro.exception.ResourceNotFoundException;
import com.testcase.pegasagro.repository.DealerRepository;
import com.testcase.pegasagro.repository.OwnerRepository;
import com.testcase.pegasagro.service.DealerService;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Dealer createDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer getDealerById(Long dealerId) {
        return dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id " + dealerId));
    }

    @Override
    public Dealer updateDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Override
    public void deleteDealer(Long dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id " + dealerId));
        dealerRepository.delete(dealer);
    }

    @Override
    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    @Override
    public List<Dealer> getDealersByOwner(Long ownerId) {
        return dealerRepository.findByOwners_Id(ownerId);
    }

    @Override
    public Dealer addOwnerToDealer(Long ownerId, Long dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id " + dealerId));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        dealer.getOwners().add(owner);
        owner.setDealer(dealer);
        ownerRepository.save(owner);
        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer removeOwnerFromDealer(Long ownerId, Long dealerId) {
        Dealer dealer = dealerRepository.findById(dealerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dealer not found with id " + dealerId));
        dealer.setOwners(dealer.getOwners().stream()
                .filter(owner -> !owner.getId().equals(ownerId))
                .collect(Collectors.toList()));
        return dealerRepository.save(dealer);
    }

}
