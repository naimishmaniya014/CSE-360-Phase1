import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.*;

public class AdminHomePage {

    private VBox view;
    private User user;
    private Label welcomeLabel;
    private Button inviteButton;
    private Button resetButton;
    private Button deleteButton;
    private Button listUsersButton;
    private Button manageRolesButton;
    private Button logoutButton;
    private TextArea outputArea;

    public AdminHomePage(User user) {
        this.user = user;

        view = new VBox(10);
        view.setPadding(new Insets(20));

        welcomeLabel = new Label("Welcome, " + user.getPreferredName() + " (Admin)");

        inviteButton = new Button("Invite User");
        inviteButton.setOnAction(e -> handleInviteUser());

        resetButton = new Button("Reset User Password");
        resetButton.setOnAction(e -> handleResetUser());

        deleteButton = new Button("Delete User");
        deleteButton.setOnAction(e -> handleDeleteUser());

        listUsersButton = new Button("List Users");
        listUsersButton.setOnAction(e -> handleListUsers());

        manageRolesButton = new Button("Manage User Roles");
        manageRolesButton.setOnAction(e -> handleManageRoles());

        logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> Main.showLoginPage());

        outputArea = new TextArea();
        outputArea.setEditable(false);

        view.getChildren().addAll(welcomeLabel, inviteButton, resetButton, deleteButton,
                listUsersButton, manageRolesButton, logoutButton, outputArea);
    }

    public VBox getView() {
        return view;
    }

    private void handleInviteUser() {
        // Implementation for inviting user
        InviteUserDialog dialog = new InviteUserDialog();
        Optional<InvitationCode> result = dialog.showAndWait();

        result.ifPresent(code -> {
            UserManager.getInstance().addInvitationCode(code);
            outputArea.appendText("Invitation Code: " + code.getCode() + "\n");
            outputArea.appendText("Roles: " + code.getRoles() + "\n");
        });
    }

    private void handleResetUser() {
        // Implementation for resetting user password
        ResetUserDialog dialog = new ResetUserDialog();
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(username -> {
            UserManager userManager = UserManager.getInstance();
            User targetUser = userManager.getUserByUsername(username);

            if (targetUser != null) {
                targetUser.setResetRequired(true);
                targetUser.setOneTimePassword(UUID.randomUUID().toString().substring(0, 8));
                outputArea.appendText("User " + username + " password reset. One-time password: " + targetUser.getOneTimePassword() + "\n");
            } else {
                outputArea.appendText("User not found.\n");
            }
        });
    }

    private void handleDeleteUser() {
        // Implementation for deleting user
        DeleteUserDialog dialog = new DeleteUserDialog();
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(username -> {
            UserManager userManager = UserManager.getInstance();
            User targetUser = userManager.getUserByUsername(username);

            if (targetUser != null) {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + username + "?");
                Optional<ButtonType> confirmation = confirmAlert.showAndWait();

                if (confirmation.isPresent() && confirmation.get() == ButtonType.OK) {
                    userManager.removeUser(username);
                    outputArea.appendText("User " + username + " deleted.\n");
                }
            } else {
                outputArea.appendText("User not found.\n");
            }
        });
    }

    private void handleListUsers() {
        // Implementation for listing users
        UserManager userManager = UserManager.getInstance();
        Collection<User> users = userManager.getAllUsers();

        outputArea.clear();
        for (User u : users) {
            outputArea.appendText("Username: " + u.getUsername() + ", Name: " + u.getFirstName() + " " + u.getLastName() + ", Roles: " + u.getRoles() + "\n");
        }
    }

    private void handleManageRoles() {
        // Implementation for managing user roles
        ManageRolesDialog dialog = new ManageRolesDialog();
        dialog.showAndWait();
    }
}
