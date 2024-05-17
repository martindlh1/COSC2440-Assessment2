package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.model.Claim;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class ClaimController {
    public GridPane grid;

    public void init(Claim claim) {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25,25,25,25));
        
        Label lId = new Label("Id");
        Label lDate = new Label("Date");
        Label lInsured = new Label("Insured");
        Label lExamDate = new Label("Exam Date");
        Label lAmount = new Label("Amount");
        Label lState = new Label("State");

        TextArea id = new TextArea(claim.getId().toString());
        id.setMaxHeight(10);
        TextArea date = new TextArea(claim.getDate().toString());
        date.setMaxHeight(10);
        TextArea insured = new TextArea("claim.getInsured().toString()");
        insured.setMaxHeight(10);
        TextArea examDate = new TextArea(claim.getExam_date().toString());
        examDate.setMaxHeight(10);
        TextArea amount = new TextArea(claim.getAmount().toString());
        amount.setMaxHeight(10);
        TextArea state = new TextArea(claim.getState().name());
        state.setMaxHeight(10);

        grid.add(lId, 0, 0);
        grid.add(id, 1, 0);
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
    }
}
