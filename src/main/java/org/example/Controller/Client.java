package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    @FXML
    private JFXButton btnsend;

    @FXML
    private TextArea clienttextarea;

    @FXML
    private TextField clienttextfield;


    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";


    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 4000);
                clienttextarea.appendText("Server connected");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    clienttextarea.appendText("\nServer: " + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    void sendBtnOnAction(ActionEvent event) throws IOException {


        String message2 = clienttextfield.getText();

        dataOutputStream.writeUTF(message2);
        dataOutputStream.flush();


        clienttextarea.appendText("Client: " + message2);

        clienttextfield.clear();
    }
}
