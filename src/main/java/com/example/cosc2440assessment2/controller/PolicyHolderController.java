package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.Dependent;
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

public class PolicyHolderController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();
    private Dependent dependent = new Dependent("dependent", "dependent", "dependent", "dependent@gmail.com", "4567890", "3456 FGHJK");

    public ListView<Dependent> mydependents;
    public ListView<Claim> myclaims;
    public ListView<Claim> mydependentsclaims;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Dependent> dependents = new ArrayList<>();
        dependents.add(dependent);
        ObservableList<Dependent> observableList = FXCollections.observableList(dependents);
        mydependents.setItems(observableList);
        mydependents.setOnMouseClicked(event -> {
            Dependent selected = mydependents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    showInfo(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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

        mydependentsclaims.setOnMouseClicked(event -> {
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

    private void showInfo(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info_modal.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        InfoModal controller = loader.getController();
        controller.init(user);
        dialog.show();
    }

    public void addClaim(ActionEvent event) {
    }

    public void addDependentClaim(ActionEvent event) {
    }
}