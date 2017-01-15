package com.databases.workshop.frontend;

import com.databases.workshop.backend.part.Part;
import com.databases.workshop.frontend.tables.EntityTable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Window;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class RepairPartsWindow extends Window {

  private EntityTable<Part> partTable;

  public RepairPartsWindow(EntityTable<Part> partTable) {
    super("Parts used in repair");
    this.partTable = partTable;
    center();
  }

  public void init() {
    MVerticalLayout layout = new MVerticalLayout(partTable);
    setContent(layout);
  }
}
