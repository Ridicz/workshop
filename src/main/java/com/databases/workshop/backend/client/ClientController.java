package com.databases.workshop.backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class ClientController {

  private ClientDAO clientDAO;

  @Autowired
  public ClientController(ClientDAO clientDAO) {
    Assert.notNull(clientDAO);
    this.clientDAO = clientDAO;
  }

  public void setClientDAO(ClientDAO clientDAO) {
    this.clientDAO = clientDAO;
  }

  @RequestMapping(value = "/clients/", method = RequestMethod.GET)
  public List<Client> printClients() {
    return clientDAO.findAllClients();
  }

  @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
  public ResponseEntity<Client> getClient(@PathVariable Integer id) {
    Client unit = clientDAO.findClientByID(id);

    if (unit == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(unit, new HttpHeaders(), HttpStatus.OK);
  }


  @RequestMapping(value = "/clients/", method = RequestMethod.POST)
  public ResponseEntity<Void> addClient(@RequestBody Client client, UriComponentsBuilder builder) {
    clientDAO.createClient(client);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/units/{name}").buildAndExpand(client.getFirstName()).
      expand("-" + client.getLastName()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/clients/{name}", method = RequestMethod.DELETE)
  public ResponseEntity<Client> deleteClient(@PathVariable Integer id) {
    Client unit = clientDAO.findClientByID(id);

    if (unit == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    clientDAO.deleteClient(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
    Client currentUnit = clientDAO.findClientByID(id);

    if (currentUnit == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    clientDAO.updateClient(client);

    return new ResponseEntity<>(currentUnit, HttpStatus.OK);
  }
}








