package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.user.Dependent;
import com.example.cosc2440assessment2.service.ModalService;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PolicyHolderController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();
    private Dependent dependent = new Dependent("dependent", "dependent", "dependent", "dependent@gmail.com", "4567890", "3456 FGHJK");
    private Claim claim = new Claim(1, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 34, null, ClaimState.APPROVED);
    private Claim claim2 = new Claim(2, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 56, null, ClaimState.REFUSED);


    public ListView<Dependent> mydependents;
    public ListView<Claim> myclaims;
    public ListView<Claim> mydependentsclaims;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Claim> claims = new ArrayList<>();
        claims.add(claim);
        claims.add(claim2);
        myclaims.setItems(FXCollections.observableList(claims));
        myclaims.setOnMouseClicked(mouseEvent -> {
            Claim selected = myclaims.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    ModalService.showClaim(claim);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        List<Dependent> dependents = new ArrayList<>();
        dependents.add(dependent);
        mydependents.setItems(FXCollections.observableList(dependents));
        mydependents.setOnMouseClicked(event -> {
            Dependent selected = mydependents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    ModalService.showInfo(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        myclaims.setOnMouseClicked(event -> {
            Claim selected = myclaims.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    ModalService.showClaim(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mydependentsclaims.setOnMouseClicked(event -> {
            Claim selected = myclaims.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    ModalService.showClaim(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addClaim(ActionEvent event) {
    }

    public void addDependentClaim(ActionEvent event) {
    }
}