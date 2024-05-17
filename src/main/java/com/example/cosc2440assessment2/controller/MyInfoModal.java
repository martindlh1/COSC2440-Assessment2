package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.user.Dependent;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MyInfoModal implements Initializable {
    public GridPane grid;
    private Dependent user = new Dependent("dependent", "dependent", "dependent dependent", "dependent@gmail.com", "345678765467", "55 Tran Din Xu");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));

        Label fullNameLabel = new Label("Full Name");
        Label usernameLabel = new Label("Username");
        Label emailLabel = new Label("Email");
        Label phoneLabel = new Label("Phone Number");
        Label addressLabel = new Label("Address");

        TextArea fullName = new TextArea(user.getFullName());
        fullName.setMaxHeight(10);
        TextArea username = new TextArea(user.getUsername());
        username.setMaxHeight(10);
        TextArea email = new TextArea(user.getEmail());
        email.setMaxHeight(10);
        TextArea phone = new TextArea(user.getPhone());
        phone.setMaxHeight(10);
        TextArea address = new TextArea(user.getAddress());
        address.setMaxHeight(10);

        grid.add(fullNameLabel, 0, 0);
        grid.add(fullName, 1, 0);
        grid.add(usernameLabel, 0, 1);
        grid.add(username, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(email, 1, 2);
        grid.add(phoneLabel, 0, 3);
        grid.add(phone, 1, 3);
        grid.add(addressLabel, 0, 4);
        grid.add(address, 1, 4);
    }

    public void updateDependent(ActionEvent event) {
    }
}
