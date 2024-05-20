/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.user.*;
import com.example.cosc2440assessment2.service.ClaimService;
import com.example.cosc2440assessment2.service.ModalService;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    public ListView<String> mybeneficiaries;
    public ListView<String> mybeneficiariesclaims;

    public Button addBeneficiaryClaim;
    public Button updateBeneficiaryInfo;


    User selected;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBeneficiaryClaim.setDisable(true);
        updateBeneficiaryInfo.setDisable(true);
        updateMyClaims(null);
        updateBeneficiaries(null);
        updateMyBeneficiaryClaims(null);
    }

    public void addClaim(ActionEvent event) {
        try {
            ModalService.showAddClaim(new PolicyOwner(auth.getUser()), this::updateMyClaims);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBeneficiary(ActionEvent event) throws IOException {
        ModalService.showAddUser(this::updateBeneficiaries);
    }

    public void addBeneficiaryClaim(ActionEvent event) throws IOException {
        ModalService.showAddClaim(new Customer(selected), this::updateMyBeneficiaryClaims);
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

    public Void updateBeneficiaries(Void t) {
        List<User> beneficiaries = userService.getBeneficiariesByOwner(auth.getUser());
        List<String> beneficiariesString = new ArrayList<>();
        beneficiaries.forEach(d -> {
            beneficiariesString.add(d.toString());
        });
        mybeneficiaries.setItems(FXCollections.observableList(beneficiariesString));
        mybeneficiaries.setOnMouseClicked(event -> {
            String sSelected = mybeneficiaries.getSelectionModel().getSelectedItem();
            if (sSelected != null) {
                selected = beneficiaries.get(beneficiariesString.indexOf(sSelected));
                addBeneficiaryClaim.setDisable(false);
                updateBeneficiaryInfo.setDisable(false);
            }
        });
        return null;
    }

    public Void updateMyBeneficiaryClaims(Void t) {
        List<Claim> beneficiariesclaims = claimService.getBeneficiariesClaimsByOwner(auth.getUser());
        List<String> claimStrings = new ArrayList<>();
        beneficiariesclaims.forEach(claim -> {
            claimStrings.add(claim.toString());
        });
        mybeneficiariesclaims.setItems(FXCollections.observableList(claimStrings));
        mybeneficiariesclaims.setOnMouseClicked(mouseEvent -> {
            String sSelected = mybeneficiariesclaims.getSelectionModel().getSelectedItem();
            if (sSelected != null) {
                try {
                    Claim selected = beneficiariesclaims.get(claimStrings.indexOf(sSelected));
                    ModalService.showClaim(selected, this::updateMyBeneficiaryClaims);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return null;
    }

    public void updateBeneficiaryInfo(ActionEvent event) throws IOException {
        ModalService.showInfo(selected, this::updateBeneficiaries);
    }
}