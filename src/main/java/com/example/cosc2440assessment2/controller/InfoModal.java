package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class InfoModal implements Initializable {
    public GridPane grid;
    private final Auth auth = Auth.getInstance();
    private final UserService userService = new UserService();
    Function<Void, Void> function;
    private User user;
    TextField fullName;
    TextField username;
    TextField email;
    TextField phone;
    TextField address;

    public void init(User u, Function<Void, Void> function) {
        this.function = function;
        user = u;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));

        Label fullNameLabel = new Label("Full Name");
        Label usernameLabel = new Label("Username");
        Label emailLabel = new Label("Email");
        Label phoneLabel = new Label("Phone Number");
        Label addressLabel = new Label("Address");

        fullName = new TextField(user.getFullName());
        fullName.setMaxHeight(10);
        username = new TextField(user.getUsername());
        username.setEditable(false);
        username.setMaxHeight(10);
        email = new TextField(user.getEmail());
        email.setMaxHeight(10);
        phone = new TextField(user.getPhone());
        phone.setMaxHeight(10);
        address = new TextField(user.getAddress());
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

        Button update = new Button();
        update.setText("Update");
        update.setOnAction(this::updateUser);

        Button delete = new Button();
        delete.setText("Delete");
        delete.setOnAction(this::deleteUser);


        if (auth.getUser().getRole() == Role.POLICY_OWNER && !auth.getUser().getUsername().equals(user.getUsername())) {
            grid.add(update, 0, 5);
            grid.add(delete, 1, 5);
        } else {
            grid.add(update, 0, 5);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateUser(ActionEvent event) {
        User newUser = user;
        newUser.setEmail(email.getText());
        newUser.setAddress(address.getText());
        newUser.setPhone(phone.getText());
        newUser.setFullName(fullName.getText());
        try {
            userService.updateUser(newUser);
        } catch (UnauthorizedException e) {
            throw new RuntimeException(e);
        }
        ((Stage) username.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }

    public void deleteUser(ActionEvent event) {
    }
}
