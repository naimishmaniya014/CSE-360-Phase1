package Utilities;

import models.User;
import models.Role;

public class SessionManager {

    /**
     * <p> Title: Session Manager Utility. </p>
     * 
     * <p> Description: This class manages the session for the current user. It stores the 
     * current user and role during the session and provides methods to access, modify, or clear
     * session data. It follows the Singleton design pattern to ensure only one instance of the
     * session manager exists at any time. </p>
     * 
     * <p> This utility helps in managing user logins and roles throughout the session. </p>
     * 
     * @author Naimish
     * 
     * @version 1.00   2024-10-09  Initial version.
     */

    private static SessionManager instance = null;
    private User currentUser;
    private Role currentRole;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Implements the Singleton pattern.
     */
    private SessionManager() {}

    /**
     * Returns the single instance of the `SessionManager` class.
     * 
     * @return The singleton instance of `SessionManager`.
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * Returns the current user for this session.
     * 
     * @return The current user, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user for this session.
     * 
     * @param currentUser The user to be set as the current user.
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Returns the current role for this session.
     * 
     * @return The current role, or null if no role is assigned.
     */
    public Role getCurrentRole() {
        return currentRole;
    }

    /**
     * Sets the current role for this session.
     * 
     * @param currentRole The role to be set as the current role.
     */
    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    /**
     * Clears the session, effectively logging the user out.
     * It sets both the current user and the current role to null.
     */
    public void clearSession() {
        this.currentUser = null;
        this.currentRole = null;
    }

    /**
     * Checks if a user is currently logged in.
     * 
     * @return True if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return this.currentUser != null;
    }
}
