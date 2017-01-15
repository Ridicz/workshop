package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.repair.Repair;
import com.databases.workshop.backend.repair.RepairDAO;
import com.databases.workshop.frontend.forms.EntityForm;
import com.databases.workshop.frontend.forms.RepairForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class RepairTable extends EntityTable<Repair> {

  private RepairForm repairForm;

  private RepairDAO repairDAO;

  public RepairTable(RepairDAO repairDAO, RepairForm repairForm) {
    this.repairDAO = repairDAO;
    this.repairForm = repairForm;

    withProperties("id", "vehicleBrand", "startDate", "endDate", "price");
    withColumnHeaders("ID", "Vehicle", "Start Date", "End Date", "Price");
    setSortableProperties("price");
//    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("vehicleBrand", 200);
    setColumnWidth("startDate", 200);
    setColumnWidth("endDate", 200);
    setColumnWidth("price", 200);;
    setColumnAlignment("id", Align.CENTER);
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Repair());
    setRows(repairDAO.findRepairsContains(likeFilter));
  }

  @Override
  public EntityForm<Repair> getForm() {
    return repairForm;
  }

  @Override
  public void add() {
    edit(new Repair());
  }

  @Override
  public void edit(Repair repair) {
    repairForm.setEntity(repair);
    repairForm.openInModalPopup();
  }

  @Override
  public void remove() {
    repairDAO.deleteRepair(getValue().getId());
    setValue(null);
    listEntities("");
  }
}
