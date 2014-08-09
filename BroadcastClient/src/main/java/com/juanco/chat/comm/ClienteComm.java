
package com.juanco.chat.comm;

import com.juanco.chat.util.Logg;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Clase encargada de la conexión y la comunicación con el servidor.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class ClienteComm implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    private final String host;
    private final int puerto;
    
    private final ObservadorCom observador;
    
    public ClienteComm(String host, int puerto, ObservadorCom observador) {
        this.host = host;
        this.puerto = puerto;
        this.observador = observador;
    }
    
    public void conectar() {
        try {
            socket = new Socket(host, puerto);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
        }catch(IOException e) {
            Logg.registrar(e.getLocalizedMessage());
        }
        
    }
    
    public boolean enviar(String mensaje) {
        try {
            out.writeUTF(mensaje);
            return true;
        } catch (IOException ex) { }
        return false;
    }
    
    @Override
    public void run() {
        while(socket != null && socket.isConnected()) {
            try {
                String mensaje = in.readUTF();
                if(observador != null) observador.nuevoMensajeRecibido(mensaje);
                
            } catch (IOException ex) { }
            
        }
    }
    
}
