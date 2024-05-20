/**
 * @author <Team 8>
 */
package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.UserFilter;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserFilterModal {
    public GridPane grid;
    private final Auth auth = Auth.getInstance();

    public void init(Function<UserFilter, Void> setFilter) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));

        Label role = new Label("Role");
        CheckBox dependent = new CheckBox("DEPENDENT");
        CheckBox policyHolder = new CheckBox("POLICY_HOLDER");
        CheckBox policyOwner = new CheckBox("POLICY_OWNER");
        CheckBox assuranceManager = new CheckBox("ASSURANCE_MANAGER");
        CheckBox assuranceSurveyor = new CheckBox("ASSURANCE_SURVEYOR");

        Button applyFilter = new Button("Apply");
        applyFilter.setOnAction(event -> {
            List<Role> roles = new ArrayList<>();
            if (dependent.isSelected())
                roles.add(Role.DEPENDENT);
            if (policyHolder.isSelected())
                roles.add(Role.POLICY_HOLDER);
            if (policyOwner.isSelected())
                roles.add(Role.POLICY_OWNER);
            if (assuranceSurveyor.isSelected())
                roles.add(Role.ASSURANCE_SURVEYOR);
            if (assuranceManager.isSelected())
                roles.add(Role.ASSURANCE_MANAGER);
            setFilter.apply(new UserFilter(roles));
            ((Stage) applyFilter.getScene().getWindow()).close();
        });
        grid.add(role, 0, 0);
        grid.add(dependent, 1, 0);
        grid.add(policyHolder, 2, 0);
        grid.add(policyOwner, 3, 0);
        grid.add(applyFilter, 0, 1);
        if (auth.getUser().getRole() == Role.ADMIN) {
            grid.add(assuranceSurveyor, 4, 0);
            grid.add(assuranceManager, 5, 0);
        }
    }
}
