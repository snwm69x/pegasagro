package com.testcase.pegasagro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testcase.pegasagro.entity.Car;
import com.testcase.pegasagro.entity.Dealer;
import com.testcase.pegasagro.entity.Owner;
import com.testcase.pegasagro.service.DealerService;
import com.testcase.pegasagro.service.OwnerService;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private DealerService dealerService;

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.createOwner(owner), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        owner.setId(id);
        return new ResponseEntity<>(ownerService.updateOwner(owner), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return new ResponseEntity<>(ownerService.getOwnerById(id), HttpStatus.OK);
    }

    @PatchMapping("/{ownerId}/car/{carId}")
    public ResponseEntity<Car> assignCarToOwner(@PathVariable Long carId, @PathVariable Long ownerId) {
        return new ResponseEntity<>(ownerService.assignCarToOwner(carId, ownerId), HttpStatus.OK);
    }

    @DeleteMapping("/{ownerId}/car/{carId}")
    public ResponseEntity<Car> deleteCarFromOwner(@PathVariable Long carId, @PathVariable Long ownerId) {
        return new ResponseEntity<>(ownerService.deleteCarFromOwner(carId, ownerId), HttpStatus.OK);
    }

    @PatchMapping("/{ownerId}/dealer/{dealerId}")
    public ResponseEntity<Dealer> addOwnerToDealer(@PathVariable Long ownerId, @PathVariable Long dealerId) {
        return new ResponseEntity<>(dealerService.addOwnerToDealer(ownerId, dealerId), HttpStatus.OK);
    }

    @DeleteMapping("/{ownerId}/dealer/{dealerId}")
    public ResponseEntity<Dealer> removeOwnerFromDealer(@PathVariable Long ownerId, @PathVariable Long dealerId) {
        return new ResponseEntity<>(ownerService.removeOwnerFromDealer(ownerId, dealerId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }
}
