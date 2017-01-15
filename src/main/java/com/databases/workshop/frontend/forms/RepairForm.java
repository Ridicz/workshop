package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.repair.Repair;
import com.databases.workshop.backend.repair.RepairDAO;
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
public class RepairForm extends EntityForm<Repair> {

  private TextField vehicleID = new MTextField("Vehicle ID");
  private TextField startDate = new MTextField("Start Date");
  private TextField endDate = new MTextField("End Date");
  private TextField price = new MTextField("Price");

  public RepairForm(RepairDAO repairDAO, EventBus.UIEventBus eventBus) {
    setSavedHandler(repair -> {
      repairDAO.createRepair(repair);
      eventBus.publish(this, new EntityModifiedEvent(repair));
    });
    setResetHandler(entity -> eventBus.publish(this, new EntityModifiedEvent(entity)));

    setSizeUndefined();
  }

  @Override
  protected Component createContent() {
    return new MVerticalLayout(
      new MFormLayout(
        vehicleID,
        startDate,
        endDate,
        price
      ).withWidth(""),
      getToolbar()
    ).withWidth("");
  }
}
