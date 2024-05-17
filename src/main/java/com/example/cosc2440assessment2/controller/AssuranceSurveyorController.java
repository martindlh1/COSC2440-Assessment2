package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimFilter;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.UserFilter;
import com.example.cosc2440assessment2.model.user.Customer;
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

public class AssuranceSurveyorController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();
    private Claim claim = new Claim(1, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 34, null, ClaimState.APPROVED);
    private Claim claim2 = new Claim(2, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 56, null, ClaimState.PROPOSED);
    private Claim claim3 = new Claim(2, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 56, null, ClaimState.REFUSED);
    private Claim claim4 = new Claim(2, new Date(2024, 5, 17), null, new Date(2024, 5, 17), null, new String[]{}, 56, null, ClaimState.MORE_INFO_REQUIRED);

    public ListView<Claim> claims;
    public ListView<Customer> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Claim> claimList = new ArrayList<>();
        claimList.add(claim);
        claimList.add(claim2);
        claimList.add(claim3);
        claimList.add(claim4);
        claims.setItems(FXCollections.observableList(claimList));
        claims.setOnMouseClicked(mouseEvent -> {
            Claim selected = claims.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    ModalService.showClaim(selected);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        List<Customer> userList = new ArrayList<>();
        users.setItems(FXCollections.observableList(userList));
    }

    private void updateClaimsList(ClaimFilter filter) {
        List<Claim> claimList = new ArrayList<>();
        claimList.add(claim);
        claimList.add(claim2);
        claimList.add(claim3);
        claimList.add(claim4);

        claims.setItems(FXCollections.observableList(claimList.stream().filter(c -> {
            if (filter.state.isEmpty())
                return true;
            if (filter.state.contains(c.getState()))
                return true;
            return false;
        }).toList()));
    }

    private void updateUserList(UserFilter filter) {
        List<Customer> userList = new ArrayList<>();

        users.setItems(FXCollections.observableList(userList.stream().filter(u -> {
            if (filter.roles.isEmpty())
                return true;
            if (filter.roles.contains(u.getRole()))
                return true;
            return false;
        }).toList()));
    }

    public void claimFilter(ActionEvent event) throws IOException {
        ModalService.showClaimFilter(this::setClaimFilter);
    }

    public void userFilter(ActionEvent event) throws IOException {
        ModalService.showUserFilter(this::setUserFilter);
    }

    public Void setClaimFilter(ClaimFilter filter) {
        updateClaimsList(filter);
        return null;
    }

    public Void setUserFilter(UserFilter filter) {
        updateUserList(filter);
        return null;
    }
}