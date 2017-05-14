package main.model.dao;

import main.model.pojo.Users;
import main.model.connection.ManagementSystem;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 20.04.2017.
 */
public class UserDAOImpl implements UserDAO {
    static{
        PropertyConfigurator.configure("C:\\Users\\admin\\Documents\\lab6.1\\src\\main\\resources\\log4j.properties");
    }

    public static final String SELECT_ALL_USERS = "SELECT * FROM USERS";
    public static final String INSERT_USERS = "INSERT INTO USERS" +
            "(username, password, enable) VALUES(?, ?, ?)";
    public static final String UPDATE_USERS = "UPDATE users SET " +
            "username=?, password=? WHERE id=?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    public static final String FIND_USER = "SELECT * FROM users WHERE username = ? AND password = ?";
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE username = ?";
    public static final String GET_BY_ID = "SELECT * FROM users where id = ?";
    public static final String BLOCK_USER = "UPDATE users SET enable = ? WHERE id=?";
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

    public void insertUser(Users user) {
        PreparedStatement preparedStatement = getPrepareStatement(INSERT_USERS);
        try {
            //preparedStatement.setLong(1, usersInformation.getId());
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.isEnable());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public List<Users> getAll() {
        List<Users> list = new ArrayList<Users>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_USERS);
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Users users = new Users(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                list.add(users);
            }
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    public void updateUser(Users user) {

    }

    public void deleteUser(Integer id) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_USER);
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e){
           Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public Users findUserByLoginAndPassword(String login, String password) {
        Users user = null;
        PreparedStatement preparedStatement = getPrepareStatement(FIND_USER);
        try {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
        return user;
    }

    public Users findUserByLogin(String login) {
        Users user = null;
        PreparedStatement preparedStatement = getPrepareStatement(FIND_USER_BY_LOGIN);
        try {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
        return user;
    }

    private Users createEntity(ResultSet resultSet) throws SQLException {
        return new Users(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getInt("enable"));
    }

    public Users get(Integer id){
        PreparedStatement preparedStatement = getPrepareStatement(GET_BY_ID);
        try {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Users user = new Users(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"),rs.getInt("enable"));
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
        return null;
    }

    public void blockUnblockUser(Integer id, Integer enable) {
        PreparedStatement preparedStatement = getPrepareStatement(BLOCK_USER);
        try {
            preparedStatement.setInt(1, enable);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e){
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, "Catch SQLException", e);
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }
}
