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
  private String model;
  private String modelVersion;
  private String clientName;

  public Vehicle() {

  }

  public Vehicle(Integer id, Integer clientID, Integer modelID, String brand, String model, String modelVersion,
                 String clientName) {
    super(id);
    this.clientID = clientID;
    this.modelID = modelID;
    this.brand = brand;
    this.model = model;
    this.modelVersion = modelVersion;
    this.clientName = clientName;
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

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getModelVersion() {
    return modelVersion;
  }

  public void setModelVersion(String modelVersion) {
    this.modelVersion = modelVersion;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }
}
