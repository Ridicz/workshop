package com.databases.workshop.frontend;

public class ChangeTableEvent {

  private EntityTable entityTable;

  public ChangeTableEvent(EntityTable entityTable) {
    this.entityTable = entityTable;
  }

  public EntityTable getEntityTable() {
    return entityTable;
  }
}
