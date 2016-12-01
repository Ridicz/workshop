package com.databases.workshop.backend.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Integer id;

  public BaseEntity() {  }

  public BaseEntity(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
