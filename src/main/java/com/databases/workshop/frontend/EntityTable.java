package com.databases.workshop.frontend;

import org.vaadin.viritin.fields.MTable;

public abstract class EntityTable<T> extends MTable<T> {
  abstract void listEntities(String nameFilter);
  abstract void add();
  abstract void edit();
  abstract void remove();
}
