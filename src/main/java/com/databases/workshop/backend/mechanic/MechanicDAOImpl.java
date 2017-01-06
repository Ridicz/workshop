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

  }

  @Override
  public void updateMechanic(Mechanic newMechanic) {

  }

  @Override
  public void deleteMechanic(Integer id) {

  }

  @Override
  public Mechanic findMechanicByID(Integer id) {
    return null;
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
//    String query = "SELECT * FROM MECHANICS WHERE FirstName LIKE ? OR LastName LIKE ?";
    String query = "SELECT * FROM MECHANICS";

    return template.query(query, ((rs, rowNum) ->
      new Mechanic(Integer.valueOf(rs.getString("MechanicID")), rs.getString("FirstName"),
        rs.getString("LastName"), rs.getInt("Salary"), rs.getInt("Age"), rs.getString("Specialization"))));
  }
}
