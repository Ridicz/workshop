package com.databases.workshop.frontend;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.mechanic.Mechanic;
import com.databases.workshop.backend.model.BaseEntity;
import com.databases.workshop.backend.model.Model;
import com.databases.workshop.backend.part.Part;
import com.databases.workshop.backend.repair.Repair;
import com.databases.workshop.backend.vehicle.Vehicle;
import com.databases.workshop.frontend.events.*;
import com.databases.workshop.frontend.forms.EntityForm;
import com.databases.workshop.frontend.tables.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
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
  private EntityTable<Model> modelTable;
  private EntityTable<Part> partTable;
  private EntityTable<Repair> repairTable;

  private TextField filterByName = new MTextField().withInputPrompt("Filter by name");

  private Button addNew = new MButton(FontAwesome.PLUS, this::add);
  private Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);
  private Button delete = new ConfirmButton(FontAwesome.TRASH_O,
    "Are you sure you want to delete entity?", this::remove);
  private Button showRepairDetails = new MButton(FontAwesome.INFO_CIRCLE, this::showRepairDetails);

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

  @Autowired
  public void setModelTable(ModelTable modelTable) {
    this.modelTable = modelTable;
  }

  @Autowired
  public void setPartTable(PartTable partTable) {
    this.partTable = partTable;
  }

  @Autowired
  public void setRepairTable(RepairTable repairTable) {
    this.repairTable = repairTable;
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
        new MHorizontalLayout(filterByName, addNew, edit, delete, showRepairDetails),
        entityTable
      ).expand(entityTable)
    );
//    showRepairDetails.setVisible(false);
//    showRepairDetails.setEnabled(false);
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

  public void showRepairDetails(Button.ClickEvent clickEvent) {
    RepairPartsWindow window = new RepairPartsWindow();
    window.init();
    UI.getCurrent().addWindow(window);

//    final Window window = new Window("Window");
//    window.setWidth(800f, Unit.PIXELS);
//    window.setHeight(450f, Unit.PIXELS);
//    final FormLayout content = new FormLayout();
//
//    content.addComponent(partTable);
//    partTable.listEntities("");
//
//    window.setContent(content);
//    UI.getCurrent().addWindow(window);
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

  @EventBusListenerMethod(scope = EventScope.UI)
  public void modelTableSet(SelectedModelTableEvent event) {
    entityTable = modelTable;
    update();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void partTableSet(SelectedPartTableEvent event) {
    entityTable = partTable;
    update();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void repairTableSet(SelectedRepairTableEvent event) {
    entityTable = repairTable;
    update();
  }
}
