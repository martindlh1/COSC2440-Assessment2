package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    private final Auth auth = Auth.getInstance();
    private final UserService userService = new UserService();

    public TextField username;
    public TextField password;
    public Label state;

    @FXML
    protected void login() throws Exception {
        User user = userService.getUserByUsername(username.getText());
        if (user == null)
            state.setText("No user with this username");
        else if (user.getPassword().equals(password.getText())) {
            auth.login(user);
            switchToDashboard(user.getRole());
        } else
            state.setText("Wrong password");
    }

    private void switchToDashboard(Role role) throws IOException {
        switch (role) {
            case Role.ADMIN -> Main.switchScene("/fxml/admin.fxml");
            case Role.DEPENDENT -> Main.switchScene("/fxml/dependent.fxml");
            case Role.POLICY_HOLDER -> Main.switchScene("/fxml/policy_holder.fxml");
            case Role.POLICY_OWNER -> Main.switchScene("/fxml/policy_owner.fxml");
            case Role.ASSURANCE_MANAGER -> Main.switchScene("/fxml/assurance_manager.fxml");
            case Role.ASSURANCE_SURVEYOR -> Main.switchScene("/fxml/assurance_surveyor.fxml");
        }
    }
}