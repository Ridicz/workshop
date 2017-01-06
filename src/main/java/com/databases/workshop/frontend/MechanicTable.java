package com.databases.workshop.frontend;

import com.databases.workshop.backend.mechanic.Mechanic;
import com.databases.workshop.backend.mechanic.MechanicDAO;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MechanicTable extends EntityTable<Mechanic> {

  private final MechanicDAO mechanicDAO;

  @Autowired
  public MechanicTable(MechanicDAO mechanicDAO) {
    withProperties("id", "firstName", "lastName", "salary", "age", "specialization");
    withColumnHeaders("ID", "First Name", "Last Name", "Salary", "Age", "Specialization");
    setSortableProperties("FirstName", "LastName");
    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("firstName", 200);
    setColumnWidth("lastName", 200);
    setColumnWidth("salary", 100);
    setColumnWidth("age", 100);
    setColumnWidth("specialization", 200);
    setColumnAlignment("id", Table.Align.CENTER);

    this.mechanicDAO = mechanicDAO;
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Mechanic());
    setRows(mechanicDAO.findMechanicsContains(likeFilter));
  }

  @Override
  public void add() {

  }

  @Override
  public void edit() {

  }

  @Override
  public void remove() {

  }
}
