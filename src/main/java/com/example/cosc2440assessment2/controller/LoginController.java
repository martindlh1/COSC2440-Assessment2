package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.helper.Auth;
import com.example.cosc2440assessment2.model.User;
import com.example.cosc2440assessment2.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            state.setText("logged");
            auth.login(user);
            Main.switchScene("/fxml/add-user.fxml");
        } else
            state.setText("Wrong password");
    }

    @FXML
    protected void viewUser() throws Exception {
        Main.switchScene("/fxml/user-list.fxml");
    }
}