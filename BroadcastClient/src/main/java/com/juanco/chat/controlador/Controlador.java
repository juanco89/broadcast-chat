
package com.juanco.chat.controlador;

import com.juanco.chat.comm.ClienteComm;
import com.juanco.chat.comm.ObservadorCom;

/**
 * Controlador de la aplicación.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Controlador {
    
    private ObservadorCom vista;
    private ClienteComm cliente;
    
    public Controlador(ObservadorCom observardor) {
        vista = observardor;
    }
    
    public boolean conectar(String host, int puerto) {
        if(cliente == null) {
            cliente = new ClienteComm(host, puerto, vista);
            cliente.conectar();
            new Thread(cliente).start();
            return true;
        }
        return false;
    }
    
    public boolean desconectar() {
        return false;
    }
    
    public boolean enviarMensaje() {
        return false;
    }
}
