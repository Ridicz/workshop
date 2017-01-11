package com.databases.workshop.backend.mechanic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MechanicDAOImpl implements MechanicDAO {

  private JdbcTemplate template;

  @Autowired
  public MechanicDAOImpl(DataSource dataSource) {
    Assert.notNull(dataSource);
    template = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Mechanic> findAllMechanics() {
    String query = "SELECT * FROM MECHANICS";

    return template.query(query, ((rs, rowNum) ->
      new Mechanic(Integer.valueOf(rs.getString("MechanicID")), rs.getString("FirstName"),
        rs.getString("LastName"), rs.getInt("Salary"), rs.getInt("Age"), rs.getString("Specialization"))));
  }

  @Override
  public void createMechanic(Mechanic mechanic) {
    if (findMechanicByID(mechanic.getId()) != null) {
      updateMechanic(mechanic);
      return;
    }

    String query = "INSERT INTO MECHANICS (FirstName, LastName, Salary, Age, Specialization) VALUES (?, ?, ?, ?, ?)";

    template.update(query, mechanic.getFirstName(), mechanic.getLastName(), mechanic.getSalary(), mechanic.getAge(), mechanic.getSpecialization());
  }

  @Override
  public void updateMechanic(Mechanic newMechanic) {
    String query = "UPDATE MECHANICS SET FirstName=?, LastName=?, Salary=?, Age=?, Specialization=? WHERE MechanicID=?";

    template.update(query, newMechanic.getFirstName(), newMechanic.getLastName(), newMechanic.getSalary(),
      newMechanic.getAge(), newMechanic.getSpecialization(), newMechanic.getId());
  }

  @Override
  public void deleteMechanic(Integer id) {
    String query = "DELETE FROM MECHANICS WHERE MechanicID = ?";

    template.update(query, id);
  }

  @Override
  public Mechanic findMechanicByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM MECHANICS WHERE MechanicID = ?";

    return template.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
      new Mechanic(id, rs.getString("FirstName"), rs.getString("LastName"),
        rs.getInt("Salary"), rs.getInt("Age"), rs.getString("Specialization")));
  }

  @Override
  public Mechanic findMechanicByFirstName(String firstName) {
    return null;
  }

  @Override
  public Mechanic findMechanicByLastName(String lastName) {
    return null;
  }

  @Override
  public List<Mechanic> findMechanicsContains(String nameFilter) {
    String query = "SELECT * FROM MECHANICS WHERE FirstName LIKE ? OR LastName LIKE ?";

    return template.query(query, new Object[]{nameFilter, nameFilter}, ((rs, rowNum) ->
      new Mechanic(Integer.valueOf(rs.getString("MechanicID")), rs.getString("FirstName"),
        rs.getString("LastName"), rs.getInt("Salary"), rs.getInt("Age"),
        rs.getString("Specialization"))));
  }
}
