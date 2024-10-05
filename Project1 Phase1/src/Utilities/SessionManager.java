package Utilities;

import models.User;
import models.Role;

public class SessionManager {
    private static SessionManager instance = null;
    private User currentUser;
    private Role currentRole;

    // Private constructor to prevent instantiation
    private SessionManager() {}

    // Singleton pattern to get the single instance of SessionManager
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Get the current user of the session
    public User getCurrentUser() {
        return currentUser;
    }

    // Set the current user for the session
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // Get the current role of the user for this session
    public Role getCurrentRole() {
        return currentRole;
    }

    // Set the current role for the session
    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    // Clear the session (log out the user)
    public void clearSession() {
        this.currentUser = null;
        this.currentRole = null;
    }

    // Check if a user is logged in
    public boolean isLoggedIn() {
        return this.currentUser != null;
    }
}
