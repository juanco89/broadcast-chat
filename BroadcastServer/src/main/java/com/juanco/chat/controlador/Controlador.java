
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
    private Servidor servidor;
    private final VentanaServidor vista;
    
    public Controlador() {
        vista = new VentanaServidor(this);
        vista.setVisible(true);
    }
    
    public boolean iniciarServidor(int puerto) {
        if(servidor == null || !servidor.isAlive()) {
            servidor = new Servidor(puerto);
            servidor.start();
            return servidor.isServerRunning();
        }
        return false;
    }
    
    public boolean detenerServidor() {
        if(servidor != null) {
            servidor.detener();
            return !servidor.isServerRunning();
        }
        return false;
    }
}
