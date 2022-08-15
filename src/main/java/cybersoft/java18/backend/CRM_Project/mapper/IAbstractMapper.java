package cybersoft.java18.backend.CRM_Project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IAbstractMapper<T> {
    T rowMapper(ResultSet resultSet) throws SQLException;
}
