package Controllers;

import javafx.scene.control.TextInputDialog;

public class DeleteUserDialog extends TextInputDialog {

    public DeleteUserDialog() {
        setTitle("Delete User");
        setHeaderText("Delete a user account.");
        setContentText("Enter the username:");

        // Optionally, you can set default text in the input field
        getEditor().setText("");
    }
}
