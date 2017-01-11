package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.vehicles.Vehicle;
import com.databases.workshop.backend.vehicles.VehicleDAO;
import com.databases.workshop.frontend.events.EntityModifiedEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class VehicleForm extends EntityForm<Vehicle> {

  private TextField brand = new MTextField("Brand");
  private TextField clientID = new MTextField("Client ID");
  private TextField modelID = new MTextField("Model ID");

  public VehicleForm(VehicleDAO vehicleDAO, EventBus.UIEventBus eventBus) {
    setSavedHandler(vehicle -> {
      vehicleDAO.createVehicle(vehicle);
      eventBus.publish(this, new EntityModifiedEvent(vehicle));
    });
    setResetHandler(p -> eventBus.publish(this, new EntityModifiedEvent(p)));

    setSizeUndefined();
  }

  @Override
  protected Component createContent() {
    return new MVerticalLayout(
      new MFormLayout(
        brand,
        clientID,
        modelID
      ).withWidth(""),
      getToolbar()
    ).withWidth("");
  }
}
