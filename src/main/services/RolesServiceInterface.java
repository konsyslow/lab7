package main.services;

import main.model.pojo.Roles;

/**
 * Created by admin on 11.05.2017.
 */
public interface RolesServiceInterface {
    void insertRole(String username, String roleName);
    Roles getRole(String username);
}
