import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class AccountSetupPage {

    private GridPane view;
    private TextField emailField;
    private TextField firstNameField;
    private TextField middleNameField;
    private TextField lastNameField;
    private TextField preferredNameField;
    private Button finishButton;
    private Label messageLabel;
    private User user;

    public AccountSetupPage(User user) {
        this.user = user;

        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setVgap(10);
        view.setHgap(10);

        emailField = new TextField();
        firstNameField = new TextField();
        middleNameField = new TextField();
        lastNameField = new TextField();
        preferredNameField = new TextField();

        finishButton = new Button("Finish Setup");
        finishButton.setOnAction(e -> handleFinishSetup());

        messageLabel = new Label();

        view.add(new Label("Email:"), 0, 0);
        view.add(emailField, 1, 0);
        view.add(new Label("First Name:"), 0, 1);
        view.add(firstNameField, 1, 1);
        view.add(new Label("Middle Name:"), 0, 2);
        view.add(middleNameField, 1, 2);
        view.add(new Label("Last Name:"), 0, 3);
        view.add(lastNameField, 1, 3);
        view.add(new Label("Preferred First Name (optional):"), 0, 4);
        view.add(preferredNameField, 1, 4);
        view.add(finishButton, 1, 5);
        view.add(messageLabel, 0, 6, 2, 1);
    }

    public GridPane getView() {
        return view;
    }

    private void handleFinishSetup() {
        String email = emailField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String middleName = middleNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String preferredName = preferredNameField.getText().trim();

        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            messageLabel.setText("Email, First Name, and Last Name are required.");
            return;
        }

        user.setEmail(email);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setPreferredName(preferredName.isEmpty() ? firstName : preferredName);
        user.setFirstLogin(false);

        UserManager.getInstance().addUser(user);
        SessionManager.getInstance().setCurrentUser(user);

        if (user.getRoles().size() > 1) {
            Main.showRoleSelectionPage(user);
        } else {
            Main.showHomePage(user, user.getRoles().get(0));
        }
    }
}
