package org.example.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Sever {


    @FXML
    private Button button;

    @FXML
    private TextField severtextfield;

    @FXML
    private TextArea severtxtarea;

    Socket socket;

    ServerSocket serverSocket;

    DataInputStream dataInputStream;

    DataOutputStream dataOutputStream;

    String message = "";


    @FXML
    void btnSendonaction(ActionEvent event) throws IOException {
        String message = severtextfield.getText();
        severtxtarea.appendText("Server: ");
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }


    public void initialize() {
        new Thread(() -> {

            try {
                serverSocket = new ServerSocket(4000);
                severtxtarea.appendText("Server Started");
                socket = serverSocket.accept();
                severtxtarea.appendText("\nClient Accepted");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")) {


                    message = dataInputStream.readUTF();
                    severtxtarea.appendText(message);

                    severtxtarea.appendText("\nClient: " + message);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}