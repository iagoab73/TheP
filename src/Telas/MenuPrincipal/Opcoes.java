package Telas.MenuPrincipal;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import org.lwjgl.input.Controller;

/**
 *
 * @author 115111168
 */
public class Opcoes extends javax.swing.JFrame {

    private int height;
    private int width;
    private boolean musica;
    private Controller controller1;
    private Controller controller2;
    private Timer comandos;
    private MenuPrincipal menu;
    
    public Opcoes(int height, int width, boolean musica, Controller controller1, Controller controller2, MenuPrincipal menu) {
        initComponents();
        this.menu = menu;
        this.height = height;
        this.width = width;
        if(width == 1280){
            Res1.setSelected(true);
        }else{
            Res2.setSelected(true);
        }
        this.musica = musica;
        if(musica){
            Mus1.setSelected(true);
        }else{
            Mus2.setSelected(true);
        }
        this.controller1 = controller1;
        this.controller2 = controller2;
        Color back = new Color(1f,1f,1f,0f );
        this.setBackground(back);
        this.Ok.setBackground(back);
        this.Cancel.setBackground(back);
        this.setLocationRelativeTo(null);
        this.buttonGroup1.add(Res1);
        this.buttonGroup1.add(Res2);
        this.buttonGroup2.add(Mus1);
        this.buttonGroup2.add(Mus2);
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);  
        }
    }
    
    private class Comandos extends TimerTask {
        private static final int DELAY = 15; 
        private boolean repet1 = false;
        private boolean repet2 = false;
        private int cont1;
        private int cont2;
        
        private Comandos(){
            cont1 = DELAY;
            cont2 = DELAY;
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
                    if(Res1.isSelected()){
                        Res2.setSelected(true);
                        width = 1152;
                        height = 864;
                    }else{
                        Res1.setSelected(true);
                        width = 1280;
                        height = 720;
                    }
                    if(repet1 == false){
                        cont1 = 0;
                        repet1 = true;
                    }else{
                        cont1 = DELAY - 5;
                    }
                }else if(controller1.isButtonPressed(5) && cont1 == DELAY){
                    if(Mus1.isSelected()){
                        Mus2.setSelected(true);
                        musica = false;
                    }else{
                        Mus1.setSelected(true);
                        musica = true;
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
            if(controller1.isButtonPressed(8)){
                CancelActionPerformed(null);
            }
            if(controller1.isButtonPressed(9)){
                OkActionPerformed(null);
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
                    if(Res1.isSelected()){
                        Res2.setSelected(true);
                        width = 1152;
                        height = 864;
                    }else{
                        Res1.setSelected(true);
                        width = 1280;
                        height = 720;
                    }
                    if(repet2 == false){
                        cont2 = 0;
                        repet2 = true;
                    }else{
                        cont2 = DELAY - 5;
                    }
                }else if(controller2.isButtonPressed(5) && cont2 == DELAY){
                    if(Mus1.isSelected()){
                        Mus2.setSelected(true);
                        musica = false;
                    }else{
                        Mus1.setSelected(true);
                        musica = true;
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
            if(controller2.isButtonPressed(8)){
                CancelActionPerformed(null);
            }
            if(controller2.isButtonPressed(9)){
                OkActionPerformed(null);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Ok = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        Res1 = new javax.swing.JRadioButton();
        Res2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Mus1 = new javax.swing.JRadioButton();
        Mus2 = new javax.swing.JRadioButton();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/Ok.png"))); // NOI18N
        Ok.setBorderPainted(false);
        Ok.setOpaque(false);
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });
        getContentPane().add(Ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 32, 32));

        Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/Cancel.png"))); // NOI18N
        Cancel.setBorderPainted(false);
        Cancel.setOpaque(false);
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        getContentPane().add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 32, 32));

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/OpcoesM.png"))); // NOI18N
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 400, 120));

        Res1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Res1.setText("1280 x 720");
        Res1.setOpaque(false);
        Res1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Res1ActionPerformed(evt);
            }
        });
        getContentPane().add(Res1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        Res2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Res2.setText("1152 x 864");
        Res2.setOpaque(false);
        Res2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Res2ActionPerformed(evt);
            }
        });
        getContentPane().add(Res2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jLabel2.setText("Selecione a Resolução:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jLabel3.setText("Música:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        Mus1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Mus1.setText("ON");
        Mus1.setOpaque(false);
        Mus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mus1ActionPerformed(evt);
            }
        });
        getContentPane().add(Mus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, -1, -1));

        Mus2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Mus2.setText("OFF");
        Mus2.setOpaque(false);
        Mus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mus2ActionPerformed(evt);
            }
        });
        getContentPane().add(Mus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        Fundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/DAMN.png"))); // NOI18N
        Fundo.setPreferredSize(new java.awt.Dimension(500, 240));
        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Res1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Res1ActionPerformed
        this.width = 1280;
        this.height = 720;
    }//GEN-LAST:event_Res1ActionPerformed

    private void Mus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mus1ActionPerformed
        this.musica = true;
    }//GEN-LAST:event_Mus1ActionPerformed

    private void Res2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Res2ActionPerformed
        this.width = 1152;
        this.height = 864;
    }//GEN-LAST:event_Res2ActionPerformed

    private void Mus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mus2ActionPerformed
        this.musica = false;
    }//GEN-LAST:event_Mus2ActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        menu.voltaOpcoes();
        if(controller1 != null){
            this.comandos.cancel();
        }
        this.dispose();
    }//GEN-LAST:event_CancelActionPerformed

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
        menu.voltaOpcoes(width, height, musica);
        if(controller1 != null){
            this.comandos.cancel();
        }
        this.dispose();
    }//GEN-LAST:event_OkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel Fundo;
    private javax.swing.JRadioButton Mus1;
    private javax.swing.JRadioButton Mus2;
    private javax.swing.JButton Ok;
    private javax.swing.JRadioButton Res1;
    private javax.swing.JRadioButton Res2;
    private javax.swing.JLabel Titulo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
