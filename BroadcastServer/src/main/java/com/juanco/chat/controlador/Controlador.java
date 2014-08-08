
package com.juanco.chat.controlador;

import com.juanco.chat.comm.Servidor;
import com.juanco.chat.ui.VentanaServidor;

/**
 * Controlador de la aplicación.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Controlador {
    
    // Instancia del servidor
    private final Servidor servidor;
    private final VentanaServidor vista;
    
    public Controlador() {
        vista = new VentanaServidor();
        vista.setVisible(true);
        
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
