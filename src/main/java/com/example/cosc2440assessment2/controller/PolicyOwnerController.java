package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.error.UnauthorizedException;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.Dependent;
import com.example.cosc2440assessment2.model.user.PolicyOwner;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PolicyOwnerController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();
    private Dependent dependent = new Dependent("dependent", "dependent", "dependent", "dependent@gmail.com", "4567890", "3456 FGHJK");

    public ListView<Claim> myclaims;
    public ListView<Customer> mybeneficiaries;
    public ListView<Claim> mybeneficiariesclaims;

    public PolicyOwnerController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Customer> dependents = new ArrayList<>();
        dependents.add(dependent);
        ObservableList<Customer> observableList = FXCollections.observableList(dependents);
        mybeneficiaries.setItems(observableList);
        mybeneficiaries.setOnMouseClicked(event -> {
            Customer selected = mybeneficiaries.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    showInfo(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addClaim(ActionEvent event) {
    }

    public void addBeneficiary(ActionEvent event) {
    }

    public void addBeneficiaryClaim(ActionEvent event) {
    }

    private void showInfo(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info_modal.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        InfoModal controller = loader.getController();
        controller.init(user);
        dialog.show();
    }
}