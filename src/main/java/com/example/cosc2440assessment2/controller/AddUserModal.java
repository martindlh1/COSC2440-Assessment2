/**
 * @author <Team 8>
 */
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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class AddUserModal {
    private final Auth auth = Auth.getInstance();
    private final UserService userService = new UserService();
    public GridPane grid;
    TextField fullName;
    TextField username;
    TextField email;
    TextField phone;
    TextField address;
    TextField password;
    ToggleGroup role;
    Function<Void, Void> function;

    public void init(Function<Void, Void> function) {
        this.function = function;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));

        Label fullNameLabel = new Label("Full Name");
        Label usernameLabel = new Label("Username");
        Label emailLabel = new Label("Email");
        Label phoneLabel = new Label("Phone Number");
        Label addressLabel = new Label("Address");
        Label passwordLabel = new Label("Password");
        Label roleLabel = new Label("Role");

        fullName = new TextField();
        fullName.setMaxHeight(10);
        username = new TextField();
        username.setMaxHeight(10);
        email = new TextField();
        email.setMaxHeight(10);
        phone = new TextField();
        phone.setMaxHeight(10);
        address = new TextField();
        address.setMaxHeight(10);
        password = new TextField();
        password.setMaxHeight(10);

        role = new ToggleGroup();

        RadioButton dependent = new RadioButton(Role.DEPENDENT.name());
        dependent.setToggleGroup(role);
        dependent.setUserData(Role.DEPENDENT.name());
        dependent.setSelected(true);
        RadioButton policyOwner = new RadioButton(Role.POLICY_OWNER.name());
        policyOwner.setUserData(Role.POLICY_OWNER.name());
        policyOwner.setToggleGroup(role);
        RadioButton policyHolder = new RadioButton(Role.POLICY_HOLDER.name());
        policyHolder.setUserData(Role.POLICY_HOLDER.name());
        policyHolder.setToggleGroup(role);
        RadioButton assuranceSurveyor = new RadioButton(Role.ASSURANCE_SURVEYOR.name());
        assuranceSurveyor.setUserData(Role.ASSURANCE_SURVEYOR.name());
        assuranceSurveyor.setToggleGroup(role);
        RadioButton assuranceManager = new RadioButton(Role.ASSURANCE_MANAGER.name());
        assuranceManager.setUserData(Role.ASSURANCE_MANAGER.name());
        assuranceManager.setToggleGroup(role);

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
        grid.add(passwordLabel, 0, 5);
        grid.add(password, 1, 5);
        grid.add(roleLabel, 0, 6);

        if (auth.getUser().getRole() == Role.ADMIN) {
            HBox hBox = new HBox(dependent, policyHolder, policyOwner, assuranceSurveyor, assuranceManager);
            grid.add(hBox, 1, 6);
        } else {
            HBox hBox = new HBox(dependent, policyHolder);
            grid.add(hBox, 1, 6);
        }

        Button add = new Button("Add User");
        add.setOnAction(this::addUser);
        grid.add(add, 0,7);
    }


    private void addUser(ActionEvent event) {
        User user = new User(username.getText(), password.getText(), fullName.getText(), email.getText(), phone.getText(), address.getText(), Role.valueOf(role.getSelectedToggle().getUserData().toString()));
        try {
            userService.addUser(user);
        } catch (UnauthorizedException e) {
            throw new RuntimeException(e);
        }
        ((Stage) username.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }

}
