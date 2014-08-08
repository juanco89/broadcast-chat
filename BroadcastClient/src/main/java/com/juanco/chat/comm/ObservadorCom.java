
package com.juanco.chat.comm;

/**
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public interface ObservadorCom {
    
    public void conexionExitosa();
    
    public void nuevoMensajeRecibido(String msn);
    
    public void conexionTerminada();
}
