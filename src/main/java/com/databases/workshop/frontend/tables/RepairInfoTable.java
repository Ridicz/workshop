package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.part.Part;
import com.databases.workshop.backend.repair.Repair;
import com.databases.workshop.frontend.forms.EntityForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class RepairInfoTable extends EntityTable<Part> {

  private Repair repair;

  public RepairInfoTable(Repair repair) {
    this.repair = repair;

    withProperties("partName");
  }

  @Override
  public void listEntities(String nameFilter) {
    setRows(new Part());
    setRows();
  }

  @Override
  public EntityForm<Part> getForm() {
    return null;
  }

  @Override
  public void add() {

  }

  @Override
  public void edit(Part baseEntity) {

  }

  @Override
  public void remove() {

  }
}
