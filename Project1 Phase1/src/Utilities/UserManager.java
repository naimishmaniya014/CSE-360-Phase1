package Utilities;

import models.User;
import models.InvitationCode;
import java.util.*;

public class UserManager {
    private static UserManager instance = null;
    private Map<String, User> users;  // Stores users with username as the key
    private Map<String, InvitationCode> invitationCodes;  // Stores invitation codes

    private UserManager() {
        users = new HashMap<>();
        invitationCodes = new HashMap<>();
    }

    // Singleton pattern to get the single instance of UserManager
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Authenticate a user by username and password
    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && !user.isResetRequired() && user.getPassword().equals(password)) {
            return user;
        } else if (user != null && user.isResetRequired() && user.getOneTimePassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Add a new user to the system
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Check if a username is already taken
    public boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }

    // Add a new invitation code
    public void addInvitationCode(InvitationCode code) {
        invitationCodes.put(code.getCode(), code);
    }

    // Get an invitation code by code string
    public InvitationCode getInvitationCode(String code) {
        return invitationCodes.get(code);
    }

    // Remove an invitation code from the system
    public void removeInvitationCode(String code) {
        invitationCodes.remove(code);
    }
    
    // Get all users in the system
    public Collection<User> getAllUsers() {
        return users.values();
    }

    // Check if no users exist in the system
    public boolean noUsersExist() {
        return users.isEmpty();
    }

    // Reset a user's password by generating a one-time password
    public void resetPassword(String username) {
        User user = users.get(username);
        if (user != null) {
            String oneTimePassword = UUID.randomUUID().toString().substring(0, 8);
            user.setOneTimePassword(oneTimePassword);
            user.setResetRequired(true);
            System.out.println("Password reset. One-time password: " + oneTimePassword);
        }
    }

    // Add a role to a user
    public void addRoleToUser(String username, models.Role role) {
        User user = users.get(username);
        if (user != null) {
            user.addRole(role);
        }
    }

    // Remove a role from a user
    public void removeRoleFromUser(String username, models.Role role) {
        User user = users.get(username);
        if (user != null) {
            user.removeRole(role);
        }
    }

    // Get a user by username
    public User getUserByUsername(String username) {
        return users.get(username);
    }

    // Remove a user from the system by username
    public void removeUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
        } else {
            System.out.println("User not found.");
        }
    }

    // Check if a user is reset required
    public boolean isResetRequired(String username) {
        User user = users.get(username);
        return user != null && user.isResetRequired();
    }
    
}
