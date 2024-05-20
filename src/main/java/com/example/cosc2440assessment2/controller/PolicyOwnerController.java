package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.Dependent;
import com.example.cosc2440assessment2.model.user.PolicyHolder;
import com.example.cosc2440assessment2.model.user.PolicyOwner;
import com.example.cosc2440assessment2.service.ClaimService;
import com.example.cosc2440assessment2.service.ModalService;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PolicyOwnerController implements Initializable {
    private final UserService userService = new UserService();
    private final ClaimService claimService = new ClaimService();
    private final Auth auth = Auth.getInstance();
    public Label yearlyCost;

    public ListView<String> myclaims;
    public ListView<Customer> mybeneficiaries;
    public ListView<Claim> mybeneficiariesclaims;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateMyClaims(null);
        List<Customer> dependents = new ArrayList<>();
        ObservableList<Customer> observableList = FXCollections.observableList(dependents);
        mybeneficiaries.setItems(observableList);
        mybeneficiaries.setOnMouseClicked(event -> {
            Customer selected = mybeneficiaries.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    ModalService.showInfo(selected, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addClaim(ActionEvent event) {
        try {
            ModalService.showAddClaim(new PolicyOwner(auth.getUser()), this::updateMyClaims);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBeneficiary(ActionEvent event) {
    }

    public void addBeneficiaryClaim(ActionEvent event) {
    }

    public void calculateYearlyCost(ActionEvent event) {
        yearlyCost.setText("3545");
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
            if (sSelected != null) {
                try {
                    Claim selected = claims.get(claimStrings.indexOf(sSelected));

                    ModalService.showClaim(selected, this::updateMyClaims);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return null;
    }

}