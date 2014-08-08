
package com.juanco.chat.controlador;

import com.juanco.chat.comm.Servidor;

/**
 * Controlador de la aplicación.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Controlador {
    
    // Instancia del servidor
    private final Servidor servidor;
    
    public Controlador() {
        int puerto = 6655;
        
        servidor = new Servidor(puerto);
    }
    
    public boolean iniciarServidor() {
        servidor.start();
        return servidor.isServerRunning();
    }
    
    public boolean detenerServidor() {
        servidor.detener();
        return !servidor.isServerRunning();
    }
}
