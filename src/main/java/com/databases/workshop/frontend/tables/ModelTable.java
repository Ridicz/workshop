package com.databases.workshop.frontend.tables;

import com.databases.workshop.backend.model.Model;
import com.databases.workshop.backend.model.ModelDAO;
import com.databases.workshop.frontend.forms.EntityForm;
import com.databases.workshop.frontend.forms.ModelForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class ModelTable extends EntityTable<Model> {

  private ModelForm modelForm;

  private ModelDAO modelDAO;

  @Autowired
  public ModelTable(ModelDAO modelDAO, ModelForm modelForm) {
    this.modelDAO = modelDAO;
    this.modelForm = modelForm;

    withProperties("id", "model", "version", "productionYear");
    withColumnHeaders("ID", "Model", "Version", "Production Year");
    setSortableProperties("Model");
    withFullWidth();

    setColumnWidth("id", 50);
    setColumnWidth("model", 200);
    setColumnWidth("version", 300);
    setColumnWidth("productionYear", 300);
    setColumnAlignment("id", Table.Align.CENTER);
  }

  @Override
  public void listEntities(String nameFilter) {
    String likeFilter = "%" + nameFilter + "%";
    setRows(new Model());
    setRows(modelDAO.findModelsContains(likeFilter));
  }

  @Override
  public EntityForm<Model> getForm() {
    return modelForm;
  }

  @Override
  public void add() {
    edit(new Model());
  }

  @Override
  public void edit(Model model) {
    modelForm.setEntity(model);
    modelForm.openInModalPopup();
  }

  @Override
  public void remove() {
    modelDAO.deleteModel(getValue().getId());
    setValue(null);
    listEntities("");
  }
}
