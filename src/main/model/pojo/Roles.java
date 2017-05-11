package main.model.pojo;

/**
 * Created by admin on 11.05.2017.
 */
public class Roles {
    private long id;
    private String userName;
    private String roleName;

    public Roles(long id, String userName, String roleName) {
        this.id = id;
        this.userName = userName;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
