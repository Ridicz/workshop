package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.vehicles.Vehicle;
import com.databases.workshop.backend.vehicles.VehicleDAO;
import com.databases.workshop.frontend.forms.EntityForm;
import com.databases.workshop.frontend.forms.VehicleForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class VehicleTable extends EntityTable<Vehicle> {

  VehicleForm vehicleForm;

  private VehicleDAO vehicleDAO;

  @Autowired
  public VehicleTable(VehicleDAO vehicleDAO, VehicleForm vehicleForm) {
    this.vehicleDAO = vehicleDAO;
    this.vehicleForm = vehicleForm;

    withProperties("id", "brand", "clientName");
    withColumnHeaders("ID", "Brand", "Client name");
    setSortableProperties("Brand");
    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("brand", 200);
    setColumnWidth("clientName", 300);
    setColumnAlignment("id", Table.Align.CENTER);
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Vehicle());
    setRows(vehicleDAO.findVehiclesContains(likeFilter));
  }

  @Override
  public EntityForm<Vehicle> getForm() {
    return vehicleForm;
  }

  @Override
  public void add() {
    edit(new Vehicle());
  }

  @Override
  public void edit(Vehicle vehicle) {
    vehicleForm.setEntity(vehicle);
    vehicleForm.openInModalPopup();
  }

  @Override
  public void remove() {
    vehicleDAO.deleteVehicle(getValue().getId());
    setValue(null);
    listEntities("");
  }
}
