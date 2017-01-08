package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.mechanic.Mechanic;
import com.databases.workshop.backend.mechanic.MechanicDAO;
import com.databases.workshop.frontend.events.ClientModifiedEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class MechanicForm extends EntityForm<Mechanic> {

  private TextField firstName = new MTextField("First Name");
  private TextField lastName = new MTextField("Last Name");
  private TextField salary = new MTextField("Salary");
  private TextField age = new MTextField("Age");
  private TextField specialization = new MTextField("Specialization");

  public MechanicForm(MechanicDAO mechanicDAO, EventBus.UIEventBus eventBus) {
    setSavedHandler(mechanic -> {
      mechanicDAO.createMechanic(mechanic);
      eventBus.publish(this, new ClientModifiedEvent(mechanic));
    });
    setResetHandler(p -> eventBus.publish(this, new ClientModifiedEvent(p)));

    setSizeUndefined();
  }

  @Override
  protected Component createContent() {
    return new MVerticalLayout(
      new MFormLayout(
        firstName,
        lastName,
        salary,
        age,
        specialization
      ).withWidth(""),
      getToolbar()
    ).withWidth("");
  }
}
