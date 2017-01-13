package com.databases.workshop.backend.vehicle;

import java.util.List;

public interface VehicleDAO {
  List<Vehicle> findAllVehicles();
  void createVehicle(Vehicle vehicle);
  void updateVehicle(Vehicle newVehicle);
  void deleteVehicle(Integer id);
  Vehicle findVehicleByID(Integer id);
  List<Vehicle> findVehiclesContains(String nameFilter);
}
