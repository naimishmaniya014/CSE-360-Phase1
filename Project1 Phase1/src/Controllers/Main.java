package Controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Phase One Application");

        // Show the login page
        showLoginPage();
    }

    public static void showLoginPage() {
        LoginPage loginPage = new LoginPage();
        Scene scene = new Scene(loginPage.getView(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAccountSetupPage(User user) {
        AccountSetupPage accountSetupPage = new AccountSetupPage(user);
        Scene scene = new Scene(accountSetupPage.getView(), 400, 400);
        primaryStage.setScene(scene);
    }

    public static void showRoleSelectionPage(User user) {
        RoleSelectionPage roleSelectionPage = new RoleSelectionPage(user);
        Scene scene = new Scene(roleSelectionPage.getView(), 400, 400);
        primaryStage.setScene(scene);
    }

    public static void showHomePage(User user, Role role) {
        Scene scene;
        switch (role) {
            case ADMIN:
                AdminHomePage adminHomePage = new AdminHomePage(user);
                scene = new Scene(adminHomePage.getView(), 600, 600);
                break;
            case STUDENT:
                StudentHomePage studentHomePage = new StudentHomePage(user);
                scene = new Scene(studentHomePage.getView(), 400, 400);
                break;
            case INSTRUCTOR:
                InstructorHomePage instructorHomePage = new InstructorHomePage(user);
                scene = new Scene(instructorHomePage.getView(), 400, 400);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }
        primaryStage.setScene(scene);
    }
    
    public static Stage getStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

