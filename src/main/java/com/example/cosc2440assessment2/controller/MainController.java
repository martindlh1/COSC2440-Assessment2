package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.command.HelpCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.exec(null);
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}