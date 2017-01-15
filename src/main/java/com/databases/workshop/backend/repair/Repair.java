package com.databases.workshop.backend.repair;

import com.databases.workshop.backend.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Repairs")
public class Repair extends BaseEntity {

  private Integer vehicleID;
  private String startDate;
  private String endDate;
  private double price;
  private String vehicleBrand;

  public Repair() {

  }

  public Repair(Integer id, Integer vehicleID, String startDate, String endDate, double price, String vehicleBrand) {
    super(id);
    this.vehicleID = vehicleID;
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.vehicleBrand = vehicleBrand;
  }

  public Integer getVehicleID() {
    return vehicleID;
  }

  public void setVehicleID(Integer vehicleID) {
    this.vehicleID = vehicleID;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getVehicleBrand() {
    return vehicleBrand;
  }

  public void setVehicleBrand(String vehicleBrand) {
    this.vehicleBrand = vehicleBrand;
  }
}
