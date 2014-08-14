
package com.juanco.chat.comm;

import com.juanco.chat.observador.ObservadorServidor;
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
    private boolean alive;
    private ServerSocket servidor;
    private final List<AsistenteServidor> clientesConectados;
    
    private ObservadorServidor observador;
    
    public Servidor(int puerto) {
        this.puerto = puerto;
        clientesConectados = new ArrayList<>();
    }
    
    public void detener() {
        alive = false;
        try {
            servidor.close();
            if(observador != null) observador.conexionTerminada();
            Logg.registrar("[*] Deteniendo el servidor");
        } catch (IOException ex) { }
    }
    
    @Override
    public void run() {
        try {
            servidor = new ServerSocket(this.puerto);
            alive = true;
            if(observador != null) observador.servidorIniciado();
            Logg.registrar("[*] Servidor corriendo en el puerto " + this.puerto);
        } catch(IOException e) {
            Logg.registrar(e.getLocalizedMessage());
            alive = false;
        }
        
        while(alive) {
            try {
                Socket cliente = servidor.accept();
                Logg.registrar("[*] Cliente conectado");
                if(observador != null) observador.nuevoClienteConectado();
                
                // Se crea hilo de escucha para el nuevo socket cliente.
                AsistenteServidor asistente = new AsistenteServidor(cliente, this);
                new Thread(asistente).start();
                
                // Se almacena referencia al asistente con el nuevo cliente.
                clientesConectados.add(asistente);
                
            }catch(IOException e) {
                Logg.registrar(e.getLocalizedMessage());
            }
        }
        
        // Liberar recursos
        detener();
    }
    
    public boolean isServerRunning() {
        return (servidor != null && !servidor.isClosed());
    }
    
    public void establecerObservador(ObservadorServidor pObservador) {
        this.observador = pObservador;
    }
    
    public void difundirMensaje(String mensaje, AsistenteServidor sender) {
        for(AsistenteServidor asistente: clientesConectados) {
            if(asistente != sender)
                asistente.enviar(mensaje);
        }
        if(observador != null) observador.nuevoMensajeRecibido(mensaje);
    }
    
    public void eliminarClienteConectado(AsistenteServidor asistente) {
        if(asistente != null) {
            clientesConectados.remove(asistente);
            if(observador != null) observador.clienteDesconectado();
        }
    }
}
