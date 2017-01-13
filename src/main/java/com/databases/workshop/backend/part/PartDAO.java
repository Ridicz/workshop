package com.databases.workshop.backend.part;

import java.util.List;

public interface PartDAO {
  void createPart(Part newPart);
  void updatePart(Part part);
  void deletePart(Integer id);
  Part findPartByID(Integer id);
  List<Part> findPartContains(String nameFilter);
}
