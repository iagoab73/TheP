package Telas.MenuPrincipal;

import Telas.SelecaoPersonagens.VS;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import org.lwjgl.input.Controller;

/**
 *
 * @author 115111168
 */
public class MenuPrincipal extends javax.swing.JFrame {

    private int width;
    private int height;
    private boolean musica;
    private Controller controller1;
    private Controller controller2;
    private int opcao = 0;
    private AudioClip audio = null;
    private Timer comandos;
    
    public MenuPrincipal(int width, int height, Controller controller1, Controller controller2, boolean musica) {
        initComponents();
        atualizaOpcoes(width, height, musica);
        this.controller1 = controller1;
        this.controller2 = controller2;
        atualizaOpt();
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);     
        }
    }
    
    private void atualizaOpcoes(int width, int height, boolean musica){
        this.width = width;
        this.height = height;
        setResolucao(width, height);
        if(this.musica == musica){
            
        }else if(musica){
            this.musica = musica;
            if(audio != null){
                audio.stop();
            }
            URL url = getClass().getResource("Soul Edge OST The Wind and Cloud.wav");
            audio = Applet.newAudioClip(url);
            audio.loop();
        }else{
            this.musica = musica;
            if(audio != null){
                audio.stop();
            }
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
                    if(opcao >= 1){
                        opcao--;
                        atualizaOpt();
                    }else{
                        opcao = 3;
                        atualizaOpt();
                    }
                    if(repet1 == false){
                        cont1 = 0;
                        repet1 = true;
                    }else{
                        cont1 = DELAY - 5;
                    }
                }else if(controller1.isButtonPressed(5) && cont1 == DELAY){
                    if(opcao <= 2){
                        opcao++;
                        atualizaOpt();
                    }else{
                        opcao = 0;
                        atualizaOpt();
                    }
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
            if(bdelay == 15){
                if(controller1.isButtonPressed(1) || controller1.isButtonPressed(0) || controller1.isButtonPressed(2) || controller1.isButtonPressed(3)){
                        acaoEscolha(1);
                        this.cancel();
                        return;
                }
            }else{
                bdelay++;
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
                    if(opcao >= 1){
                        opcao--;
                        atualizaOpt();
                    }else{
                        opcao = 3;
                        atualizaOpt();
                    }
                    if(repet2 == false){
                        cont2 = 0;
                        repet2 = true;
                    }else{
                        cont2 = DELAY - 5;
                    }
                }else if(controller2.isButtonPressed(5) && cont2 == DELAY){
                    if(opcao <= 2){
                        opcao++;
                        atualizaOpt();
                    }else{
                        opcao = 0;
                        atualizaOpt();
                    }
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
                if(controller2.isButtonPressed(1) || controller2.isButtonPressed(0) || controller2.isButtonPressed(2) || controller2.isButtonPressed(3)){
                        acaoEscolha(2);
                        this.cancel();
                }
            }
        }    
    }

    private void atualizaOpt(){
        switch(opcao){
            case 0:
                reseta();
                this.ModoCarreira.setIcon(new ImageIcon(getClass().getResource("ModoCarreira40.png")));
                break;
            case 1:
                reseta();
                this.Multijogador.setIcon(new ImageIcon(getClass().getResource("Multijogador40.png")));
                break;
            case 2:
                reseta();
                this.Opcoes.setIcon(new ImageIcon(getClass().getResource("Opções40.png")));
                break;
            case 3:
                reseta();
                this.Sair.setIcon(new ImageIcon(getClass().getResource("Sair40.png")));
                break;
        }
    }
    
    private void reseta(){
        this.ModoCarreira.setIcon(new ImageIcon(getClass().getResource("ModoCarreira35.png")));          
        this.Multijogador.setIcon(new ImageIcon(getClass().getResource("Multijogador35.png")));                                
        this.Opcoes.setIcon(new ImageIcon(getClass().getResource("Opções35.png")));
        this.Sair.setIcon(new ImageIcon(getClass().getResource("Sair35.png")));
    }
    
    private void acaoEscolha(int con){
        if(con == 2){
            Controller aux = controller1;
            controller1 = controller2;
            controller2 = aux;
        }
        switch(opcao){
            case 0:
                VS v1 = new VS(width, height, controller1, controller2, musica,true,4);
                v1.setEnabled(false);
                Transicao t = new Transicao(v1,width, height, controller1, controller2, musica,true,4);
                t.setVisible(true);
                
                this.setEnabled(false);
                this.dispose();
                if(controller1 != null){
                    comandos.cancel();
                }
                if(audio != null){
                    audio.stop();
                }
                break;
            case 1:
                VS v2 = new VS(width, height, controller1, controller2, musica,false,4);
                v2.setVisible(true);
                v2.ativaTempoeComandos();
                this.setEnabled(false);
                this.dispose();
                if(controller1 != null){
                    comandos.cancel();
                }
                if(audio != null){
                    audio.stop();
                }
                break;
            case 2:
                if(controller1 != null){
                    comandos.cancel();
                }
                this.setEnabled(false);
                Opcoes o = new Opcoes(height, width, musica,controller1, controller2,this);
                o.setVisible(true);
                break;
            case 3:
                if(controller1 != null){
                    comandos.cancel();
                }
                if(audio!=null){
                    audio.stop();
                }
                this.setEnabled(false);
                this.dispose();                
                break;
        }
    }
    
    public void voltaOpcoes(){
        this.setEnabled(true);
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);     
        }
    }
    
    public void voltaOpcoes(int width, int height, boolean musica){
        atualizaOpcoes(width, height, musica);
        this.setEnabled(true);
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);     
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

        CloseButton = new javax.swing.JButton();
        MinimizeButton = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        Sair = new javax.swing.JLabel();
        Opcoes = new javax.swing.JLabel();
        Multijogador = new javax.swing.JLabel();
        ModoCarreira = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/cancel.png"))); // NOI18N
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 32, 32));

        MinimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/minimize.png"))); // NOI18N
        MinimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinimizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(MinimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 0, 32, 32));

        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/TheProfhecy.png"))); // NOI18N
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 939, 182));

        Sair.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/Sair35.png"))); // NOI18N
        Sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SairMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SairMouseEntered(evt);
            }
        });
        getContentPane().add(Sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 549, 89, 58));

        Opcoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Opcoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/Opções35.png"))); // NOI18N
        Opcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpcoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OpcoesMouseEntered(evt);
            }
        });
        getContentPane().add(Opcoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 474, 159, 58));

        Multijogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Multijogador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/Multijogador35.png"))); // NOI18N
        Multijogador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MultijogadorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MultijogadorMouseEntered(evt);
            }
        });
        getContentPane().add(Multijogador, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 399, 285, 58));

        ModoCarreira.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModoCarreira.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/ModoCarreira35.png"))); // NOI18N
        ModoCarreira.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModoCarreiraMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ModoCarreiraMouseEntered(evt);
            }
        });
        getContentPane().add(ModoCarreira, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 324, 320, 58));

        Fundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/TelaFundo.jpg"))); // NOI18N
        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 864));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setResolucao(int width, int height){
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        Dimension d = new Dimension(width,height);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setSize(d);
        this.Fundo.setLocation(((1280 - width) /2)*(-1),((864 - height)/2)*(-1));
        this.CloseButton.setLocation((width - 32), 0);
        this.MinimizeButton.setLocation(width - 64, 0);
        this.Titulo.setLocation((width - Titulo.getWidth())/2, height / 9);
        this.ModoCarreira.setLocation((width - ModoCarreira.getWidth())/2, (int) ((height/4) * 1.8));
        this.Multijogador.setLocation((width - Multijogador.getWidth())/2, ModoCarreira.getY() + 75);
        this.Opcoes.setLocation((width - Opcoes.getWidth())/2, ModoCarreira.getY() + 150);
        this.Sair.setLocation((width - Sair.getWidth())/2, ModoCarreira.getY() + 225);
        this.setLocationRelativeTo(null);  
    }
        
    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        this.setEnabled(false);
        this.dispose();
        if(comandos != null){
            comandos.cancel();
        }
        if(audio!=null){
            audio.stop();
        }
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void MinimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinimizeButtonActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonActionPerformed

    private void MultijogadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MultijogadorMouseClicked
        opcao = 1;
        this.acaoEscolha(0);
    }//GEN-LAST:event_MultijogadorMouseClicked

    private void OpcoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcoesMouseClicked
        opcao = 2;
        this.acaoEscolha(0);
    }//GEN-LAST:event_OpcoesMouseClicked

    private void SairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SairMouseClicked
        opcao = 3;
        this.acaoEscolha(0);
    }//GEN-LAST:event_SairMouseClicked

    private void ModoCarreiraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModoCarreiraMouseEntered
        opcao = 0;
        atualizaOpt();
    }//GEN-LAST:event_ModoCarreiraMouseEntered

    private void MultijogadorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MultijogadorMouseEntered
        opcao = 1;
        atualizaOpt();
    }//GEN-LAST:event_MultijogadorMouseEntered

    private void OpcoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpcoesMouseEntered
        opcao = 2;
        atualizaOpt();
    }//GEN-LAST:event_OpcoesMouseEntered

    private void SairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SairMouseEntered
        opcao = 3;
        atualizaOpt();
    }//GEN-LAST:event_SairMouseEntered

    private void ModoCarreiraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModoCarreiraMouseClicked
        opcao = 0;
        this.acaoEscolha(0);
    }//GEN-LAST:event_ModoCarreiraMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel Fundo;
    private javax.swing.JButton MinimizeButton;
    private javax.swing.JLabel ModoCarreira;
    private javax.swing.JLabel Multijogador;
    private javax.swing.JLabel Opcoes;
    private javax.swing.JLabel Sair;
    private javax.swing.JLabel Titulo;
    // End of variables declaration//GEN-END:variables
}
