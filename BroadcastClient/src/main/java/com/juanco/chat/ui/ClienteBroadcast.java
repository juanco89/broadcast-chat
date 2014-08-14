
package com.juanco.chat.ui;

import com.juanco.chat.controlador.Controlador;
import com.juanco.chat.Global;
import com.juanco.chat.comm.ObservadorCom;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Juan C. Orozco <juanco89@gmail.com>
 */
public class ClienteBroadcast extends javax.swing.JFrame implements ObservadorCom {

    private static final String CONFIG_CARD = "CONFIG_CARD";
    private static final String MSN_CARD = "MSN_CARD";
    
    public ClienteBroadcast() {
        initComponents();
        
        crearComponentesUI();
        
        setTitle("Cliente broadcast");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void crearComponentesUI() {
        // Controles para configurar conexión.
        Box contServidor = Box.createHorizontalBox();
        txHost = new JTextField("localhost");
        txHost.setPreferredSize(new Dimension(this.getWidth() / 3, 30));
        txHost.setMinimumSize(txHost.getPreferredSize());
        txHost.setMaximumSize(txHost.getPreferredSize());
//        txHost.setSelectionStart(0);
//        txHost.setSelectionEnd(txHost.getText().length());
        txPuerto = new JTextField(Global.PUERTO_DEFAULT);
        txPuerto.setPreferredSize(new Dimension(this.getWidth() / 4, 30));
        txPuerto.setMinimumSize(txPuerto.getPreferredSize());
        txPuerto.setMaximumSize(txPuerto.getPreferredSize());
        contServidor.add(new JLabel("Host: "));
        contServidor.add(txHost);
        contServidor.add(Box.createRigidArea(new Dimension(30, 0)));
        contServidor.add(new JLabel("Puerto: "));
        contServidor.add(txPuerto);
        // Contenedor para el nickname
        Box contNickname = Box.createHorizontalBox();
        txNickname = new JTextField();
        txNickname.setPreferredSize(new Dimension(this.getWidth() / 2, 30));
        txNickname.setMinimumSize(txNickname.getPreferredSize());
        txNickname.setMaximumSize(txNickname.getPreferredSize());
        txNickname.requestFocus();
        // Botón de acción para conectar al servidor.
        btConectar = new JButton("Conectar");
        btConectar.addActionListener(accionConectar);
        // agregar componentes al contenedor
        contNickname.add(new Label("Nombre: "));
        contNickname.add(txNickname);
        contNickname.add(Box.createRigidArea(new Dimension(30, 0)));
        contNickname.add(btConectar);
        // Contenedor para la configuración y la conexión al servidor.
        JPanel panelConf = new JPanel();
        panelConf.setLayout(new BoxLayout(panelConf, BoxLayout.Y_AXIS));
        panelConf.add(contServidor);
        panelConf.add(contNickname);
        panelConf.add(Box.createGlue());
        
        // Contenedor de comunicación
        Box contMsn = Box.createHorizontalBox();
        btEnviar = new JButton("Enviar");
        btEnviar.addActionListener(accionEnviar);
        txMensaje = new JTextField();
        txMensaje.setMaximumSize(new Dimension(this.getHeight() - btEnviar.getHeight(), 30));
        contMsn.add(txMensaje);
        contMsn.add(Box.createHorizontalStrut(50));
        contMsn.add(btEnviar);
        
        txConsola = new JTextArea();
        txConsola.setEnabled(false);
        txConsola.setBorder(BorderFactory.createEtchedBorder());
        
        JPanel panelMsn = new JPanel();
        panelMsn.setLayout(new BoxLayout(panelMsn, BoxLayout.Y_AXIS));
        panelMsn.add(contMsn);
        panelMsn.add(txConsola);
        
        // Contenedor principal.
        Container contenedor = getContentPane();
        contenedor.setLayout(new CardLayout());
        contenedor.add(panelConf, CONFIG_CARD);
        contenedor.add(panelMsn, MSN_CARD);
    }
    
    public void setControlador(Controlador c) {
        controlador = c;
    }
    
    // Métodos de la interfaz ObservadorCom
    
    @Override
    public void conexionExitosa() {
        txConsola.append("[*] Conectado al servidor\n");
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), MSN_CARD);
    }

    @Override
    public void nuevoMensajeRecibido(String msn) {
        txConsola.append("+ " + msn + "\n");
    }

    @Override
    public void conexionTerminada() {
        txConsola.append("[*] Terminando conexión\n");
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
    private JTextField txNickname;
    private JButton btConectar;
    private JTextField txMensaje;
    private JButton btEnviar;
    private JTextArea txConsola;
    
    private Controlador controlador;
    
    private final ActionListener accionConectar = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            // Validaciones de campos
            if(txHost.getText().isEmpty() || txPuerto.getText().isEmpty() || txNickname.getText().isEmpty())
                return;
            
            controlador.conectar(txHost.getText(), Integer.valueOf(txPuerto.getText()), txNickname.getText());
        }
    };
    
    private final ActionListener accionEnviar = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!txMensaje.getText().isEmpty()) {
                controlador.enviarMensaje(txMensaje.getText());
                txConsola.append("+ [Yo]: " + txMensaje.getText() + "\n");
                txMensaje.setText("");
            }
        }
    };
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
