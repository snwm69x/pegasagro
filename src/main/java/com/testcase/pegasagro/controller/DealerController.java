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

import com.testcase.pegasagro.entity.Dealer;
import com.testcase.pegasagro.service.DealerService;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @PostMapping
    public ResponseEntity<Dealer> createDealer(@RequestBody Dealer dealer) {
        return new ResponseEntity<>(dealerService.createDealer(dealer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable Long id) {
        return new ResponseEntity<>(dealerService.getDealerById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Long id, @RequestBody Dealer dealer) {
        dealer.setId(id);
        return new ResponseEntity<>(dealerService.updateDealer(dealer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable Long id) {
        dealerService.deleteDealer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Dealer>> getAllDealers() {
        return new ResponseEntity<>(dealerService.getAllDealers(), HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Dealer>> getDealersByOwner(@PathVariable Long ownerId) {
        return new ResponseEntity<>(dealerService.getDealersByOwner(ownerId), HttpStatus.OK);
    }

    @PatchMapping("/{dealerId}/owner/{ownerId}")
    public ResponseEntity<Dealer> addOwnerToDealer(@PathVariable Long ownerId, @PathVariable Long dealerId) {
        return new ResponseEntity<>(dealerService.addOwnerToDealer(ownerId, dealerId), HttpStatus.OK);
    }

    @DeleteMapping("/{dealerId}/owner/{ownerId}")
    public ResponseEntity<Dealer> removeOwnerFromDealer(@PathVariable Long ownerId, @PathVariable Long dealerId) {
        return new ResponseEntity<>(dealerService.removeOwnerFromDealer(ownerId, dealerId), HttpStatus.OK);
    }
}
