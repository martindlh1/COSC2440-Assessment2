package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.service.ClaimService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.function.Function;

public class ClaimController {
    public GridPane grid;
    private Function<Void, Void> function;
    private final ClaimService claimService = new ClaimService();
    private final Auth auth = Auth.getInstance();
    private Claim claim;
    TextField name;
    TextField date;
    TextField insured;
    DatePicker examDate;
    TextField amount;
    TextField state;

    public void init(Claim claim, Function<Void, Void> function) {
        this.claim = claim;
        this.function = function;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));
        
        Label lName = new Label("Full Name");
        Label lDate = new Label("Date");
        Label lInsured = new Label("Insured");
        Label lExamDate = new Label("Exam Date");
        Label lAmount = new Label("Amount");
        Label lState = new Label("State");


        name = new TextField(claim.getInsured_name() != null ? claim.getInsured_name() : "");
        name.setMaxHeight(10);
        name.setEditable(false);
        date = new TextField(claim.getDate().toString());
        date.setMaxHeight(10);
        date.setEditable(false);
        insured = new TextField(claim.getInsured() == null ? "N/A" : claim.getInsured().toString());
        insured.setMaxHeight(10);
        insured.setEditable(false);
        examDate = new DatePicker();
        if (claim.getExam_date() != null)
            examDate.setValue(claim.getExam_date().toLocalDate());
        examDate.setMaxHeight(10);
        if (auth.getUser().getRole() == Role.DEPENDENT)
            examDate.setEditable(false);
        amount = new TextField(claim.getAmount() == null ? "N/A": claim.getAmount().toString());
        amount.setMaxHeight(10);
        if (auth.getUser().getRole() == Role.DEPENDENT)
            amount.setEditable(false);
        state = new TextField(claim.getState().name());
        state.setEditable(false);
        state.setMaxHeight(10);

        if (claim.getInsured_name() != null) {
            grid.add(lName, 0, 0);
            grid.add(name, 1, 0);
        }
        grid.add(lDate, 0, 1);
        grid.add(date, 1, 1);
        grid.add(lInsured, 0, 2);
        grid.add(insured, 1, 2);
        grid.add(lExamDate, 0, 3);
        grid.add(examDate, 1, 3);
        grid.add(lAmount, 0, 4);
        grid.add(amount, 1, 4);
        grid.add(lState, 0, 5);
        grid.add(state, 1, 5);

        switch (auth.getUser().getRole()) {
            case ASSURANCE_SURVEYOR -> {
                if (claim.getState() == ClaimState.PENDING) {
                    Button propose = new Button();
                    propose.setOnAction(this::propose);
                    propose.setText("Propose to Manager");
                    grid.add(propose, 0, 6);
                    Button require = new Button();
                    require.setOnAction(this::requireMoreInfo);
                    require.setText("Require more info");
                    grid.add(require, 1, 6);
                }
            }
            case ASSURANCE_MANAGER -> {
                if (claim.getState() == ClaimState.PROPOSED) {
                    Button approve = new Button();
                    approve.setText("Approve");
                    approve.setOnAction(this::approve);
                    grid.add(approve, 0, 6);
                    Button refuse = new Button();
                    refuse.setOnAction(this::refuse);
                    refuse.setText("Refuse");
                    grid.add(refuse, 1, 6);
                }
            }
            case POLICY_OWNER -> {
                Button update = new Button();
                update.setText("Update");
                update.setOnAction(this::update);
                grid.add(update, 0, 6);
                Button delete = new Button();
                delete.setOnAction(this::delete);
                delete.setText("Delete");
                grid.add(delete, 1, 6);
            }
            case POLICY_HOLDER -> {
                Button update = new Button();
                update.setOnAction(this::update);
                update.setText("Update");
                grid.add(update, 0, 6);
            }
        }
    }
    
    public void update(ActionEvent event) {
        claim.setExam_date(examDate == null ? null : java.sql.Date.valueOf(examDate.getValue()));
        claim.setAmount(Integer.parseInt(amount.getText()));
        claimService.updateClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
    public void delete(ActionEvent event) {
        claimService.deleteClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
    public void propose(ActionEvent event) {
        claim.setState(ClaimState.PROPOSED);
        claimService.updateClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
    public void approve(ActionEvent event) {
        claim.setState(ClaimState.APPROVED);
        claimService.updateClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
    public void refuse(ActionEvent event) {
        claim.setState(ClaimState.REFUSED);
        claimService.updateClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
    public void requireMoreInfo(ActionEvent event) {
        claim.setState(ClaimState.MORE_INFO_REQUIRED);
        claimService.updateClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
}
