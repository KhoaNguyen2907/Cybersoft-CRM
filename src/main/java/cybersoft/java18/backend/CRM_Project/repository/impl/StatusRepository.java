package cybersoft.java18.backend.CRM_Project.repository.impl;

import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;
import cybersoft.java18.backend.CRM_Project.model.StatusModel;
import cybersoft.java18.backend.CRM_Project.repository.AbstracRepository;
import cybersoft.java18.backend.CRM_Project.repository.IStatusRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StatusRepository extends AbstracRepository<StatusModel> implements IStatusRepository {
    @Override
    public List<StatusModel> findAllStatus() {
        String query = "SELECT * FROM Status";
        return executeQuery(query, new IAbstractMapper<StatusModel>() {
            @Override
            public StatusModel rowMapper(ResultSet resultSet) throws SQLException {
                return new StatusModel(resultSet.getInt("id"), resultSet.getString("name"));
            }
        });
    }
}
