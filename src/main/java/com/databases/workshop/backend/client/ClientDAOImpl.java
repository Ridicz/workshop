package com.databases.workshop.backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

  private JdbcTemplate template;

  @Autowired
  public ClientDAOImpl(DataSource dataSource) {
    Assert.notNull(dataSource);
    template = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Client> findAllClients() {
    String query = "SELECT * FROM CLIENTS";

    return template.query(query, ((rs, rowNum) ->
      new Client(Integer.valueOf(rs.getString("ClientID")), rs.getString("FirstName"), rs.getString("LastName"))
    ));
  }

  @Override
  public void createClient(Client client) {
    if (findClientByID(client.getId()) != null) {
      updateClient(client);
      return;
    }

    String query = "INSERT INTO CLIENTS (FirstName, LastName) VALUES (?, ?)";

    template.update(query, client.getFirstName(), client.getLastName());
  }

  @Override
  public void updateClient(Client newClient) {
    String query = "UPDATE CLIENTS SET FirstName=?, LastName=? WHERE ClientID=?";

    template.update(query, newClient.getFirstName(), newClient.getLastName(), newClient.getId());
  }

  @Override
  public void deleteClient(Integer id) {
    String query = "DELETE FROM CLIENTS WHERE ClientID = ?";

    template.update(query, id);
  }

  @Override
  public Client findClientByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM CLIENTS WHERE ClientID = ?";

    return template.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
      new Client(id, rs.getString("FirstName"), rs.getString("LastName")));
  }

  @Override
  public Client findClientByFirstName(String firstName) {
    return null;
  }

  @Override
  public Client findClientByLastName(String lastName) {
    return null;
  }

  @Override
  public List<Client> findClientsContains(String nameFilter) {
    String query = "SELECT * FROM CLIENTS WHERE FirstName LIKE ? OR LastName LIKE ?";

    return template.query(query, new Object[]{nameFilter, nameFilter}, ((rs, rowNum) ->
      new Client(Integer.valueOf(rs.getString("ClientID")), rs.getString("FirstName"), rs.getString("LastName"))
    ));
  }
}
