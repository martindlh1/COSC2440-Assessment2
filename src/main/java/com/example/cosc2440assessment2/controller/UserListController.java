package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.User;
import com.example.cosc2440assessment2.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    private final UserService userService = new UserService();

    public ListView<String> userlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> users = userService.getAllUsers().stream().map(user -> user.getFullName() + " " + user.getRole()).toList();
        ObservableList<String> observableList = FXCollections.observableList(users);
        userlist.getItems().clear();
        userlist.setItems(observableList);
    }
}