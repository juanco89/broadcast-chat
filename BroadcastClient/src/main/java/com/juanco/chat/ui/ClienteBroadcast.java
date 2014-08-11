
package com.juanco.chat.ui;

import com.juanco.chat.controlador.Controlador;
import com.juanco.chat.Global;
import com.juanco.chat.comm.ObservadorCom;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class ClienteBroadcast extends javax.swing.JFrame implements ObservadorCom {

    public ClienteBroadcast() {
        initComponents();
        
        crearComponentesUI();
        
        setTitle("Cliente broadcast");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void crearComponentesUI() {
        Container contenedor = getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        
        // Controles para configurar conexión
        Box contenedorConfig = Box.createHorizontalBox();
        contenedorConfig.add(new JLabel("Host: "));
        txHost = new JTextField("localhost");
        txHost.setPreferredSize(new Dimension(this.getWidth() / 3, 30));
        txHost.setMinimumSize(txHost.getPreferredSize());
        txHost.setMaximumSize(txHost.getPreferredSize());
        txHost.setSelectionStart(0);
        txHost.setSelectionEnd(txHost.getText().length());
        contenedorConfig.add(txHost);
        contenedorConfig.add(new JLabel("Puerto: "));
        txPuerto = new JTextField(Global.PUERTO_DEFAULT);
        txPuerto.setPreferredSize(new Dimension(this.getWidth() / 4, 30));
        txPuerto.setMinimumSize(txPuerto.getPreferredSize());
        txPuerto.setMaximumSize(txPuerto.getPreferredSize());
        txPuerto.setSelectionStart(0);
        txPuerto.setSelectionEnd(txPuerto.getText().length());
        contenedorConfig.add(txPuerto);
        btConectar = new JButton("Conectar");
        btConectar.addActionListener(accionConectar);
        contenedorConfig.add(btConectar);
        contenedor.add(contenedorConfig);
        
        // Contenedor de comunicación
        Box contenedorCom = Box.createHorizontalBox();
        btEnviar = new JButton("Enviar");
        btEnviar.addActionListener(accionEnviar);
        txMensaje = new JTextField();
        txMensaje.setMaximumSize(new Dimension(this.getHeight() - btEnviar.getHeight(), 30));
        contenedorCom.add(txMensaje);
        contenedorCom.add(Box.createHorizontalStrut(50));
        contenedorCom.add(btEnviar);
        contenedor.add(contenedorCom);
        
        txSalida = new JTextArea();
        txSalida.setEnabled(false);
        txSalida.setBorder(BorderFactory.createEtchedBorder());
        contenedor.add(txSalida);
    }
    
    public void setControlador(Controlador c) {
        controlador = c;
    }
    
    // Métodos de la interfaz ObservadorCom
    
    @Override
    public void conexionExitosa() {
        txSalida.append("[*] Conectado al servidor\n");
    }

    @Override
    public void nuevoMensajeRecibido(String msn) {
        txSalida.append("[-] Usuario dice: " + msn + "\n");
    }

    @Override
    public void conexionTerminada() {
        txSalida.append("[*] Terminando conexión\n");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private JTextField txPuerto;
    private JTextField txHost;
    private JButton btConectar;
    private JTextField txMensaje;
    private JButton btEnviar;
    private JTextArea txSalida;
    
    private Controlador controlador;
    
    private final ActionListener accionConectar = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            controlador.conectar(txHost.getText(), Integer.valueOf(txPuerto.getText()));
        }
    };
    
    private final ActionListener accionEnviar = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!txMensaje.getText().isEmpty()) {
                controlador.enviarMensaje(txMensaje.getText());
                txSalida.append(txMensaje.getText() + "\n");
                txMensaje.setText("");
            }
        }
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
