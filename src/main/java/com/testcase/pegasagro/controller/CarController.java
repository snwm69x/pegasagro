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
import com.testcase.pegasagro.service.CarService;
import com.testcase.pegasagro.service.OwnerService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return new ResponseEntity<>(carService.updateCar(car), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @PatchMapping("/{carId}/owner/{ownerId}")
    public ResponseEntity<Car> setOwnerOfCar(@PathVariable Long carId, @PathVariable Long ownerId) {
        return new ResponseEntity<>(ownerService.assignCarToOwner(carId, ownerId), HttpStatus.OK);
    }

    @PatchMapping("/{carId}/owner")
    public ResponseEntity<Car> removeOwnerFromCar(@PathVariable Long carId) {
        return new ResponseEntity<>(carService.removeOwnerFromCar(carId), HttpStatus.OK);
    }
}
