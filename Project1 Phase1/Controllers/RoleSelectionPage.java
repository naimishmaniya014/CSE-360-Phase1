import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class RoleSelectionPage {

    private GridPane view;
    private ComboBox<Role> roleComboBox;
    private Button proceedButton;
    private Label messageLabel;
    private User user;

    public RoleSelectionPage(User user) {
        this.user = user;

        view = new GridPane();
        view.setPadding(new Insets(20));
        view.setVgap(10);
        view.setHgap(10);

        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll(user.getRoles());

        proceedButton = new Button("Proceed");
        proceedButton.setOnAction(e -> handleProceed());

        messageLabel = new Label();

        view.add(new Label("Select Role for this session:"), 0, 0);
        view.add(roleComboBox, 1, 0);
        view.add(proceedButton, 1, 1);
        view.add(messageLabel, 0, 2, 2, 1);
    }

    public GridPane getView() {
        return view;
    }

    private void handleProceed() {
        Role selectedRole = roleComboBox.getValue();
        if (selectedRole == null) {
            messageLabel.setText("Please select a role.");
            return;
        }
        SessionManager.getInstance().setCurrentRole(selectedRole);
        Main.showHomePage(user, selectedRole);
    }
}
