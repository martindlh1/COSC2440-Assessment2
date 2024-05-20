package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.Dependent;
import com.example.cosc2440assessment2.model.user.PolicyHolder;
import com.example.cosc2440assessment2.model.user.User;
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
    private List<User> dependents;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateMyClaims(null);
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
                    ModalService.showClaim(selected, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addClaim(ActionEvent event) {
        try {
            ModalService.showAddClaim(new PolicyHolder(auth.getUser()), this::updateMyClaims);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addDependentClaim(ActionEvent event) {
        try {
            ModalService.showUserList(dependents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Void updateMyClaims(Void t) {
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
                    ModalService.showClaim(selected, this::updateMyClaims);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return null;
    }
}