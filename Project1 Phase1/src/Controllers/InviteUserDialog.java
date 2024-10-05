package Controllers;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import models.InvitationCode;
import models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InviteUserDialog extends Dialog<InvitationCode> {

    private TextField rolesField;

    public InviteUserDialog() {
        setTitle("Invite User");
        setHeaderText("Generate an invitation code for a new user.");

        ButtonType generateButtonType = new ButtonType("Generate", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(generateButtonType, ButtonType.CANCEL);

        rolesField = new TextField();
        rolesField.setPromptText("Roles (comma-separated, e.g., ADMIN,STUDENT)");

        GridPane grid = new GridPane();
        grid.add(new Label("Roles:"), 0, 0);
        grid.add(rolesField, 1, 0);

        getDialogPane().setContent(grid);

        setResultConverter(dialogButton -> {
            if (dialogButton == generateButtonType) {
                String rolesInput = rolesField.getText().trim();
                if (!rolesInput.isEmpty()) {
                    List<Role> roles = new ArrayList<>();
                    for (String roleStr : rolesInput.split(",")) {
                        try {
                            roles.add(Role.valueOf(roleStr.trim().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            // Ignore invalid roles
                        }
                    }
                    if (!roles.isEmpty()) {
                        String code = UUID.randomUUID().toString().substring(0, 8);
                        return new InvitationCode(code, roles);
                    }
                }
            }
            return null;
        });
    }
}
