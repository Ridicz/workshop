package com.databases.workshop.backend.client;

import java.util.List;

public interface ClientDAO {
  List<Client> findAllClients();
  void createClient(Client client);
  void updateClient(Client newClient);
  void deleteClient(Integer id);
  Client findClientByID(Integer id);
  Client findClientByFirstName(String firstName);
  Client findClientByLastName(String lastName);
  List<Client> findClientsContains(String nameFilter);
}
