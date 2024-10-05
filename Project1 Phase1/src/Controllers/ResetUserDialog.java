package Controllers;

import javafx.scene.control.TextInputDialog;

public class ResetUserDialog extends TextInputDialog {

    public ResetUserDialog() {
        setTitle("Reset User Password");
        setHeaderText("Reset a user's password.");
        setContentText("Enter the username:");

        // Optionally, you can set default text in the input field
        getEditor().setText("");
    }
}
