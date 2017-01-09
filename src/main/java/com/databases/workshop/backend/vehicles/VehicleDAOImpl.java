package com.databases.workshop.backend.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

  private JdbcTemplate template;

  @Autowired
  public VehicleDAOImpl(DataSource dataSource) {
    Assert.notNull(dataSource);
    template = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Vehicle> findAllVehicles() {
    String query = "SELECT * FROM VEHICLES";

    return template.query(query, ((rs, rowNum) ->
      new Vehicle(Integer.valueOf(rs.getString("VehicleID")), rs.getInt("ClientID"),
        rs.getInt("ModelID"), rs.getString("Brand"))));
  }

  @Override
  public void createVehicle(Vehicle vehicle) {

  }

  @Override
  public void updateVehicle(Vehicle newVehicle) {

  }

  @Override
  public void deleteVehicle(Integer id) {

  }

  @Override
  public Vehicle findVehicleByID(Integer id) {
    return null;
  }

  @Override
  public List<Vehicle> findVehiclesContains(String nameFilter) {
    return null;
  }
}
