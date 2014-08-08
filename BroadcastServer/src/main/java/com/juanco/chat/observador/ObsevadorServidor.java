
package com.juanco.chat.observador;

/**
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public interface ObsevadorServidor {
    
    public void nuevoClienteConectado();
    
    public void nuevoMensajeRecibido();
    
    public void conexionTerminada();
}
