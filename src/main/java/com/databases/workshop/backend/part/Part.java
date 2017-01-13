package com.databases.workshop.backend.part;

import com.databases.workshop.backend.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Parts")
public class Part extends BaseEntity {

  private String partName;
  private String manufacturer;
  private Integer modelID;
  private double price;
  private int availability;
  private String modelName;

  public Part() {

  }

  public Part(Integer id, String partName, String manufacturer, Integer modelID, double price, int availability, String modelName) {
    super(id);
    this.partName = partName;
    this.manufacturer = manufacturer;
    this.modelID = modelID;
    this.price = price;
    this.availability = availability;
    this.modelName = modelName;
  }

  public String getPartName() {
    return partName;
  }

  public void setPartName(String partName) {
    this.partName = partName;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public Integer getModelID() {
    return modelID;
  }

  public void setModelID(Integer modelID) {
    this.modelID = modelID;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getAvailability() {
    return availability;
  }

  public void setAvailability(int availability) {
    this.availability = availability;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }
}
