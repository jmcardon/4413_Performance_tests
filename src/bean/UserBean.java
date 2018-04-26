package bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "user")
@XmlType(propOrder = {"id", "username", "firstname", "lastname", "userType"})
public class UserBean {

    //Visitor-only instantiation
    public UserBean(){
        this.userType = UserType.VISITOR;
    }

    public static UserBean newVisitor() {
        return new UserBean();
    }

    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private UserType userType;

    public UserBean(int id, String username, String fname, String lname, UserType type) {
        this.setId(id);
        this.setUsername(username);
        this.setFirstname(fname);
        this.setLastname(lname);
        this.setUserType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isAdmin(){
        return this.userType == UserType.ADMIN;
    }

    public boolean isCustomer(){
        return this.userType == UserType.CUSTOMER;
    }

    public boolean isVisitor(){
        return this.userType == UserType.VISITOR;
    }

    public boolean isPartner(){
        return this.userType == UserType.PARTNER;
    }

}
