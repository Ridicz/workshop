package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.part.Part;
import com.databases.workshop.backend.part.PartDAO;
import com.databases.workshop.frontend.forms.EntityForm;
import com.databases.workshop.frontend.forms.PartForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class PartTable extends EntityTable<Part> {

  private PartForm partForm;

  private PartDAO partDAO;

  public PartTable(PartDAO partDAO, PartForm partForm) {
    this.partDAO = partDAO;
    this.partForm = partForm;

    withProperties("id", "partName", "manufacturer", "price", "availability", "modelName");
    withColumnHeaders("ID", "Part Name", "Manufacturer", "Price", "Availability", "Model Name");
    setSortableProperties("partName");
    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("partName", 200);
    setColumnWidth("manufacturer", 200);
    setColumnWidth("price", 200);
    setColumnWidth("availability", 200);
    setColumnWidth("modelName", 200);
    setColumnAlignment("id", Align.CENTER);
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Part());
    setRows(partDAO.findPartContains(likeFilter));
  }

  @Override
  public EntityForm<Part> getForm() {
    return partForm;
  }

  @Override
  public void add() {
    edit(new Part());
  }

  @Override
  public void edit(Part part) {
    partForm.setEntity(part);
    partForm.openInModalPopup();
  }

  @Override
  public void remove() {
    partDAO.deletePart(getValue().getId());
    setValue(null);
    listEntities("");
  }
}
