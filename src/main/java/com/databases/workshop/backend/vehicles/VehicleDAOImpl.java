package com.databases.workshop.backend.vehicles;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.client.ClientDAO;
import com.databases.workshop.backend.model.Model;
import com.databases.workshop.backend.model.ModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

  private JdbcTemplate template;

  private ClientDAO clientDAO;
  private ModelDAO modelDAO;

  @Autowired
  public VehicleDAOImpl(DataSource dataSource, ClientDAO clientDAO, ModelDAO modelDAO) {
    Assert.notNull(dataSource);
    template = new JdbcTemplate(dataSource);
    this.clientDAO = clientDAO;
    this.modelDAO = modelDAO;
  }

  @Override
  public List<Vehicle> findAllVehicles() {
    String query = "SELECT * FROM VEHICLES";

    return template.query(query, ((rs, rowNum) ->
      new Vehicle(Integer.valueOf(rs.getString("VehicleID")), rs.getInt("ClientID"),
        rs.getInt("ModelID"), rs.getString("Brand"), getClientName(rs.getInt("ClientID")),
        getModelVersion(rs.getInt("ModelID")))));
  }

  @Override
  public void createVehicle(Vehicle vehicle) {
    if (findVehicleByID(vehicle.getId()) != null) {
      updateVehicle(vehicle);
      return;
    }

    String query = "INSERT INTO VEHICLES (ClientID, ModelID, Brand) VALUES (?, ?, ?)";

    template.update(query, vehicle.getClientID(), vehicle.getModelID(), vehicle.getBrand());
  }

  @Override
  public void updateVehicle(Vehicle newVehicle) {
    String query = "UPDATE VEHICLES SET ClientID=?, ModelID=?, Brand=? WHERE VehicleID=?";

    template.update(query, newVehicle.getClientID(), newVehicle.getModelID(), newVehicle.getBrand(), newVehicle.getId());
  }

  @Override
  public void deleteVehicle(Integer id) {
    String query = "DELETE FROM VEHICLES WHERE VehicleID=?";

    template.update(query, id);

  }

  @Override
  public Vehicle findVehicleByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM VEHICLES WHERE VehicleID=?";

    return template.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
      new Vehicle(rs.getInt("VehicleID"), rs.getInt("ClientId"), rs.getInt("ModelID"),
        rs.getString("Brand"), getClientName(rs.getInt("ClientID")),
        getModelVersion(rs.getInt("ModelID"))));
  }

  @Override
  public List<Vehicle> findVehiclesContains(String nameFilter) {
    String query = "SELECT * FROM VEHICLES WHERE Brand LIKE ?";

    return template.query(query, new Object[]{nameFilter}, (rs, rowNum) ->
      new Vehicle(rs.getInt("VehicleID"), rs.getInt("ClientID"),
        rs.getInt("ModelID"), rs.getString("Brand"), getModel()
        getClientName(rs.getInt("ClientID")), getModelVersion(rs.getInt("ModelID"))));
  }

  private String getClientName(Integer clientID) {
    Client client = clientDAO.findClientByID(clientID);
    return client.getFirstName() + " " + client.getLastName();
  }

  private String getModelVersion(Integer id) {
    Model model = modelDAO.findModelByID(id);
    return model.getVersion();
  }

  private String getModel(Integer id) {
    Model model = modelDAO.findModelByID(id);
    return model.getModel();
  }
}
