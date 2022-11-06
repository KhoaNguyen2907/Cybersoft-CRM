package cybersoft.java18.backend.CRM_Project.repository;

import cybersoft.java18.backend.CRM_Project.jdbc.PostgresqlConnection;
import cybersoft.java18.backend.CRM_Project.mapper.IAbstractMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbstracRepository<T> {
    public void setParameter(PreparedStatement statement, Object... parameter) throws SQLException {
        for (int i = 0; i < parameter.length; i++) {
            if (parameter[i] instanceof String) {
                statement.setString(i + 1, (String) parameter[i]);
            } else if (parameter[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) parameter[i]);
            } else if (parameter[i] instanceof Boolean) {
                statement.setBoolean(i + 1, (Boolean) parameter[i]);
            } else if (parameter[i] instanceof LocalDateTime) {
                if (parameter[i] != null) {
                    statement.setTimestamp(i + 1, Timestamp.valueOf((LocalDateTime) parameter[i]));
                }
            }
        }
    }

    public List<T> executeQuery(String query, IAbstractMapper<T> rowMapper, Object... parameter) {
        try (Connection connection = PostgresqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            List<T> list = new ArrayList<>();
            setParameter(statement, parameter);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(rowMapper.rowMapper(resultSet));
                }
            } catch (Exception e2) {
                throw new RuntimeException("Error when executing result set: " + e2.getMessage());
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error when executeQuery: " + e.getMessage());
        }
    }

    public int executeUpdate(String query, Object... parameter) {
        try (Connection connection = PostgresqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setParameter(statement, parameter);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
                return 0;
            } catch (Exception e2) {
                throw new RuntimeException("Error when executing resul set: " + e2.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error when executeUpdate: " + e.getMessage());
        }

    }

}
