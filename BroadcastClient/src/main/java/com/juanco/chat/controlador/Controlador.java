
package com.juanco.chat.controlador;

import com.juanco.chat.comm.ClienteComm;
import com.juanco.chat.comm.ObservadorCom;

/**
 * Controlador de la aplicación.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Controlador {
    
    private final ObservadorCom vista;
    private ClienteComm cliente;
    
    public Controlador(ObservadorCom observardor) {
        vista = observardor;
    }
    
    public boolean conectar(String host, int puerto, String nickname) {
        if(cliente == null) {
            cliente = new ClienteComm(host, puerto, vista);
            cliente.conectar(nickname);
            return true;
        }
        return false;
    }
    
    public boolean desconectar() {
        return false;
    }
    
    public boolean enviarMensaje(String mensaje) {
        if(cliente != null) {
            cliente.enviar(mensaje);
            return true;
        }
        return false;
    }
}
