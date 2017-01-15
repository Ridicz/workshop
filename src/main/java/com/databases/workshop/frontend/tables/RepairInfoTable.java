package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.repair.Repair;
import com.databases.workshop.frontend.forms.EntityForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class RepairInfoTable extends EntityTable<Repair> {

  private Repair repair;

  public RepairInfoTable(Repair repair) {
    this.repair = repair;

    withProperties("partName");
  }

  @Override
  public void listEntities(String nameFilter) {
    setRows(new Repair());
    setRows();
  }

  @Override
  public EntityForm<Repair> getForm() {
    return null;
  }

  @Override
  public void add() {

  }

  @Override
  public void edit(Repair baseEntity) {

  }

  @Override
  public void remove() {

  }
}
