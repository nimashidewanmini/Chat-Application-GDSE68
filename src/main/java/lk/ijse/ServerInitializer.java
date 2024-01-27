package lk.ijse;

import lk.ijse.controller.ClientHandllerController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer  {
    private static ArrayList<ClientHandllerController> clients = new ArrayList<>();
    private static Socket socket;
    public static void main(String[] args) {
        System.out.println("Hello" + clients);
        try {
            ServerSocket serverSocket = new ServerSocket(5003);
            while(true){
                System.out.println("Server Listening");
                socket = serverSocket.accept();
                System.out.println("connect");

                ClientHandllerController clientHandllerController = new ClientHandllerController(socket,clients);
                Thread clientThread = new Thread(clientHandllerController);
                clients.add(clientHandllerController);
                System.out.println(clients);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
