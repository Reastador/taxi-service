package project.dao;

import project.model.Car;

import java.util.List;

public interface CarDao extends GenericDao<Car> {
    List<Car> getAllByDriver(Long driverId);
}
