package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimState;
import com.example.cosc2440assessment2.model.InsuranceCard;
import com.example.cosc2440assessment2.model.Role;
import com.example.cosc2440assessment2.model.user.Customer;
import com.example.cosc2440assessment2.model.user.User;
import com.example.cosc2440assessment2.service.ClaimService;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.function.Function;

public class AddClaimController {
    private final ClaimService claimService = new ClaimService();
    private Customer customer;
    private Function<Void, Void> function;
    public GridPane grid;
    TextField date;
    TextField insured;
    DatePicker examDate;
    TextField amount;

    public void init(Customer customer, Function<Void, Void> function) {
        this.function = function;
        this.customer = customer;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));

        Label lDate = new Label("Date");
        Label lInsured = new Label("Insured");
        Label lExamDate = new Label("Exam Date");
        Label lAmount = new Label("Amount");

        date = new TextField(new Date(System.currentTimeMillis()).toString());
        date.setMaxHeight(10);
        date.setEditable(false);
        insured = new TextField(customer.getFullName());
        insured.setMaxHeight(10);
        insured.setEditable(false);
        examDate = new DatePicker();
        examDate.setMaxHeight(10);
        amount = new TextField();

        grid.add(lDate, 0, 0);
        grid.add(date, 1, 0);
        grid.add(lInsured, 0, 1);
        grid.add(insured, 1, 1);
        grid.add(lExamDate, 0, 2);
        grid.add(examDate, 1, 2);
        grid.add(lAmount, 0, 3);
        grid.add(amount, 1, 3);

        Button add = new Button("Add Claim");
        add.setOnAction(this::addClaim);
        grid.add(add, 0, 4);
    }

    public void addClaim(ActionEvent event) {
        Claim claim = new Claim(new Date(System.currentTimeMillis()), customer.getId(), customer.getFullName(), java.sql.Date.valueOf(examDate.getValue()), new InsuranceCard(1), null, Integer.valueOf(amount.getText()), null, ClaimState.PENDING);
        claimService.addClaim(claim);
        ((Stage) grid.getScene().getWindow()).close();
        if (function != null)
            function.apply(null);
    }
}
