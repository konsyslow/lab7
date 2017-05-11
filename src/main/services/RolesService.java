package main.services;

import main.model.dao.RolesDao;
import main.model.pojo.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 11.05.2017.
 */
@Service
public class RolesService implements RolesServiceInterface {
    private RolesDao rolesDao;

    public RolesDao getRolesDao() {
        return rolesDao;
    }

    @Autowired
    public void setRolesDao(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    public void insertRole(String username, String roleName) {

        rolesDao.insertRole(username,roleName);
    }

    public Roles getRole(String username) {
        return rolesDao.getRoleByUserName(username);
    }
}
