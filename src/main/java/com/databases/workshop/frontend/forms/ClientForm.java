package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.client.ClientDAO;
import com.databases.workshop.frontend.events.ClientModifiedEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class ClientForm extends EntityForm<Client> {

  private TextField firstName = new MTextField("First Name");
  private TextField lastName = new MTextField("Last Name");

  public ClientForm(ClientDAO clientDAO, EventBus.UIEventBus eventBus) {
    setSavedHandler(client -> {
      clientDAO.createClient(client);
      eventBus.publish(this, new ClientModifiedEvent(client));
    });
    setResetHandler(p -> eventBus.publish(this, new ClientModifiedEvent(p)));

    setSizeUndefined();
  }

  @Override
  protected Component createContent() {
    return new MVerticalLayout(
      new MFormLayout(
        firstName,
        lastName
    ).withWidth(""),
      getToolbar()
    ).withWidth("");
  }
}
