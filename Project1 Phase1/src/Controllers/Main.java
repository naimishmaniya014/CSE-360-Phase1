package Controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;

public class Main extends Application {

    /**
     * <p> Title: Main Application Class. </p>
     * 
     * <p> Description: This class serves as the entry point for the application.
     * It controls the primary stage and manages navigation between different pages
     * such as login, account setup, role selection, and the home pages for various user roles. </p>
     * 
     * @author Naimish
     * 
     * @version 1.00   2024-10-09  Initial version.
     */

    private static Stage primaryStage;

    /**
     * Starts the application and sets the initial stage.
     * The login page is displayed when the application starts.
     * 
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Phase One Application");

        // Show the login page
        showLoginPage();
    }

    /**
     * Displays the login page in the primary stage.
     */
    public static void showLoginPage() {
        LoginPage loginPage = new LoginPage();
        Scene scene = new Scene(loginPage.getView(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Displays the account setup page for a new user.
     * 
     * @param user The user object whose account is being set up.
     */
    public static void showAccountSetupPage(User user) {
        AccountSetupPage accountSetupPage = new AccountSetupPage(user);
        Scene scene = new Scene(accountSetupPage.getView(), 400, 400);
        primaryStage.setScene(scene);
    }

    /**
     * Displays the role selection page for users with multiple roles.
     * 
     * @param user The user object selecting their role.
     */
    public static void showRoleSelectionPage(User user) {
        RoleSelectionPage roleSelectionPage = new RoleSelectionPage(user);
        Scene scene = new Scene(roleSelectionPage.getView(), 400, 400);
        primaryStage.setScene(scene);
    }

    /**
     * Displays the home page based on the user's selected role.
     * 
     * @param user The user object whose home page is being displayed.
     * @param role The selected role determining which home page to display (Admin, Student, Instructor).
     */
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

    /**
     * Returns the primary stage of the application.
     * 
     * @return The primary stage being used by the application.
     */
    public static Stage getStage() {
        return primaryStage;
    }

    /**
     * Main method that launches the JavaFX application.
     * 
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
    	System.out.println("Running all tests...");

        // 1. Call Role Tests
        models.RoleTest roleTester = new models.RoleTest();
        roleTester.runTests();

        // 2. Call UserManager Tests
        Utilities.UserManagerTest userManagerTester = new Utilities.UserManagerTest();
        userManagerTester.runTests();

        // 3. Call SessionManager Tests
        Utilities.SessionManagerTest sessionManagerTester = new Utilities.SessionManagerTest();
        sessionManagerTester.runTests();

        // 4. Call User Tests
        models.UserTest userTester = new models.UserTest();
        userTester.runTests();

        // 5. Call InvitationCode Tests
        models.InvitationCodeTest invitationCodeTester = new models.InvitationCodeTest();
        invitationCodeTester.runTests();

        System.out.println("All tests completed.");
        launch(args);
    }
}
