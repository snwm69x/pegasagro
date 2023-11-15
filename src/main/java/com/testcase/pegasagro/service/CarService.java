package com.testcase.pegasagro.service;

import java.util.List;

import com.testcase.pegasagro.entity.Car;

public interface CarService {
    Car createCar(Car car);

    Car getCarById(Long carId);

    Car updateCar(Car car);

    void deleteCar(Long carId);

    List<Car> getAllCars();

    List<Car> getCarsByOwner(Long ownerId);

    Car removeOwnerFromCar(Long carId);
}
