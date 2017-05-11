package main.model.dao;

import main.model.pojo.Roles;

/**
 * Created by admin on 11.05.2017.
 */
public interface RolesDao {
    Roles getRoleByUserName(String userName);
    void insertRole(String username, String roleName);
}
