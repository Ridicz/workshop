package com.databases.workshop.frontend;

import com.databases.workshop.frontend.tables.RepairInfoTable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class RepairPartsWindow extends Window {

  private RepairInfoTable repairInfoTable;

  public RepairPartsWindow() {
    super("Parts used in repair");
    center();
  }

  @Autowired
  public void setPartTable(RepairInfoTable repairInfoTable) {
    this.repairInfoTable = repairInfoTable;
  }

  public void init() {
    MVerticalLayout layout = new MVerticalLayout(repairInfoTable);
    setContent(layout);
  }
}
