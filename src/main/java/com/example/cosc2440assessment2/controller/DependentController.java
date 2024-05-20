/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.service.ClaimService;
import com.example.cosc2440assessment2.service.ModalService;
import com.example.cosc2440assessment2.service.UserService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DependentController implements Initializable {
    private final UserService userService = new UserService();
    private final ClaimService claimService = new ClaimService();
    private final Auth auth = Auth.getInstance();

    public ListView<String> myclaims;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Claim> claims = claimService.getClaimsByUsername(auth.getUser().getUsername());
        List<String> claimStrings = new ArrayList<>();
        claims.forEach(claim -> {
            claimStrings.add(claim.toString());
        });
        ObservableList<String> observableList = FXCollections.observableList(claimStrings);

        myclaims.getItems().clear();
        myclaims.setItems(observableList);
        myclaims.setPrefHeight(claims.size() * 10 + 30);
        myclaims.setOnMouseClicked(event -> {
            String sSelected = myclaims.getSelectionModel().getSelectedItem();
            Claim selected = claims.get(claimStrings.indexOf(sSelected));
            if (selected != null) {
                try {
                    ModalService.showClaim(selected, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}