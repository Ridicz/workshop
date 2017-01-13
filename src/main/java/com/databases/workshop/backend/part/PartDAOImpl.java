package com.databases.workshop.backend.part;

import com.databases.workshop.backend.model.Model;
import com.databases.workshop.backend.model.ModelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PartDAOImpl implements PartDAO {

  private JdbcTemplate template;

  private ModelDAO modelDAO;

  @Autowired
  public PartDAOImpl(DataSource dataSource, ModelDAO modelDAO) {
    template = new JdbcTemplate(dataSource);
    this.modelDAO = modelDAO;
  }

  @Override
  public void createPart(Part newPart) {
    if (findPartByID(newPart.getId()) != null) {
      updatePart(newPart);
      return;
    }

    String query = "INSERT INTO PARTS (PartName, Manufacturer, ModelID, Price, Availability) VALUES (?, ?, ?, ?, ?)";

    template.update(query, newPart.getPartName(), newPart.getManufacturer(), newPart.getModelID(), newPart.getPrice(), newPart.getAvailability());
  }

  @Override
  public void updatePart(Part part) {
    String query = "UPDATE PARTS SET PartName=?, Manufacturer=?, ModelID=?, Price=?, Availability=? WHERE PartID=?";

    template.update(query, part.getPartName(), part.getManufacturer(), part.getModelID(), part.getPrice(), part.getAvailability(), part.getId());
  }

  @Override
  public void deletePart(Integer id) {
    String query = "DELETE FROM PARTS WHERE PartID=?";

    template.update(query, id);
  }

  @Override
  public Part findPartByID(Integer id) {
    if (id == null) {
      return null;
    }

    String query = "SELECT * FROM PARTS WHERE PartID=?";

    return template.queryForObject(query, new Object[]{id}, (rs, rowNum) ->
      new Part(id, rs.getString("PartName"), rs.getString("Manufacturer"), rs.getInt("ModelID"),
        rs.getDouble("Price"), rs.getInt("Availability"), getModelName(rs.getInt("ModelID"))));
  }

  @Override
  public List<Part> findPartContains(String nameFilter) {
    String query = "SELECT * FROM PARTS WHERE PartName LIKE ?";

    return template.query(query, new Object[]{nameFilter}, (rs, rowNum) ->
      new Part(rs.getInt("PartID"), rs.getString("PartName"), rs.getString("Manufacturer"), rs.getInt("ModelID"),
        rs.getDouble("Price"), rs.getInt("Availability"), getModelName(rs.getInt("ModelID"))));
  }

  private String getModelName(Integer id) {
    Model model = modelDAO.findModelByID(id);
    return model.getModel();
  }
}
