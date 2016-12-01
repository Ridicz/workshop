package com.databases.workshop.frontend;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.client.ClientDAO;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.components.DisclosurePanel;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@Title("Workshop site")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

//  private ClientRepository clientDAO;
  private ClientDAO clientDAO;
  private ClientForm clientForm;
  private EventBus.UIEventBus eventBus;

  private MTable<Client> list = new MTable<>(Client.class)
    .withProperties("ClientID", "FirstName", "LastName")
    .withColumnHeaders("ID", "First Name", "Last Name")
    .setSortableProperties("FirstName", "LastName")
    .withFullWidth();

  private TextField filterByName = new MTextField().withInputPrompt("Filter by name");

  private Button addNew = new MButton(FontAwesome.PLUS, this::add);
  private Button edit = new MButton(FontAwesome.PENCIL_SQUARE_O, this::edit);
  private Button delete = new MButton(FontAwesome.TRASH_O, "Are you sure?", this::remove);

  public MainUI(ClientDAO /*ClientRepository*/ clientDAO, ClientForm clientForm, EventBus.UIEventBus eventBus) {
    this.clientDAO = clientDAO;
    this.clientForm = clientForm;
    this.eventBus = eventBus;
  }

  @Override
  protected void init(VaadinRequest vaadinRequest) {
//    DisclosurePanel aboutBox = new DisclosurePanel("TEXT", new RichText().withMarkDownResource("welcome.md"));
    setContent(
      new MVerticalLayout(
//        aboutBox,
        new MHorizontalLayout(filterByName, addNew, edit, delete),
        list
      ).expand(list)
    );
    listEntities();

    list.addMValueChangeListener(e -> adjustActionButtonState());
    filterByName.addTextChangeListener(e ->
      listEntities(e.getText()));

    eventBus.subscribe(this);
  }

  protected void adjustActionButtonState() {
    boolean hasSelection = list.getValue() != null;
    edit.setEnabled(hasSelection);
    delete.setEnabled(hasSelection);
  }

  static final int PAGESIZE = 45;

  private void listEntities() {
    listEntities(filterByName.getValue());
  }

  private void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    list.setRows(clientDAO.findClientsContains(likeFilter));
//    list.setRows(clientDAO.findAllClients());

    adjustActionButtonState();

  }

  public void add(Button.ClickEvent clickEvent) {
    edit(new Client());
  }

  public void edit(Button.ClickEvent e) {
    edit(list.getValue());
  }

  public void remove(Button.ClickEvent e) {
    clientDAO.deleteClient(list.getValue().getId());
    list.setValue(null);
    listEntities();
  }

  protected void edit(final Client phoneBookEntry) {
    clientForm.setEntity(phoneBookEntry);
    clientForm.openInModalPopup();
  }

  @EventBusListenerMethod(scope = EventScope.UI)
  public void onPersonModified(ClientModifiedEvent event) {
    listEntities();
    clientForm.closePopup();
  }
}
