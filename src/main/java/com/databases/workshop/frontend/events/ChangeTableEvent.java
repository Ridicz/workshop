package com.databases.workshop.frontend.events;

import com.databases.workshop.frontend.tables.EntityTable;

public class ChangeTableEvent {

  private EntityTable entityTable;

  public ChangeTableEvent(EntityTable entityTable) {
    this.entityTable = entityTable;
  }

  public EntityTable getEntityTable() {
    return entityTable;
  }
}
