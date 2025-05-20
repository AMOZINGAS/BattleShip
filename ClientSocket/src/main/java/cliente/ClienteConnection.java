/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Gestiona la coneción y desconexión del cliente
 * además de los streams
 * @author Beto_
 */
public class ClienteConnection {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected;

    
    public boolean connect(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            connected = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
            return false;
        }
    }

    public void disconnect() {
        try {
            if (connected) {
                socket.close();
                connected = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Socket getSocket() {
        return socket; 
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
    
    public boolean isConnected() {
        return connected; 
    }

    public void cerrar() throws IOException {
        socket.close();
    }
}
