package com.databases.workshop.frontend;

import com.databases.workshop.backend.client.Client;

import java.io.Serializable;

public class ClientModifiedEvent implements Serializable {

  private final Client client;

  public ClientModifiedEvent(Client client) {
    this.client = client;
  }

  public Client getClient() {
    return client;
  }
}
