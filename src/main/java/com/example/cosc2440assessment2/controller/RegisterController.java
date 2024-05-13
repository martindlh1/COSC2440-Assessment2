package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.User;
import com.example.cosc2440assessment2.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private final UserService userService = new UserService();

    public TextField username;
    public TextField password;
    public TextField full_name;
    public ComboBox<String> role;

    @FXML
    protected void register() {
        userService.addUser(new User(username.getText(), password.getText(), full_name.getText(), Role.valueOf(role.getValue())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(Role.ADMIN));
        list.add(String.valueOf(Role.DEPENDENT));
        list.add(String.valueOf(Role.POLICY_HOLDER));
        list.add(String.valueOf(Role.POLICY_OWNER));
        list.add(String.valueOf(Role.ASSURANCE_MANAGER));
        list.add(String.valueOf(Role.ASSURANCE_SURVEYOR));
        ObservableList<String> observableList = FXCollections.observableList(list);
        role.getItems().clear();
        role.setItems(observableList);
    }
}