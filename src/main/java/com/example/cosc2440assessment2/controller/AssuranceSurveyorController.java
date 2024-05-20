package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimFilter;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.UserFilter;
import com.example.cosc2440assessment2.model.user.Customer;
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

public class AssuranceSurveyorController implements Initializable {
    private final UserService userService = new UserService();
    private final Auth auth = Auth.getInstance();
    private final ClaimService claimService = new ClaimService();

    private UserFilter userFilter = null;
    private ClaimFilter claimFilter = null;
    public ListView<String> claims;
    public ListView<String> users;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateClaimsList(null);
        updateUserList(null);
    }

    private Void updateClaimsList(Void t) {
        List<Claim> claimList = claimService.getAllClaims();
        List<String> claimsString = new ArrayList<>();
        claimList.forEach(c -> {
            claimsString.add(c.toString());
        });
        List<String> filtered = claimsString.stream().filter(c -> {
            if (claimFilter == null)
                return true;
            if (claimFilter.state.isEmpty())
                return true;
            return claimFilter.state.contains(claimList.get(claimsString.indexOf(c)).getState());
        }).toList();
        claims.setOnMouseClicked(mouseEvent -> {
            String sSelected = claims.getSelectionModel().getSelectedItem();
            if (sSelected != null) {
                try {
                    Claim selected = claimList.get(claimsString.indexOf(sSelected));
                    ModalService.showClaim(selected, this::updateClaimsList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        claims.setItems(FXCollections.observableList(filtered));
        return null;
    }


    private Void updateUserList(Void t) {
        List<User> userList = userService.getAllCustomers();
        List<String> usersString = new ArrayList<>();
        userList.forEach(c -> {
            usersString.add(c.toString());
        });
        List<String> filtered = usersString.stream().filter(c -> {
            if (userFilter == null)
                return true;
            if (userFilter.roles.isEmpty())
                return true;
            return userFilter.roles.contains(userList.get(usersString.indexOf(c)).getRole());
        }).toList();
        users.setOnMouseClicked(mouseEvent -> {
            String sSelected = users.getSelectionModel().getSelectedItem();
            if (sSelected != null) {
                try {
                    User selected = userList.get(usersString.indexOf(sSelected));
                    ModalService.showInfo(selected , this::updateUserList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        users.setItems(FXCollections.observableList(filtered));
        return null;

    }

    public void claimFilter(ActionEvent event) throws IOException {
        ModalService.showClaimFilter(this::setClaimFilter);
    }

    public void userFilter(ActionEvent event) throws IOException {
        ModalService.showUserFilter(this::setUserFilter);
    }

    public Void setClaimFilter(ClaimFilter filter) {
        claimFilter = filter;
        updateClaimsList(null);
        return null;
    }

    public Void setUserFilter(UserFilter filter) {
        userFilter = filter;
        updateUserList(null);
        return null;
    }
}