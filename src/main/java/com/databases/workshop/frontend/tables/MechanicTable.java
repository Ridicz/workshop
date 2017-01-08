package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.mechanic.Mechanic;
import com.databases.workshop.backend.mechanic.MechanicDAO;
import com.databases.workshop.frontend.forms.MechanicForm;
import com.databases.workshop.frontend.tables.EntityTable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class MechanicTable extends EntityTable<Mechanic> {

  private MechanicForm mechanicForm;

  private MechanicDAO mechanicDAO;

  @Autowired
  public MechanicTable(MechanicDAO mechanicDAO, MechanicForm mechanicForm) {
    this.mechanicDAO = mechanicDAO;
    this.mechanicForm = mechanicForm;

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
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Mechanic());
    setRows(mechanicDAO.findMechanicsContains(likeFilter));
  }

  @Override
  public MechanicForm getForm() {
    return mechanicForm;
  }

  @Override
  public void add() {
    edit(new Mechanic());
  }

  @Override
  public void edit(Mechanic mechanic) {
    mechanicForm.setEntity(mechanic);
    mechanicForm.openInModalPopup();
  }

  @Override
  public void remove() {
    mechanicDAO.deleteMechanic(getValue().getId());
    setValue(null);
    listEntities("");
  }
}
