package Controllers;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.application.Platform;
import javafx.concurrent.Task;
import models.User;
import Utilities.UserManager;

public class SetNewPasswordPage {

    private GridPane view;
    private PasswordField newPasswordField;
    private PasswordField confirmNewPasswordField;
    private Button setPasswordButton;
    private Label messageLabel;
    private User user;

    public SetNewPasswordPage(User user) {
        this.user = user;

        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setVgap(10);
        view.setHgap(10);

        Label newPasswordLabel = new Label("New Password:");
        newPasswordField = new PasswordField();

        Label confirmNewPasswordLabel = new Label("Confirm New Password:");
        confirmNewPasswordField = new PasswordField();

        setPasswordButton = new Button("Set Password");
        setPasswordButton.setOnAction(e -> handleSetPassword());

        messageLabel = new Label();

        view.add(newPasswordLabel, 0, 0);
        view.add(newPasswordField, 1, 0);
        view.add(confirmNewPasswordLabel, 0, 1);
        view.add(confirmNewPasswordField, 1, 1);
        view.add(setPasswordButton, 1, 2);
        view.add(messageLabel, 0, 3, 2, 1);
    }

    public GridPane getView() {
        return view;
    }

    private void handleSetPassword() {
        String newPassword = newPasswordField.getText().trim();
        String confirmNewPassword = confirmNewPasswordField.getText().trim();

        if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            messageLabel.setText("All fields are required.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        // Update the user's password
        user.setPassword(newPassword);

        // Invalidate the OTP and reset flags
        UserManager.getInstance().invalidateOtp(user);

        messageLabel.setText("Password updated successfully. Redirecting to login...");

        // Disable the button to prevent multiple submissions
        setPasswordButton.setDisable(true);

        // Create a background task to wait before redirecting
        Task<Void> redirectTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Wait for 3 seconds
                Thread.sleep(3000);
                return null;
            }

            @Override
            protected void succeeded() {
                // Redirect to login page on the JavaFX Application Thread
                Platform.runLater(() -> Main.showLoginPage());
            }
        };

        // Start the background task
        new Thread(redirectTask).start();
    }
}
