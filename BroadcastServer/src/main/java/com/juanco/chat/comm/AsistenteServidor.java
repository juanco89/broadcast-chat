
package com.juanco.chat.comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Procesa las solicitudes de un cliente conectado.
 * 
 * Esta clase recibe un cliente conectado (socket TCP) y procesa
 * sus solicitudes, encargandose de establecer y mantener la comunicación.
 * 
 * La tarea de esta clase va enfocada a liberar lo antes posible el servidor
 * y permitir que esté disponible para la conexión de otro cliente.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class AsistenteServidor implements Runnable {

    private final Socket socket;
    
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public AsistenteServidor(Socket socket) {
        this.socket = socket;
        
        try {
            in = new ObjectInputStream(this.socket.getInputStream());
            out = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            in = null;
            out = null;
        }
    }
    
    @Override
    public void run() {
        while(socket.isConnected()) {
            try {
                String mensaje = in.readUTF();
                
                // TODO: Notificar del mensaje recibido.
            } catch (IOException ex) { }
        }
    }
    
    public void enviar(String mensaje) {
        try {
            out.writeUTF(mensaje);
        } catch (IOException ex) { }
    }
    
    public Socket getSocket() {
        return this.socket;
    }
}
