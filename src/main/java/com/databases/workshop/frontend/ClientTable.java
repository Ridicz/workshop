package com.databases.workshop.frontend;

import com.databases.workshop.backend.client.Client;
import com.databases.workshop.backend.client.ClientDAO;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientTable extends EntityTable<Client> {

  private final ClientDAO clientDAO;

  @Autowired
  public ClientTable(ClientDAO clientDAO) {
    withProperties("id", "firstName", "lastName");
    withColumnHeaders("ID", "First Name", "Last Name");
    setSortableProperties("FirstName", "LastName");
    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("firstName", 400);
    setColumnWidth("lastName", 400);
    setColumnAlignment("id", Table.Align.CENTER);

    this.clientDAO = clientDAO;
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Client());
    setRows(clientDAO.findClientsContains(likeFilter));
  }

  @Override
  public void add() {

  }

  @Override
  public void edit() {

  }

  @Override
  public void remove() {

  }
}
