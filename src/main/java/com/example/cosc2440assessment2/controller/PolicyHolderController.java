/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.error.UnauthorizedException;
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
import javafx.scene.control.Button;
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
    User selected;

    public ListView<String> mydependents;
    public ListView<String> myclaims;
    public ListView<String> mydependentsclaims;
    public Button addDependentClaim;
    public Button updateDependentInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDependentClaim.setDisable(true);
        updateDependentInfo.setDisable(true);

        updateMyClaims(null);
        updateDependents(null);
        updateMyDependentsClaims(null);
    }

    public void addClaim(ActionEvent event) {
        try {
            ModalService.showAddClaim(new PolicyHolder(auth.getUser()), this::updateMyClaims);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Void updateDependents(Void t) {
        List<User> dependents = userService.getDependentsByHolder(auth.getUser());
        List<String> dependentsString = new ArrayList<>();
        dependents.forEach(d -> {
            dependentsString.add(d.toString());
        });
        mydependents.setItems(FXCollections.observableList(dependentsString));
        mydependents.setOnMouseClicked(event -> {
            String sSelected = mydependents.getSelectionModel().getSelectedItem();
            if (sSelected != null) {
                selected = dependents.get(dependentsString.indexOf(sSelected));
                addDependentClaim.setDisable(false);
                updateDependentInfo.setDisable(false);
            }
        });
        return null;
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

    public Void updateMyDependentsClaims(Void t) {
        List<Claim> dependentsclaims = claimService.getDependentsClaimsByHolder(auth.getUser());
        List<String> claimStrings = new ArrayList<>();
        dependentsclaims.forEach(claim -> {
            claimStrings.add(claim.toString());
        });
        mydependentsclaims.setItems(FXCollections.observableList(claimStrings));
        mydependentsclaims.setOnMouseClicked(mouseEvent -> {
            String sSelected = mydependentsclaims.getSelectionModel().getSelectedItem();
            if (sSelected != null) {
                try {
                    Claim selected = dependentsclaims.get(claimStrings.indexOf(sSelected));
                    ModalService.showClaim(selected, this::updateMyDependentsClaims);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return null;
    }

    public void updateDependentInfo() throws IOException {
        ModalService.showInfo(selected, this::updateDependents);
    }

    public void addDependentClaim() throws IOException {
        ModalService.showAddClaim(new Dependent(selected), this::updateMyDependentsClaims);
    }
}