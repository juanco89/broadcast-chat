
package com.juanco.chat;

import com.juanco.chat.controlador.Controlador;

/**
 *
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Main {
    
    
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.iniciarServidor();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) { }
        
        controlador.detenerServidor();
    }
}
