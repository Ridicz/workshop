package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.part.Part;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.fields.MTextField;

@UIScope
@SpringComponent
public class PartForm extends EntityForm<Part> {

  private TextField partName = new MTextField("Part Name");
  private TextField manufacturer = new MTextField("Manufacturer");
  private TextField modelID = new MTextField("Model ID");
  private TextField price = new MTextField("Price");
  private TextField availability = new MTextField("Availability");

  @Override
  protected Component createContent() {
    return null;
  }
}
