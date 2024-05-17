package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
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
    private final Auth auth = Auth.getInstance();
    private Claim claim = new Claim(1, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 34, null, ClaimState.APPROVED);
    private Claim claim2 = new Claim(2, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 56, null, ClaimState.REFUSED);

    public ListView<Claim> myclaims;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Claim> claims = new ArrayList<>();
        claims.add(claim);
        claims.add(claim2);
        ObservableList<Claim> observableList = FXCollections.observableList(claims);

        myclaims.getItems().clear();
        myclaims.setItems(observableList);
        myclaims.setPrefHeight(claims.size() * 10 + 30);
        myclaims.setOnMouseClicked(event -> {
            Claim selected = myclaims.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    showClaim(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void showClaim(Claim claim) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/claim.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        ClaimController controller = loader.getController();
        controller.init(claim);
        dialog.show();
    }
}