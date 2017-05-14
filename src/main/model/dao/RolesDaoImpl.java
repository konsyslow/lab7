package main.model.dao;

import main.model.connection.ManagementSystem;
import main.model.pojo.Roles;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by admin on 11.05.2017.
 */
@Component
public class RolesDaoImpl implements RolesDao {
    static{
        PropertyConfigurator.configure("C:\\Users\\admin\\Documents\\lab6.1\\src\\main\\resources\\log4j.properties");
    }
    public static final String FIND_ROLE_BY_USERNAME = "SELECT * FROM users_roles WHERE username = ?";
    public static final String INSERT_ROLES = "INSERT INTO users_roles" +
            "(username, role) VALUES(?, ?)";
    private Connection connection;

    public PreparedStatement getPrepareStatement(String sql) {
        connection = ManagementSystem.getCon();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
            }
        }
    }

    public Roles getRoleByUserName(String userName) {
        Roles role = null;
        PreparedStatement preparedStatement = getPrepareStatement(FIND_ROLE_BY_USERNAME);
        try {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Roles(resultSet.getLong(1),resultSet.getString(2), resultSet.getString(3));

            }
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
        return role;
    }

    public void insertRole(String username, String roleName) {

        PreparedStatement preparedStatement = getPrepareStatement(INSERT_ROLES);
        try {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, roleName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }
}
