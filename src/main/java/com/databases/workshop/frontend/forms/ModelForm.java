package com.databases.workshop.frontend.forms;

import com.databases.workshop.backend.model.Model;
import com.databases.workshop.backend.model.ModelDAO;
import com.databases.workshop.frontend.events.EntityModifiedEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class ModelForm extends EntityForm<Model> {

  private TextField model = new MTextField("Model");
  private TextField version = new MTextField("Version");
  private TextField productionYear = new MTextField("Production Year");

  public ModelForm(ModelDAO modelDAO, EventBus.UIEventBus eventBus) {
    setSavedHandler(model -> {
      modelDAO.createModel(model);
      eventBus.publish(this, new EntityModifiedEvent(model));
    });
    setResetHandler(entity -> eventBus.publish(this, new EntityModifiedEvent(entity)));

    setSizeUndefined();
  }

  @Override
  protected Component createContent() {
    return new MVerticalLayout(
      new MFormLayout(
        model,
        version,
        productionYear
      ).withWidth(""),
      getToolbar()
    ).withWidth("");
  }
}
