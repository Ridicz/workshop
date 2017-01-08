package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.client.ClientDAO;
import com.databases.workshop.frontend.forms.ClientForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class ClientTable extends EntityTable<Client> {

  private ClientForm clientForm;

  private ClientDAO clientDAO;

  @Autowired
  public ClientTable(ClientDAO clientDAO, ClientForm clientForm) {
    this.clientDAO = clientDAO;
    this.clientForm = clientForm;

    withProperties("id", "firstName", "lastName");
    withColumnHeaders("ID", "First Name", "Last Name");
    setSortableProperties("FirstName", "LastName");
    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("firstName", 400);
    setColumnWidth("lastName", 400);
    setColumnAlignment("id", Table.Align.CENTER);
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Client());
    setRows(clientDAO.findClientsContains(likeFilter));
  }

  @Override
  public ClientForm getForm() {
    return clientForm;
  }

  @Override
  public void add() {
    edit(new Client());
  }

  @Override
  public void edit(Client client) {
    clientForm.setEntity(client);
    clientForm.openInModalPopup();
  }

  @Override
  public void remove() {
    clientDAO.deleteClient(getValue().getId());
    setValue(null);
    listEntities("");
  }
}
