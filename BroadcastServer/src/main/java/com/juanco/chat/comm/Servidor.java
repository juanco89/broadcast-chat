
package com.juanco.chat.comm;

import com.juanco.chat.observador.ObsevadorServidor;
import com.juanco.chat.util.Logg;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor encargado de escuchar un puerto determinado.
 * 
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class Servidor extends Thread {
    
    private final int puerto;
    private ServerSocket servidor;
    private boolean alive;
    private List<AsistenteServidor> clientesConectados;
    
    private ObsevadorServidor observador;
    
    public Servidor(int puerto) {
        this.puerto = puerto;
        clientesConectados = new ArrayList<>();
    }
    
    public void detener() {
        alive = false;
        try {
            servidor.close();
            Logg.registrar("[*] Deteniendo el servidor");
        } catch (IOException ex) { }
    }
    
    @Override
    public void run() {
        try {
            servidor = new ServerSocket(this.puerto);
            alive = true;
            Logg.registrar("[*] Servidor corriendo en el puerto " + this.puerto);
        } catch(IOException e) {
            Logg.registrar(e.getLocalizedMessage());
            alive = false;
        }
        
        while(alive) {
            try {
                Socket cliente = servidor.accept();
                Logg.registrar("[*] Cliente conectado");
                
                AsistenteServidor asistente = new AsistenteServidor(cliente);
                clientesConectados.add(asistente);
                
            }catch(IOException e) {
                Logg.registrar(e.getLocalizedMessage());
            }
        }
        
        // Liberar recursos
        try {
            servidor.close();
        } catch (IOException e) {
            Logg.registrar(e.getLocalizedMessage());
        }
    }
    
    public boolean isServerRunning() {
        return (servidor != null && !servidor.isClosed());
    }
    
    public void establecerObservador(ObsevadorServidor pObservador) {
        this.observador = pObservador;
    }
}
