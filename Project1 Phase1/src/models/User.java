package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private boolean isFirstLogin;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredName;
    private List<Role> roles;
    private String oneTimePassword;
    private boolean resetRequired;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isFirstLogin = true;
        this.roles = new ArrayList<>();
        this.resetRequired = false;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public boolean isResetRequired() {
        return resetRequired;
    }

    public void setResetRequired(boolean resetRequired) {
        this.resetRequired = resetRequired;
    }


    // Add role
    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    // Remove role
    public void removeRole(Role role) {
        roles.remove(role);
    }
}
