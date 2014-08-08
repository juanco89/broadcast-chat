
package com.juanco.chat;

import com.juanco.chat.controlador.Controlador;
import com.juanco.chat.ui.ClienteBroadcast;

/**
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Main {
   
    public static void main(String[] args) {
        ClienteBroadcast ventana = new ClienteBroadcast();
        Controlador controlador = new Controlador(ventana);
        
        ventana.setControlador(controlador);
        ventana.setVisible(true);
    }
}
