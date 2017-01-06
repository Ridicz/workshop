package com.databases.workshop.frontend;

import com.databases.workshop.backend.client.Client;
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
import org.vaadin.viritin.components.DisclosurePanel;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@Title("Workshop site")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

  private ClientForm clientForm;
  private EventBus.UIEventBus eventBus;

  private EntityTable entityTable;

  private ClientTable clientTable;
  private MechanicTable mechanicTable;

  private TextField filterByName = new MTextField().withInputPrompt("Filter by name");

  private Button addNew = new MButton(FontAwesome.PLUS, this::add);
  private Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);
  private Button delete = new ConfirmButton(FontAwesome.TRASH_O,
    "Are you sure you want to delete entity?", this::remove);

  @Autowired
  public MainUI(ClientForm clientForm, EventBus.UIEventBus eventBus, ClientTable clientTable, MechanicTable mechanicTable) {
    this.clientForm = clientForm;
    this.eventBus = eventBus;
    this.entityTable = clientTable;
    this.clientTable = clientTable;
    this.mechanicTable = mechanicTable;
  }

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    DisclosurePanel aboutBox = new DisclosurePanel("TEXT", new RichText().withMarkDownResource("/welcome.md"));
    setContent(
      new MVerticalLayout(
        new NavigationMenu(eventBus),
        aboutBox,
        new MHorizontalLayout(filterByName, addNew, edit, delete),
        entityTable
      ).expand(entityTable)
    );
    listEntities();

    entityTable.addMValueChangeListener(e -> adjustActionButtonState());
    filterByName.addTextChangeListener(e -> entityTable.listEntities(e.getText()));

    eventBus.subscribe(this);
  }

  private void update() {
    DisclosurePanel aboutBox = new DisclosurePanel("TEXT", new RichText().withMarkDownResource("/welcome.md"));
    setContent(
      new MVerticalLayout(
        new NavigationMenu(eventBus),
        aboutBox,
        new MHorizontalLayout(filterByName, addNew, edit, delete),
        entityTable
      ).expand(entityTable)
    );
    listEntities();
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
    entityTable.edit();
  }

  public void remove(Button.ClickEvent clickEvent) {
    entityTable.remove();
  }

  protected void edit(final Client client) {
    clientForm.setEntity(client);
    clientForm.openInModalPopup();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void onPersonModified(ClientModifiedEvent event) {
    listEntities();
    clientForm.closePopup();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void mechanicTableSet(ChangeTableEvent event) {
    entityTable = event.getEntityTable();
    update();
  }
}
