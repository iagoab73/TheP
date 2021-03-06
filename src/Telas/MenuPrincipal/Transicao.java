package Telas.MenuPrincipal;

import Telas.SelecaoPersonagens.VS;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import org.lwjgl.input.Controller;

/**
 *
 * @author Administrator
 */
public class Transicao extends javax.swing.JFrame {

    private int width;
    private int height;
    private boolean musica;
    private Controller controller1;
    private Controller controller2;
    private AudioClip audio = null;
    private boolean p2bot;
    private int idp2;
    private final Timer tempo;
    private final VS v;
    
    public Transicao(VS v, int width, int height, Controller controller1, Controller controller2, boolean musica, boolean p2bot, int idp2) {
        initComponents();
        this.v = v;
        this.p2bot = p2bot;
        this.idp2 = idp2;
        this.width = width;
        this.height = height;
        this.musica = musica;
        this.controller1 = controller1;
        this.controller2 = controller2;
        tempo = new Timer();
        switch(idp2){
            case 4:
                Text.setIcon(new ImageIcon(getClass().getResource("Text0.png")));
                break;
            case 5:
                Text.setIcon(new ImageIcon(getClass().getResource("Text1.png")));
                break;
            case 6:
                Text.setIcon(new ImageIcon(getClass().getResource("Text2.png")));
                break;
            case 7:
                Text.setIcon(new ImageIcon(getClass().getResource("Text3.png")));
                break;     
        }
        setResolucao(width, height);
    }
    
    private class Tempo extends TimerTask{
        @Override
        public void run() {
            if(Text.getY() <= Text.getHeight()*(-1)){
                this.cancel(); 
                iniciaSelecao();  
            }
            Text.setLocation(Text.getX(), Text.getY() - 1);
        }
    }
    
    private void iniciaSelecao(){
        tempo.cancel();
        v.setVisible(true);
        v.setEnabled(true);
        v.ativaTempoeComandos();
        this.setEnabled(false);
        this.dispose();
    }
    
    private void setResolucao(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        Dimension d = new Dimension(width,height);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setSize(d);
        this.Fundo.setLocation(((1280 - width) /2)*(-1),((864 - height)/2)*(-1));
        Text.setLocation((this.width - Text.getWidth())/2, this.height);
        this.setLocationRelativeTo(null);  
        
        tempo.schedule(new Tempo(), 0, 10);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Text = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Text.setMaximumSize(new java.awt.Dimension(1062, 1000));
        Text.setMinimumSize(new java.awt.Dimension(0, 0));
        Text.setPreferredSize(new java.awt.Dimension(1062, 1000));
        getContentPane().add(Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/MenuPrincipal/TelaFundo.jpg"))); // NOI18N
        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 864));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel Text;
    // End of variables declaration//GEN-END:variables

    
}
