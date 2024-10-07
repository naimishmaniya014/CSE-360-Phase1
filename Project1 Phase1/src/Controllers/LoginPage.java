package Controllers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

import Utilities.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginPage {

    private GridPane view;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField invitationCodeField;
    private Button loginButton;
    private Button useInvitationButton;
    private Label messageLabel;
    
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");

    public LoginPage() {
        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setVgap(10);
        view.setHgap(10);

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin());

        Label invitationLabel = new Label("Invitation Code:");
        invitationCodeField = new TextField();

        useInvitationButton = new Button("Use Code");
        useInvitationButton.setOnAction(e -> handleInvitationCode());

        messageLabel = new Label();

        view.add(usernameLabel, 0, 0);
        view.add(usernameField, 1, 0);
        view.add(passwordLabel, 0, 1);
        view.add(passwordField, 1, 1);
        view.add(loginButton, 1, 2);
        view.add(new Separator(), 0, 3, 2, 1);
        view.add(invitationLabel, 0, 4);
        view.add(invitationCodeField, 1, 4);
        view.add(useInvitationButton, 1, 5);
        view.add(messageLabel, 0, 6, 2, 1);
    }

    public GridPane getView() {
        return view;
    }

    // Existing methods...

    private void handleLogin() {
        UserManager userManager = UserManager.getInstance();

        // Check if the user list is empty
        if (userManager.getAllUsers().isEmpty()) {
            handleFirstAdminCreation();  // Create the first admin user
            return;
        }

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        User user = userManager.authenticate(username, password);

        if (user != null) {
            SessionManager.getInstance().setCurrentUser(user);

            if (user.isResetRequired()) {
                // Redirect to SetNewPasswordPage
                SetNewPasswordPage setNewPasswordPage = new SetNewPasswordPage(user);
                Scene scene = new Scene(setNewPasswordPage.getView(), 400, 300);
                Main.getStage().setScene(scene);
            } else if (user.isFirstLogin()) {
                Main.showAccountSetupPage(user);
            } else if (user.getRoles().size() > 1) {
                Main.showRoleSelectionPage(user);
            } else {
                Main.showHomePage(user, user.getRoles().get(0));
            }
        } else {
            // Check if the user exists and if the OTP was used or expired
        	User existingUser = userManager.getUserByUsername(username);
            if (existingUser != null && existingUser.isResetRequired()) {
                // Determine if the OTP is expired
                LocalDateTime now = LocalDateTime.now();
                if (existingUser.getOtpExpiration() != null && now.isAfter(existingUser.getOtpExpiration())) {
                    String formattedExpiry = existingUser.getOtpExpiration().format(DISPLAY_FORMATTER);
                    messageLabel.setText("One-time password has expired at " + formattedExpiry + ". Please contact an administrator.");
                } else {
                    messageLabel.setText("Invalid one-time password.");
                }
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        }
    }

    private void handleInvitationCode() {
        String code = invitationCodeField.getText().trim();
        UserManager userManager = UserManager.getInstance();
        InvitationCode invitation = userManager.getInvitationCode(code);

        if (invitation != null && !invitation.isUsed()) {
            // Proceed to account creation
            CreateUserPage createUserPage = new CreateUserPage(invitation);
            Scene scene = new Scene(createUserPage.getView(), 400, 400);
            Main.getStage().setScene(scene);
        } else {
            messageLabel.setText("Invalid or used invitation code.");
        }
    }

 // Additional method for creating the first admin user
    private void handleFirstAdminCreation() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Username and password cannot be empty.");
            return;
        }

        // Prompt for password confirmation
        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Confirm Password");
        dialog.setHeaderText("Enter the password again for confirmation:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals(password)) {
            // Create the first admin user
            UserManager userManager = UserManager.getInstance();
            User adminUser = new User(username, password);
            adminUser.addRole(Role.ADMIN);  // Set role as Admin
            userManager.addUser(adminUser);

            // Direct to the login page again
            messageLabel.setText("Admin account created. Please log in.");
        } else {
            messageLabel.setText("Passwords do not match. Try again.");
        }
    }
}
