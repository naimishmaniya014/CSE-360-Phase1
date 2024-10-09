package Controllers;

import javafx.geometry.Insets;
import models.*;
import Utilities.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class InstructorHomePage {

	/**
     * <p> Title: Instructor Home Page Controller. </p>
     * 
     * <p> Description: This class manages the Instructor Home Page, providing basic
     * functionalities such as displaying a welcome message and allowing the
     * instructor to log out. </p>
     * 
     * @author Naimish
     * 
     * @version 1.00   2024-10-09  Initial version.
     */
	
    private VBox view;
    private User user;
    private Label welcomeLabel;
    private Button logoutButton;

    public InstructorHomePage(User user) {
        this.user = user;

        view = new VBox(10);
        view.setPadding(new Insets(20));

        welcomeLabel = new Label("Welcome, " + user.getPreferredName() + " (Instructor)");
        logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> Main.showLoginPage());

        view.getChildren().addAll(welcomeLabel, logoutButton);
    }

    /**
     * Returns the view for the Instructor Home Page, which is a VBox layout.
     * 
     * @return The VBox layout of the instructor's home page.
     */
    public VBox getView() {
        return view;
    }
}
