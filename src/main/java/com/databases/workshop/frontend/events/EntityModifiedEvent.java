package com.databases.workshop.frontend.events;

import com.databases.workshop.backend.model.BaseEntity;

import java.io.Serializable;

public class EntityModifiedEvent implements Serializable {

  private final BaseEntity entity;

  public EntityModifiedEvent(BaseEntity entity) {
    this.entity = entity;
  }

  public BaseEntity getEntity() {
    return entity;
  }
}
