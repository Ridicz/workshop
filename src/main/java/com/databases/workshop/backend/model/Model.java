package com.databases.workshop.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Models")
public class Model extends BaseEntity {
  private String model;
  private String version;
  private int productionYear;

  public Model() {

  }

  public Model(Integer id, String model, String version, int productionYear) {
    super(id);
    this.model = model;
    this.version = version;
    this.productionYear = productionYear;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public int getProductionYear() {
    return productionYear;
  }

  public void setProductionYear(int productionYear) {
    this.productionYear = productionYear;
  }
}
