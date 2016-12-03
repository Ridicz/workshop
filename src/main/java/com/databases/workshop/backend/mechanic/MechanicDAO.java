package com.databases.workshop.backend.mechanic;

import java.util.List;

public interface MechanicDAO {
  List<Mechanic> findAllMechanics();
  void createMechanic(Mechanic mechanic);
  void updateMechanic(Mechanic newMechanic);
  void deleteMechanic(Integer id);
  Mechanic findMechanicByID(Integer id);
  Mechanic findMechanicByFirstName(String firstName);
  Mechanic findMechanicByLastName(String lastName);
  List<Mechanic> findMechanicsContains(String nameFilter);
}
