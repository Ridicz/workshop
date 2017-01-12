package com.databases.workshop.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ModelDAOImpl implements ModelDAO {

  private JdbcTemplate template;

  @Autowired
  public ModelDAOImpl(DataSource dataSource) {
    Assert.notNull(dataSource);
    this.template = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Model> findAllModels() {
    return null;
  }

  @Override
  public void createModel(Model model) {

  }

  @Override
  public void updateModel(Model newModel) {

  }

  @Override
  public void deleteModel(Integer id) {

  }

  @Override
  public Model findModelByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM MODELS WHERE ModelID=?";

    return template.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
      new Model(rs.getInt("ModelID"), rs.getString("Model"), rs.getString("Version"),
        rs.getInt("ProductionYear")));
  }

  @Override
  public List<Model> findModelsContains(String nameFilter) {
    String query = "SELECT * FROM MODELS WHERE Version LIKE ?";

    return template.query(query, new Object[]{nameFilter}, (rs, rowNum) -> new Model(rs.getInt("ModelID"),
      rs.getString("Model"), rs.getString("Version"), rs.getInt("ProductionYear")));
  }
}
