package models;

import java.time.LocalDateTime;
import Controllers.*;
import Utilities.*;
import java.util.ArrayList;
import java.util.List;

public class InvitationCode {
    private String code;
    private List<Role> roles;
    private boolean isUsed;

    public InvitationCode(String code, List<Role> roles) {
        this.code = code;
        this.roles = roles;
        this.isUsed = false;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
