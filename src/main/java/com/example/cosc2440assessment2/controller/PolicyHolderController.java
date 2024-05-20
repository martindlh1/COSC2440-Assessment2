package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.user.Dependent;
import com.example.cosc2440assessment2.service.ClaimService;
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
    private final ClaimService claimService = new ClaimService();

    public ListView<Dependent> mydependents;
    public ListView<String> myclaims;
    public ListView<String> mydependentsclaims;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Claim> claims = claimService.getClaimsByUsername(auth.getUser().getUsername());
        List<String> claimStrings = new ArrayList<>();
        claims.forEach(claim -> {
            claimStrings.add(claim.toString());
        });
        myclaims.setItems(FXCollections.observableList(claimStrings));
        myclaims.setOnMouseClicked(mouseEvent -> {
            String sSelected = myclaims.getSelectionModel().getSelectedItem();
            Claim selected = claims.get(claimStrings.indexOf(sSelected));
            if (selected != null) {
                try {
                    ModalService.showClaim(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        List<Dependent> dependents = new ArrayList<>();
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

        mydependentsclaims.setOnMouseClicked(event -> {
            Claim selected = null;
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