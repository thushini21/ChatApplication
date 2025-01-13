package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Sever.fxml"))));
            stage.setTitle("Server Form");
            stage.centerOnScreen();
            stage.show();

            Stage stage2 = new Stage();

            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Client.fxml"))));
            stage2.setTitle("Client Form");
            stage2.centerOnScreen();
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}