package com.testcase.pegasagro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcase.pegasagro.entity.Car;
import com.testcase.pegasagro.entity.Owner;
import com.testcase.pegasagro.exception.ResourceNotFoundException;
import com.testcase.pegasagro.repository.CarRepository;
import com.testcase.pegasagro.repository.OwnerRepository;
import com.testcase.pegasagro.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car getCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        carRepository.delete(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarsByOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner != null) {
            return carRepository.findByOwner(owner);
        }
        return new ArrayList<>();
    }

    @Override
    public Car removeOwnerFromCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        car.setOwner(null);
        return carRepository.save(car);
    }

}
