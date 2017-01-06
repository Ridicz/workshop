package com.databases.workshop.frontend;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.viritin.button.MButton;

@UIScope
@SpringView
public class NavigationMenu extends HorizontalLayout {

  private EventBus eventBus;

  @Autowired
  public NavigationMenu(EventBus.UIEventBus eventBus) {
    this.eventBus = eventBus;
    this.init();
  }

  private void init() {
    MButton clients = new MButton("Clients");
    MButton mechanics = new MButton("Mechanics");
    MButton vehicles = new MButton("Vehicles");
    MButton repairs = new MButton("Repairs");
    MButton parts = new MButton("Parts");
    MButton machines = new MButton("Machines");
    MButton models = new MButton("Models");

    clients.addClickListener(event -> eventBus.publish(EventScope.APPLICATION, new ChangeTableEvent(new ClientTable())));
    mechanics.addClickListener(event -> eventBus.publish(EventScope.APPLICATION, new ChangeTableEvent(new MechanicTable())));

    addComponents(clients, mechanics, vehicles, repairs, parts, machines, models);
    setSpacing(true);
  }
}
