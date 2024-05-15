package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();

    public Label role;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.setText(Role.ADMIN.name());
    }

    @FXML
    public void logout() throws IOException {
        auth.logout();
        Main.switchScene("/fxml/login.fxml");
    }
}