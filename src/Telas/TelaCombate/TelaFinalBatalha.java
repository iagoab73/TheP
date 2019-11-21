package Telas.TelaCombate;

import Telas.MenuPrincipal.MenuPrincipal;
import Telas.MenuPrincipal.Transicao;
import Telas.SelecaoPersonagens.VS;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import org.lwjgl.input.Controller;
/**
 *
 * @author Administrator
 */
public class TelaFinalBatalha extends javax.swing.JFrame {

    /**
     * Creates new form TelaFinalBatalha
     */
    private static final int VITORIA  = 0;
    private final int width;
    private final int height;
    private final Tela tela;
    private final Controller controller1;
    private final Controller controller2;
    private boolean command = false;
    private final boolean musica;
    private final boolean p2bot;
    private int idp2;
    private int idp2novo;
    
    public TelaFinalBatalha(int width, int height, Tela tela, Controller controller1, Controller controller2, boolean musica, boolean p2bot, int idp2, int vit) {
        initComponents();
        this.musica = musica;
        this.p2bot = p2bot;
        this.idp2 = idp2;
        this.idp2novo = idp2;
        this.tela = tela;
        this.width = width;
        this.height = height;
        this.controller1 = controller1;
        this.controller2 = controller2;
        if(vit == 1 && p2bot){
            jLabel1.setIcon(new ImageIcon(getClass().getResource("Defeat.png")));
        }else{
            jLabel1.setIcon(new ImageIcon(getClass().getResource("Victory.png")));
            this.idp2novo++;
        }  
        this.setLocationRelativeTo(null);
        Color back = new Color(1f,1f,1f,0f );
        this.setBackground(back);
        if(controller1 != null){
            Timer comand = new Timer();
            comand.schedule(new Comandos(), 0, 16);
        }
    }
    
    private class Comandos extends TimerTask{
        @Override
        public void run() {
            controller1.poll();
            if(controller1.isButtonPressed(8)){
                tela.fechatela();
                MenuPrincipal m = new MenuPrincipal(width, height,controller1, controller2, musica);
                m.setVisible(true);
                this.cancel();
                dispose();
                return;
            }else if(controller1.isButtonPressed(9)){
                this.cancel();
                command = false;
                jButton2ActionPerformed(null);
                return;
            }
            controller2.poll();
            if(controller2.isButtonPressed(8)){
                tela.fechatela();
                MenuPrincipal m = new MenuPrincipal(width, height,controller1, controller2, musica);
                m.setVisible(true);
                this.cancel();
                dispose();
                command = false;
            }else if(controller2.isButtonPressed(9)){
                this.cancel();
                command = false;
                jButton2ActionPerformed(null);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(419, 245));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Voltar ao Menu (Select)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 180, -1));

        jButton2.setText("Continuar (Start)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 180, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 420, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tela.fechatela();
        MenuPrincipal m = new MenuPrincipal(width, height,controller1, controller2, musica);
        m.setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(idp2novo == 8){
            MenuPrincipal m = new MenuPrincipal(width, height,controller1, controller2, musica);
            m.setVisible(true);
        }else{
            VS v = new VS(width, height, controller1, controller2, musica, p2bot, idp2novo);
            v.setEnabled(false);
            if(p2bot && (idp2 != idp2novo)){
                Transicao t = new Transicao(v,width, height, controller1, controller2, musica,p2bot,idp2novo);
                t.setVisible(true);
            }else if (p2bot){
                v.setEnabled(true);               
                v.ativaTempoeComandos();
                v.setVisible(p2bot);
            }
        }
        tela.fechatela();
        this.setEnabled(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}