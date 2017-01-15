package com.databases.workshop.backend.repair;

import java.util.List;

public interface RepairDAO {
  List<Repair> findAllRepairs();
  void createRepair(Repair repair);
  void updateRepair(Repair newRepair);
  void deleteRepair(Integer id);
  Repair findRepairByID(Integer id);
  List<Repair> findRepairsContains(String nameFilter);
}
