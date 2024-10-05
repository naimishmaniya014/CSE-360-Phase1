import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class StudentHomePage {

    private VBox view;
    private User user;
    private Label welcomeLabel;
    private Button logoutButton;

    public StudentHomePage(User user) {
        this.user = user;

        view = new VBox(10);
        view.setPadding(new Insets(20));

        welcomeLabel = new Label("Welcome, " + user.getPreferredName() + " (Student)");
        logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> Main.showLoginPage());

        view.getChildren().addAll(welcomeLabel, logoutButton);
    }

    public VBox getView() {
        return view;
    }
}
