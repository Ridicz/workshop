package com.databases.workshop.backend.vehicles;

import com.databases.workshop.backend.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicles")
public class Vehicle extends BaseEntity {

  private Integer clientID;
  private Integer modelID;
  private String brand;

  public Vehicle() {

  }

  public Vehicle(Integer id, Integer clientID, Integer modelID, String brand) {
    super(id);
    this.clientID = clientID;
    this.modelID = modelID;
    this.brand = brand;
  }

  public Integer getClientID() {
    return clientID;
  }

  public void setClientID(Integer clientID) {
    this.clientID = clientID;
  }

  public Integer getModelID() {
    return modelID;
  }

  public void setModelID(Integer modelID) {
    this.modelID = modelID;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
}
