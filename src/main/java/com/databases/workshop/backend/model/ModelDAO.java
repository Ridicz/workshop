package com.databases.workshop.backend.model;

import java.util.List;

public interface ModelDAO {
  List<Model> findAllModels();
  void createModel(Model model);
  void updateModel(Model newModel);
  void deleteModel(Integer id);
  Model findModelByID(Integer id);
  List<Model> findModelsContains(String nameFilter);
}
