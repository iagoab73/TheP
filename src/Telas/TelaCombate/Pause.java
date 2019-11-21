package Telas.TelaCombate;

import Telas.MenuPrincipal.*;
import Telas.SelecaoPersonagens.VS;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import org.lwjgl.input.Controller;

/**
 *
 * @author 115111168
 */
public class Pause extends javax.swing.JFrame {

    private int height;
    private int width;
    private boolean musica;
    private Controller controller1;
    private Controller controller2;
    private Timer comandos;
    private Tela tela;
    private int opcao = 2;
    private boolean p2bot;
    private int idp2;
    
    
    public Pause(int height, int width, boolean musica, Controller controller1, Controller controller2, Tela tela, boolean p2bot,int idp2) {
        initComponents();
        atualizaOpcao();
        this.p2bot = p2bot;
        this.idp2 = idp2;
        this.height = height;
        this.width = width;
        this.musica = musica;
        this.controller1 = controller1;
        this.controller2 = controller2;
        this.tela = tela;
        Color back = new Color(1f,1f,1f,0f );
        this.setBackground(back);
        this.Menu.setBackground(back);
        this.VS.setBackground(back);
        this.Continua.setBackground(back);
        this.setLocationRelativeTo(null);
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);  
        }
    }

    private void atualizaOpcao() {
        switch(opcao){
            case 0:
                Menu.setBorderPainted(true);
                VS.setBorderPainted(false);
                Continua.setBorderPainted(false);
                break;
            case 1:
                Menu.setBorderPainted(false);
                VS.setBorderPainted(true);
                Continua.setBorderPainted(false);
                break;
            case 2:
                Menu.setBorderPainted(false);
                VS.setBorderPainted(false);
                Continua.setBorderPainted(true);
                break;
        }
    }
    
    private void acaoEscolha(){
        switch(opcao){
            case 0:
                MenuActionPerformed(null);
                break;
            case 1:
                VSActionPerformed(null);
                break;
            case 2:
                ContinuaActionPerformed(null);
                comandos.cancel();
                break;
        }
    }
    
    private class Comandos extends TimerTask {
        private static final int DELAY = 15; 
        private boolean repet1 = false;
        private boolean repet2 = false;
        private int cont1;
        private int cont2;
        private int bdelay;
        
        private Comandos(){
            cont1 = DELAY;
            cont2 = DELAY;
            bdelay = 0;
        }
        
        @Override
        public void run() {
            if(cont1 < DELAY){
                controller1.poll();
                if(controller1.isButtonPressed(4) == false && controller1.isButtonPressed(5) == false){
                    repet1 = false;
                    cont1 = DELAY;
                }
                cont1 ++;
            }else{
                controller1.poll();
                if(controller1.isButtonPressed(4) && controller1.isButtonPressed(5)){

                }else if(controller1.isButtonPressed(4) && cont1 == DELAY){
                    if(opcao == 0){
                        opcao = 2;
                    }else{
                        opcao--;
                    }
                    atualizaOpcao();
                    if(repet1 == false){
                        cont1 = 0;
                        repet1 = true;
                    }else{
                        cont1 = DELAY - 5;
                    }
                }else if(controller1.isButtonPressed(5) && cont1 == DELAY){
                    if(opcao == 2){
                        opcao = 0;
                    }else{
                        opcao++;
                    }
                    atualizaOpcao();
                    if(repet1 == false){
                        cont1 = 0;
                        repet1 = true;
                    }else{
                        cont1 = DELAY - 5;
                    }
                }else{
                    repet1 = false;
                    cont1 = DELAY;
                }
            }
            if(bdelay < 15){
                bdelay++;
            }else{
                if(controller1.isButtonPressed(9)){
                    ContinuaActionPerformed(null);
                }
                if(controller1.isButtonPressed(0) || controller1.isButtonPressed(1) || controller1.isButtonPressed(2) || controller1.isButtonPressed(3)){
                    acaoEscolha();
                }
            }
            
            if(cont2 < DELAY){
                controller2.poll();
                if(controller2.isButtonPressed(4) == false && controller2.isButtonPressed(5) == false){
                    repet2 = false;
                    cont2 = DELAY;
                }
                cont2 ++;

            }else{
                controller2.poll();
                if(controller2.isButtonPressed(4) && controller2.isButtonPressed(5)){

                }else if(controller2.isButtonPressed(4) && cont2 == DELAY){
                    if(opcao == 0){
                        opcao = 2;
                    }else{
                        opcao--;
                    }
                    atualizaOpcao();
                    if(repet2 == false){
                        cont2 = 0;
                        repet2 = true;
                    }else{
                        cont2 = DELAY - 5;
                    }
                }else if(controller2.isButtonPressed(5) && cont2 == DELAY){
                    if(opcao == 2){
                        opcao = 0;
                    }else{
                        opcao++;
                    }
                    atualizaOpcao();
                    if(repet2 == false){
                        cont2 = 0;
                        repet2 = true;
                    }else{
                        cont2 = DELAY - 5;
                    }
                }else{
                    repet2 = false;
                    cont2 = DELAY;
                }
            }
            if(bdelay == 15){
                if(controller2.isButtonPressed(9)){
                    ContinuaActionPerformed(null);
                }
                if(controller2.isButtonPressed(0) || controller2.isButtonPressed(1) || controller2.isButtonPressed(2) || controller2.isButtonPressed(3)){
                    acaoEscolha();
                }
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

        Continua = new javax.swing.JButton();
        VS = new javax.swing.JButton();
        Menu = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Continua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/direita.png"))); // NOI18N
        Continua.setText("Continua");
        Continua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        Continua.setBorderPainted(false);
        Continua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Continua.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Continua.setOpaque(false);
        Continua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ContinuaMouseEntered(evt);
            }
        });
        Continua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinuaActionPerformed(evt);
            }
        });
        getContentPane().add(Continua, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 190, -1, 32));

        VS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/back.png"))); // NOI18N
        VS.setText("Selecão de Personagens");
        VS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        VS.setBorderPainted(false);
        VS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VS.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        VS.setOpaque(false);
        VS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VSMouseEntered(evt);
            }
        });
        VS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VSActionPerformed(evt);
            }
        });
        getContentPane().add(VS, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 150, -1, 32));

        Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/exitdoor.png"))); // NOI18N
        Menu.setText("Menu Principal");
        Menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        Menu.setBorderPainted(false);
        Menu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Menu.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Menu.setOpaque(false);
        Menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MenuMouseEntered(evt);
            }
        });
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuActionPerformed(evt);
            }
        });
        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, 32));

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/PauseMenu.png"))); // NOI18N
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 400, 120));

        Fundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/DAMN.png"))); // NOI18N
        Fundo.setPreferredSize(new java.awt.Dimension(500, 240));
        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuActionPerformed
        MenuPrincipal pr = new MenuPrincipal(width,height,controller1, controller2, musica);
        pr.setVisible(true);
        tela.fechatela();
        if(controller1 != null){
            this.comandos.cancel();
        }
        this.dispose();
    }//GEN-LAST:event_MenuActionPerformed

    private void VSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VSActionPerformed
        VS v = new VS(width, height, controller1, controller2, musica, p2bot, idp2);
        v.setVisible(true);
        v.ativaTempoeComandos();
        tela.fechatela();
        if(controller1 != null){
            this.comandos.cancel();
        }
        this.dispose();
    }//GEN-LAST:event_VSActionPerformed

    private void ContinuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinuaActionPerformed
        tela.continua();
        if(controller1 != null){
            this.comandos.cancel();
        }
        this.dispose();
    }//GEN-LAST:event_ContinuaActionPerformed

    private void MenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuMouseEntered
        opcao = 0;
        atualizaOpcao();
    }//GEN-LAST:event_MenuMouseEntered

    private void VSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VSMouseEntered
        opcao = 1;
        atualizaOpcao();
    }//GEN-LAST:event_VSMouseEntered

    private void ContinuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContinuaMouseEntered
        opcao = 2;
        atualizaOpcao();
    }//GEN-LAST:event_ContinuaMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Continua;
    private javax.swing.JLabel Fundo;
    private javax.swing.JButton Menu;
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton VS;
    // End of variables declaration//GEN-END:variables
}
