
package com.juanco.chat.comm;

import com.juanco.chat.util.Logg;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase encargada de la conexión y la comunicación con el servidor.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class ClienteComm extends Thread {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    private final String host;
    private final int puerto;
    
    private final ObservadorCom observador;
    
    public ClienteComm(String host, int puerto, ObservadorCom observador) {
        this.host = host;
        this.puerto = puerto;
        this.observador = observador;
    }
    
    public void conectar(String nickname) {
        try {
            socket = new Socket(host, puerto);
            Logg.registrar("Conectado a " + socket.getRemoteSocketAddress());
            if(socket.isConnected()) {
                if(observador != null) observador.conexionExitosa();
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                enviar(nickname);
                this.start();
            }
        }catch(IOException e) {
            Logg.registrar(e.getLocalizedMessage());
        }
        
    }
    
    public boolean enviar(String mensaje) {
        try {
            out.writeUTF(mensaje);
            out.flush();
            return true;
        } catch (IOException ex) {
            Logg.registrar(ex.getLocalizedMessage());
        }
        return false;
    }
    
    @Override
    public void run() {
        while(socket != null && socket.isConnected()) {
            try {
                String mensaje = in.readUTF();
                if(observador != null) observador.nuevoMensajeRecibido(mensaje);
                
            } catch (IOException ex) {
                Logg.registrar(ex.getLocalizedMessage());
                
                if(ex instanceof EOFException) {
                    try {
                        socket.close();
                    } catch (IOException ex1) { }
                    break;
                }
            }
        }
        if(observador != null) observador.conexionTerminada();
    }
    
}
