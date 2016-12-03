package com.databases.workshop.backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

  private DataSource dataSource;

  private JdbcTemplate template;

  @Autowired
  public ClientDAOImpl(DataSource dataSource) {
    Assert.notNull(dataSource);
    this.dataSource = dataSource;
    template = new JdbcTemplate(dataSource);
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<Client> findAllClients() {
    String query = "SELECT * FROM CLIENTS";

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();

      List<Client> list = new ArrayList<>();

      while (resultSet.next()) {
        list.add(new Client(resultSet.getInt("ClientID"), resultSet.getString("FirstName"),
          resultSet.getString("LastName")));
      }

      resultSet.close();
      statement.close();

      return list;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void createClient(Client client) {
    if (findClientByID(client.getId()) != null) {
      updateClient(client);
      return;
    }

    String query = "INSERT INTO CLIENTS (FirstName, LastName) VALUES (?, ?)";

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, client.getFirstName());
      statement.setString(2, client.getLastName());
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void updateClient(Client newClient) {
    String query = "UPDATE CLIENTS SET FirstName=?, LastName=? WHERE ClientID=?";

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, newClient.getFirstName());
      statement.setString(2, newClient.getLastName());
      statement.setInt(3, newClient.getId());
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void deleteClient(Integer id) {
    String query = "DELETE FROM CLIENTS WHERE ClientID = ?";

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, id);
      statement.executeUpdate();
      statement.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public Client findClientByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM CLIENTS WHERE ClientID = ?";

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, id);
      Client client = null;
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        client = new Client(resultSet.getInt("ClientID"), resultSet.getString("firstName"),
          resultSet.getString("lastName"));
      }

      resultSet.close();
      statement.close();

      return client;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
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

    Connection connection = null;

    try {
      connection = dataSource.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);

      statement.setString(1, nameFilter);
      statement.setString(2, nameFilter);

      ResultSet resultSet = statement.executeQuery();

      List<Client> list = new ArrayList<>();

      while (resultSet.next()) {
        list.add(new Client(resultSet.getInt("ClientID"), resultSet.getString("firstName"),
          resultSet.getString("lastName")));
      }

      resultSet.close();
      statement.close();

      return list;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
