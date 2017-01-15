package com.databases.workshop.backend.repair;

import com.databases.workshop.backend.vehicle.Vehicle;
import com.databases.workshop.backend.vehicle.VehicleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RepairDAOImpl implements RepairDAO {

  private JdbcTemplate template;

  private VehicleDAO vehicleDAO;

  @Autowired
  public RepairDAOImpl(DataSource dataSource, VehicleDAO vehicleDAO) {
    template = new JdbcTemplate(dataSource);
    this.vehicleDAO = vehicleDAO;
  }

  @Override
  public List<Repair> findAllRepairs() {
    String query = "SELECT * FROM REPAIRS";

    return template.query(query, ((rs, rowNum) ->
      new Repair(rs.getInt("RepairID"), rs.getInt("VehicleID"), rs.getString("StartDate"),
        rs.getString("EndDate"), rs.getDouble("Price"), getVehicleBrand(rs.getInt("VehicleID")))));
  }

  @Override
  public void createRepair(Repair repair) {
    if (findRepairByID(repair.getId()) != null) {
      updateRepair(repair);
      return;
    }

    String query = "INSERT INTO REPAIRS (VehicleID, StartDate, EndDate, Price) VALUES (?, ?, ?, ?)";

    template.update(query, repair.getVehicleID(), repair.getStartDate(), repair.getEndDate(), repair.getPrice());
  }

  @Override
  public void updateRepair(Repair repair) {
    String query = "UPDATE REPAIRS SET VehicleID=?, StartDate=?, EndDate=?, Price=? WHERE RepairID=?";

    template.update(query, repair.getVehicleID(), repair.getStartDate(), repair.getEndDate(),
      repair.getPrice(), repair.getId());
  }

  @Override
  public void deleteRepair(Integer id) {
    String query = "DELETE FROM REPAIRS WHERE RepairID=?";

    template.update(query, id);
  }

  @Override
  public Repair findRepairByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM REPAIRS WHERE RepairID=?";

    return template.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
      new Repair(id, rs.getInt("VehicleID"), rs.getString("StartDate"),
        rs.getString("EndDate"), rs.getDouble("Price"),
        getVehicleBrand(rs.getInt("VehicleID"))));
  }

  @Override
  public List<Repair> findRepairsContains(String nameFilter) {
    return findAllRepairs();
  }

  private String getVehicleBrand(Integer id) {
    Vehicle vehicle = vehicleDAO.findVehicleByID(id);
    return vehicle.getBrand() + " " + vehicle.getModel();
  }
}
