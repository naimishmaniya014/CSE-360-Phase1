import javafx.geometry.Insets;
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

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        UserManager userManager = UserManager.getInstance();
        User user = userManager.authenticate(username, password);

        if (user != null) {
            SessionManager.getInstance().setCurrentUser(user);

            if (user.isFirstLogin()) {
                Main.showAccountSetupPage(user);
            } else if (user.getRoles().size() > 1) {
                Main.showRoleSelectionPage(user);
            } else {
                Main.showHomePage(user, user.getRoles().get(0));
            }
        } else {
            messageLabel.setText("Invalid username or password.");
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
}
