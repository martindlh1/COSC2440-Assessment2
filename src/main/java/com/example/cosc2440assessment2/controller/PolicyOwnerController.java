package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.PolicyOwner;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PolicyOwnerController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();
    private final PolicyOwner user = userService.getPolicyOwner(auth.getUser());

    public Label role;

    public PolicyOwnerController() throws UnauthorizedException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.setText(Role.POLICY_OWNER.name());
    }

    @FXML
    public void logout() throws IOException {
        auth.logout();
        Main.switchScene("/fxml/login.fxml");
    }
}