package com.example.cosc2440assessment2.service;

import com.example.cosc2440assessment2.controller.ClaimController;
import com.example.cosc2440assessment2.controller.ClaimFilterModal;
import com.example.cosc2440assessment2.controller.InfoModal;
import com.example.cosc2440assessment2.controller.UserFilterModal;
import com.example.cosc2440assessment2.model.Claim;
import com.example.cosc2440assessment2.model.ClaimFilter;
import com.example.cosc2440assessment2.model.UserFilter;
import com.example.cosc2440assessment2.model.user.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

public class ModalService {
    static public void showInfo(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModalService.class.getResource("/fxml/info_modal.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        InfoModal controller = loader.getController();
        controller.init(user);
        dialog.show();
    }

    static public void showClaim(Claim claim) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModalService.class.getResource("/fxml/claim.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        ClaimController controller = loader.getController();
        controller.init(claim);
        dialog.show();
    }

    static public void showClaimFilter(Function<ClaimFilter, Void> function) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModalService.class.getResource("/fxml/claim_filter.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        ClaimFilterModal controller = loader.getController();
        controller.init(function);
        dialog.show();
    }

    static public void showUserFilter(Function<UserFilter, Void> function) throws IOException {
        FXMLLoader loader = new FXMLLoader(ModalService.class.getResource("/fxml/user_filter.fxml"));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(loader.load()));
        UserFilterModal controller = loader.getController();
        controller.init(function);
        dialog.show();
    }

}
