package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.part.Part;
import com.databases.workshop.backend.part.PartDAO;
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
public class PartForm extends EntityForm<Part> {

  private TextField partName = new MTextField("Part Name");
  private TextField manufacturer = new MTextField("Manufacturer");
  private TextField modelID = new MTextField("Model ID");
  private TextField price = new MTextField("Price");
  private TextField availability = new MTextField("Availability");

  public PartForm(PartDAO partDAO, EventBus.UIEventBus eventBus) {
    setSavedHandler(part -> {
      partDAO.createPart(part);
      eventBus.publish(this, new EntityModifiedEvent(part));
    });
    setResetHandler(entity -> eventBus.publish(this, new EntityModifiedEvent(entity)));

    setSizeUndefined();
  }

  @Override
  protected Component createContent() {
    return new MVerticalLayout(
      new MFormLayout(
        partName,
        manufacturer,
        modelID,
        price,
        availability
      ).withWidth(""),
      getToolbar()
    ).withWidth("");
  }
}
