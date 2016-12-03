package com.databases.workshop.frontend;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.viritin.button.MButton;

@UIScope
@SpringView
public class NavigationMenu extends HorizontalLayout {
  private MButton clients;
  private MButton mechanics;
  private MButton vehicles;
  private MButton repairs;
  private MButton parts;
  private MButton machines;
  private MButton models;

  public NavigationMenu() {
    this.init();
  }

  private void init() {
    clients = new MButton("Clients");
    mechanics = new MButton("Mechanics");
    vehicles = new MButton("Vehicles");
    repairs = new MButton("Repairs");
    parts = new MButton("Parts");
    machines = new MButton("Machines");
    models = new MButton("Models");

    addComponents(clients, mechanics, vehicles, repairs, parts, machines, models);

    setSpacing(true);
  }


}
