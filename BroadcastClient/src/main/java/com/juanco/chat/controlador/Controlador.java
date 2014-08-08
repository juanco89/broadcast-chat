
package com.juanco.chat.controlador;

import com.juanco.chat.comm.ObservadorCom;

/**
 * Controlador de la aplicación.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Controlador {
    
    private ObservadorCom vista;
    
    public Controlador(ObservadorCom observardor) {
        vista = observardor;
        
        // TODO: Crear instancia de comunicacion
    }
    
    public boolean conectar(String host, int puerto) {
        return false;
    }
    
    public boolean desconectar() {
        return false;
    }
    
    public boolean enviarMensaje() {
        return false;
    }
}
