package com.databases.workshop.frontend;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.mechanic.Mechanic;
import com.databases.workshop.backend.model.BaseEntity;
import com.databases.workshop.backend.vehicles.Vehicle;
import com.databases.workshop.frontend.events.EntityModifiedEvent;
import com.databases.workshop.frontend.events.SelectedClientTableEvent;
import com.databases.workshop.frontend.events.SelectedMechanicTableEvent;
import com.databases.workshop.frontend.events.SelectedVehicleTableEvent;
import com.databases.workshop.frontend.forms.EntityForm;
import com.databases.workshop.frontend.tables.ClientTable;
import com.databases.workshop.frontend.tables.EntityTable;
import com.databases.workshop.frontend.tables.MechanicTable;
import com.databases.workshop.frontend.tables.VehicleTable;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.ConfirmButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@Title("Workshop site")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

  private EventBus.UIEventBus eventBus;

  private EntityForm entityForm;
  private EntityTable entityTable;

  private EntityTable<Client> clientTable;
  private EntityTable<Mechanic> mechanicTable;
  private EntityTable<Vehicle> vehicleTable;

  private TextField filterByName = new MTextField().withInputPrompt("Filter by name");

  private Button addNew = new MButton(FontAwesome.PLUS, this::add);
  private Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);
  private Button delete = new ConfirmButton(FontAwesome.TRASH_O,
    "Are you sure you want to delete entity?", this::remove);

  @Autowired
  public MainUI(EventBus.UIEventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Autowired
  public void setEntityTable(MechanicTable vehicleTable) {
    this.entityTable = vehicleTable;
  }

  @Autowired
  public void setClientTable(ClientTable clientTable) {
    this.clientTable = clientTable;
  }

  @Autowired
  public void setMechanicTable(MechanicTable mechanicTable) {
    this.mechanicTable = mechanicTable;
  }

  @Autowired
  public void setVehicleTable(VehicleTable vehicleTable) {
    this.vehicleTable = vehicleTable;
  }

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    update();
    eventBus.subscribe(this);
  }

  private void update() {
    setContent(
      new MVerticalLayout(
        new NavigationMenu(eventBus),
        new MHorizontalLayout(filterByName, addNew, edit, delete),
        entityTable
      ).expand(entityTable)
    );
    listEntities();

    entityTable.addMValueChangeListener(event -> adjustActionButtonState());
    filterByName.addTextChangeListener(e -> entityTable.listEntities(e.getText()));

    entityForm = entityTable.getForm();
  }

  protected void adjustActionButtonState() {
    boolean hasSelection = entityTable.getValue() != null;
    edit.setEnabled(hasSelection);
    delete.setEnabled(hasSelection);
  }

  private void listEntities() {
    entityTable.listEntities(filterByName.getValue());
    adjustActionButtonState();
  }

  public void add(Button.ClickEvent clickEvent) {
    entityTable.add();
  }

  public void edit(Button.ClickEvent clickEvent) {
    entityTable.edit((BaseEntity) entityTable.getValue());
  }

  public void remove(Button.ClickEvent clickEvent) {
    entityTable.remove();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void onEntityModified(EntityModifiedEvent event) {
    listEntities();
    entityForm.closePopup();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void clientTableSet(SelectedClientTableEvent event) {
    entityTable = clientTable;
    update();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void mechanicTableSet(SelectedMechanicTableEvent event) {
    entityTable = mechanicTable;
    update();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void vehicleTableSet(SelectedVehicleTableEvent event) {
    entityTable = vehicleTable;
    update();
  }
}
