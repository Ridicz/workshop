package com.databases.workshop.backend.client;

import com.databases.workshop.backend.model.Person;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Client extends Person {

  public Client() {  }

  public Client(String firstName, String lastName) {
    super(firstName, lastName);
  }

  public Client(Integer id, String firstName, String lastName) {
    super(id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "Client{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      '}';
  }
}
