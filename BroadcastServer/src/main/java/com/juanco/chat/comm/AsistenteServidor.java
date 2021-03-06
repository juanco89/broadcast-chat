
package com.juanco.chat.comm;

import com.juanco.chat.util.Logg;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private DataInputStream in;
    private DataOutputStream out;
    
    private final Servidor servidor;
    
    private String nickname;
    
    public AsistenteServidor(Socket socket, Servidor padre) {
        this.socket = socket;
        
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            // Recibir el nickname.
            nickname = in.readUTF();
        } catch (IOException e) {
            in = null;
            out = null;
        }
        this.servidor = padre;
    }
    
    @Override
    public void run() {
        while(!socket.isClosed() && !socket.isInputShutdown()) {
            try {
                String mensaje = in.readUTF();
                Logg.registrar(nickname + " dice: " + mensaje);
                if(servidor != null) servidor.difundirMensaje(mensaje, this);
            } catch (IOException ex) {
                Logg.registrar(ex.getMessage());
                if(ex instanceof EOFException) {
                    try {
                        socket.close();
                    } catch (IOException ex1) { }
                    break;
                }
            }
        }
        servidor.eliminarClienteConectado(this);
    }
    
    public void detener() {
        if(socket.isConnected()) {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) { 
                Logg.registrar(ex.getLocalizedMessage());
            }
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
    
    public String getNickname() {
        return this.nickname;
    }
}
