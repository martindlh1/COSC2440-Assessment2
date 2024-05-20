package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.service.ModalService;
import com.example.cosc2440assessment2.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController {
    private final UserService userService = new UserService();
    public ListView<String> userlist;
    public Button addClaim;
    private User selected = null;


    public void init(List<User> users) {
        addClaim.setDisable(true);
        List<String> userString = new ArrayList<>();
        users.forEach(u -> {
           userString.add(u.getFullName());
        });
        userlist.setItems(FXCollections.observableList(userString));
        userlist.setOnMouseClicked(mouseEvent -> {
           String selectedString = userlist.getFocusModel().getFocusedItem();
           selected = users.get(userString.indexOf(selectedString));
           if (selected != null) {
                addClaim.setDisable(false);
           }
        });
    }

    public void addClaim(ActionEvent event) {
        try {
            ModalService.showAddClaim((Customer) selected, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}