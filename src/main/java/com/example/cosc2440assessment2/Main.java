package com.example.cosc2440assessment2;

import com.example.cosc2440assessment2.controller.ErrorController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(Main::showError);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        Main.stage = stage;
    }

    private static void showError(Thread t, Throwable e) {
        System.err.println("***Default exception handler***");
        if (Platform.isFxApplicationThread()) {
            showErrorDialog(e);
        } else {
            System.err.println("An unexpected error occurred in "+t);
        }
    }

    private static void showErrorDialog(Throwable e) {
        StringWriter errorMsg = new StringWriter();
        e.printStackTrace(new PrintWriter(errorMsg));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/error.fxml"));
        try {
            Parent root = loader.load();
            ((ErrorController)loader.getController()).setErrorText(errorMsg.toString());
            dialog.setScene(new Scene(root, 250, 400));
            dialog.show();
        } catch (IOException exc) {
            System.err.println(exc.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static void switchScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }
}