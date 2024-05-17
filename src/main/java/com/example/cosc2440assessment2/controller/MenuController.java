package com.example.cosc2440assessment2.controller;

import com.example.cosc2440assessment2.Main;
import com.example.cosc2440assessment2.singleton.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private final Auth auth = Auth.getInstance();
    public MenuBar menu;
    public HBox hbox;

    public void showInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info_modal.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        InfoModal controller = loader.getController();
        controller.init(auth.getUser());
        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.setAlignment(Pos.TOP_LEFT);
        menu.prefWidthProperty().bind(Main.stage.widthProperty());
    }

    public void logout(ActionEvent event) throws IOException {
        auth.logout();
        Main.switchScene("/fxml/login.fxml");
    }

    public void displayHistory(ActionEvent event) {
    }
}
