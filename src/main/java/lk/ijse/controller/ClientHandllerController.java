package lk.ijse.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandllerController implements Runnable{
    String msg=" ";
    private  String name;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;

    private ArrayList<ClientHandllerController> servers;

    public ClientHandllerController(Socket socket, ArrayList<ClientHandllerController> clients) throws IOException {
        this.socket = socket;
        this.servers = clients;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

        System.out.println(name);
    }

    @Override
    public void run() {
        try {
            while (!msg.equals("stop")) {
                name =dataInputStream.readUTF();
                msg = dataInputStream.readUTF();
                System.out.println(msg);
                for (ClientHandllerController client : servers) {
                    System.out.println(client.socket.getPort());
                    System.out.println(socket.getPort());
                    System.out.println(client.name);
                    System.out.println(name + ": " + msg);
                    if(client.socket.getPort() != socket.getPort()) {

                        client.dataOutputStream.writeUTF(name + ":" + msg);
                        dataOutputStream.flush();
                    }
                }
//                dataOutputStream.writeUTF("Nimashi");
//                dataOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }
}
