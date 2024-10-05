package Controllers;

import javafx.geometry.Insets;
import models.*;
import Utilities.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CreateUserPage {

    private GridPane view;
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private Button createButton;
    private Label messageLabel;
    private InvitationCode invitation;

    public CreateUserPage(InvitationCode invitation) {
        this.invitation = invitation;

        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setVgap(10);
        view.setHgap(10);

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordField = new PasswordField();

        createButton = new Button("Create Account");
        createButton.setOnAction(e -> handleCreateAccount());

        messageLabel = new Label();

        view.add(usernameLabel, 0, 0);
        view.add(usernameField, 1, 0);
        view.add(passwordLabel, 0, 1);
        view.add(passwordField, 1, 1);
        view.add(confirmPasswordLabel, 0, 2);
        view.add(confirmPasswordField, 1, 2);
        view.add(createButton, 1, 3);
        view.add(messageLabel, 0, 4, 2, 1);
    }

    public GridPane getView() {
        return view;
    }

    private void handleCreateAccount() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        UserManager userManager = UserManager.getInstance();

        if (userManager.isUsernameTaken(username)) {
            messageLabel.setText("Username is already taken.");
            return;
        }

        User newUser = new User(username, password);
        newUser.setFirstLogin(true);
        newUser.setRoles(invitation.getRoles());

        userManager.addUser(newUser);
        invitation.setUsed(true);
        userManager.removeInvitationCode(invitation.getCode());

        messageLabel.setText("Account created successfully. Please log in.");
    }
}
