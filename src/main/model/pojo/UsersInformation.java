package main.model.pojo;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by admin on 15.04.2017.
 */
public class UsersInformation {

    private long id;
    @NotNull
    @Size(min=3, max=20)
    private String firstName;
    @NotNull
    @Size(min=3, max=20)
    private String secondName;
    @NotNull
    @Size(min=3, max=20)
    private String lastName;

    public UsersInformation(long id, String firstName, String secondName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public UsersInformation(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
