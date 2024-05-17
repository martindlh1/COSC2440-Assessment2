package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.ClaimFilter;
import com.example.cosc2440assessment2.model.ClaimState;
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

public class ClaimFilterModal {
    public GridPane grid;

    public void init(Function<ClaimFilter, Void> setFilter) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));

        Button applyFilter = new Button("Apply");
        Label state = new Label("State");
        CheckBox proposed = new CheckBox("PROPOSED");
        CheckBox approved = new CheckBox("APPROVED");
        CheckBox refused = new CheckBox("REFUSED");
        CheckBox infoRequired = new CheckBox("INFO REQUIRED");
        applyFilter.setOnAction(event -> {
            List<ClaimState> states = new ArrayList<>();
            if (proposed.isSelected())
                states.add(ClaimState.PROPOSED);
            if (approved.isSelected())
                states.add(ClaimState.APPROVED);
            if (refused.isSelected())
                states.add(ClaimState.REFUSED);
            if (infoRequired.isSelected())
                states.add(ClaimState.MORE_INFO_REQUIRED);
            ClaimFilter claimFilter = new ClaimFilter(states);
            setFilter.apply(claimFilter);
            ((Stage) applyFilter.getScene().getWindow()).close();
        });
        grid.add(state, 0, 0);
        grid.add(proposed, 1, 0);
        grid.add(approved, 2, 0);
        grid.add(refused, 3, 0);
        grid.add(infoRequired, 4, 0);
        grid.add(applyFilter, 0, 1);
    }
}
