
package com.juanco.chat.observador;

/**
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public interface ObservadorServidor {
    
    public void servidorIniciado();
    
    public void nuevoClienteConectado();
    
    public void clienteDesconectado();
    
    public void nuevoMensajeRecibido();
    
    public void conexionTerminada();
}
