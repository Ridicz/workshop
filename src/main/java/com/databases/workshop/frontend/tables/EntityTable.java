package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.model.BaseEntity;
import com.databases.workshop.frontend.forms.EntityForm;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.form.AbstractForm;

public abstract class EntityTable<T extends BaseEntity> extends MTable<T> {
  public abstract void listEntities(String nameFilter);
  public abstract EntityForm<T> getForm();
  public abstract void add();
  public abstract void edit(T baseEntity);
  public abstract void remove();
}
