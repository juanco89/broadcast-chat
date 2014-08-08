
package com.juanco.chat.comm;

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
    
    public AsistenteServidor(Socket socket) {
        this.socket = socket;
        
        // TODO: Obtener streams
    }
    
    @Override
    public void run() {
        // La ejecucion en paralelo está encargada de la escucha por el socket.
    }
    
    public void enviar(String mensaje) {
        
    }
    
    public Socket getSocket() {
        return this.socket;
    }
}
