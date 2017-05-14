package main.services;

import main.model.connection.ManagementSystem;
import main.model.dao.UserDAO;
import main.model.dao.UserDAOImpl;
import main.model.pojo.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 20.04.2017.
 */
@Service
public class UserService implements UserServiceInterface {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Users auth(String login, String password) {

        //Users user = userDAO.findUserByLoginAndPassword(login, password);
        Users user = userDAO.findUserByLogin(login);

        if (user != null && user.isEnable()==0) {
            return null;
        }else if(user != null && user.isEnable()==1){
            try {
                boolean b = PasswordStorage.verifyPassword(password, user.getPassword());
                if(b==false){
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return user;
    }

    public void insert(String login, String password){
        Users user = new Users( 0,  login,  password,  1);
        userDAO.insertUser(user);
    }

    public List<Users> getAll(){
        return userDAO.getAll();
    }

    public Users get(Integer id) {
        return userDAO.get(id);
    }
    public Users getUserByLogin(String username) {
        return userDAO.findUserByLogin(username);
    }
    public void delete(Integer id) {
        userDAO.deleteUser(id);
    }

    public void blockUnblockUser(Integer id, Integer enable) {
        userDAO.blockUnblockUser(id,enable);
    }
}
