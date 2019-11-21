package Telas.SelecaoPersonagens;

import Personagens.Humanos.*;
import Personagens.Inumanos.*;
import Personagens.Personagem;
import Telas.MenuPrincipal.MenuPrincipal;
import Telas.TelaCombate.Tela;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import org.lwjgl.input.Controller;

public class VS extends javax.swing.JFrame {

    private final int width;
    private final int height;
    private final boolean musica;
    private int seg = 0;
    private int min = 3;
    private int pers1 = 0;
    private int pers2 = 7;
    private int hab1 = 0;
    private int hab2 = 0;
    private final Controller controller1;
    private final Controller controller2;
    private boolean conf1 = false;
    private boolean conf2 = false;
    private AudioClip audio = null;
    private final Timer tempo;
    private Timer comandos;
    private int perslib = 7;
    private boolean p2bot;
    private int idp2 = 7;
    private final List<Personagem> personagens = Arrays.asList(new Guerreiro(), new Paladino(), new Mago(), new Assassino(), new Animal(), new Troll(), new Zumbi(), new Dragao());
 
    
    public VS(int width, int height, Controller controller1, Controller controller2, boolean musica, boolean p2bot, int idp2) {
        initComponents();
        this.p2bot = p2bot;
        this.idp2 = idp2;
        if(p2bot){
            perslib = 3;
            pers2= idp2;
            mostraPrevia();
            conf2 = true;
            Confirm2.setIcon(new ImageIcon(getClass().getResource("thumb-up (2).png")));
        }
        this.width = width;
        this.height = height;
        this.musica = musica;
        this.controller1 = controller1;
        this.controller2 = controller2;
        setResolucao(width, height);
        this.mostraPrevia();
        if(musica){
            URL url = getClass().getResource("SoulEdge  Blade OST 01 - Epic Calling! (Arranged).wav");
            audio = Applet.newAudioClip(url);
            audio.loop();
        }
        
        tempo = new Timer();
    }
    
    public void ativaTempoeComandos(){
        tempo.schedule(new Tempo(), 0, 1000);
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);
        }
    }
    
    private class Tempo extends TimerTask{
        @Override
        public void run() {
            if(seg == 0 && min > 0){
                seg = 59;
                min--;
            }else{
                seg--;
            }
            String minu = String.format ("%02d", min);
            String segu = String.format ("%02d", seg);
            Relogio.setText(minu + " : " + segu);
            if(seg == 0 && min == 0){
                comecabatalha();
            }

        }
    }
    
    private class Comandos extends TimerTask{
        private static final int DELAY = 15; 
        private boolean repet1 = false;
        private boolean repet1_1 = false;
        private boolean repet2 = false;
        private boolean repet2_2 = false;
        private int cont1;
        private int cont1_1;
        private int cont2;
        private int cont2_2;
        
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
                    if(conf1 == false){
                        if(pers1 > 0){
                            pers1--;
                            mostraPrevia();
                        }else{
                            pers1 = perslib;
                            mostraPrevia();
                        }
                        if(repet1 == false){
                            cont1 = 0;
                            repet1 = true;
                        }else{
                            cont1 = DELAY - 5;
                        }
                    }                        
                }else if(controller1.isButtonPressed(5) && cont1 == DELAY){
                    if(conf1 == false){
                        if(pers1 < perslib){
                            pers1++;
                            mostraPrevia();
                        }else{
                            pers1 = 0;
                            mostraPrevia();
                        }
                        if(repet1 == false){
                        cont1 = 0;
                        repet1 = true;
                        }else{
                            cont1 = DELAY - 5;
                        }
                    }
                }else{
                    repet1 = false;
                    cont1 = DELAY;
                }
            }
            
            if(cont1_1 < DELAY){
                    controller1.poll();
                    if(controller1.isButtonPressed(8) == false){
                        repet1_1 = false;
                        cont1_1 = DELAY;
                    }
                    cont1_1 ++;     
            }else{
                if(controller1.isButtonPressed(8)){
                    if(hab1 == 3){
                        hab1 = 0;
                    }else{
                        hab1++;
                    }
                    atualizaHabilidade1();
                    if(repet1_1 == false){
                        cont1_1 = 0;
                        repet1_1 = true;
                    }else{
                        cont1_1 = DELAY - 5;
                    }
                }else{
                    repet1_1 = false;
                    cont1_1 = DELAY;
                }
            }
            
            if(controller1.isButtonPressed(1) || controller1.isButtonPressed(0) || controller1.isButtonPressed(2) || controller1.isButtonPressed(3)){
                if(conf1 == false){
                        conf1 = true;
                        Confirm1.setIcon(new ImageIcon(getClass().getResource("thumb-up (2).png")));
                        if(conf2 == true){
                             comecabatalha();
                        }
                        cont1 = 0;
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
                    if(conf2 == false){
                        if(pers2 > 0){
                            pers2--;
                            mostraPrevia();
                        }else{
                            pers2 = perslib;
                            mostraPrevia();
                        }
                        if(repet2 == false){
                            cont2 = 0;
                            repet2 = true;
                        }else{
                            cont2 = DELAY - 5;
                        }
                    }                        
                }else if(controller2.isButtonPressed(5) && cont2 == DELAY){
                    if(conf2 == false){
                        if(pers2 < perslib){
                            pers2++;
                            mostraPrevia();
                        }else{
                            pers2 = 0;
                            mostraPrevia();
                        }
                        if(repet2 == false){
                        cont2 = 0;
                        repet2 = true;
                        }else{
                            cont2 = DELAY - 5;
                        }
                    }
                }else{
                    repet2 = false;
                    cont2 = DELAY;
                }
            }
            
            if(cont2_2 < DELAY){
                    controller2.poll();
                    if(controller2.isButtonPressed(8) == false){
                        repet2_2 = false;
                        cont2_2 = DELAY;
                    }
                    cont2_2 ++;     
            }else{
                if(controller2.isButtonPressed(8)){
                    if(hab2 == 3){
                        hab2 = 0;
                    }else{
                        hab2++;
                    }
                    atualizaHabilidade2();
                    if(repet2_2 == false){
                        cont2_2 = 0;
                        repet2_2 = true;
                    }else{
                        cont2_2 = DELAY - 5;
                    }
                }else{
                    repet2_2 = false;
                    cont2_2 = DELAY;
                }
            }
            
            if(controller2.isButtonPressed(1) || controller2.isButtonPressed(0) || controller2.isButtonPressed(2) || controller2.isButtonPressed(3)){
                if(conf2 == false){
                        conf2 = true;
                        Confirm2.setIcon(new ImageIcon(getClass().getResource("thumb-up (2).png")));
                        if(conf1 == true){
                             comecabatalha();
                        }
                        cont2 = 0;
                }
            }
        }
    }
    
    private void setResolucao(int width, int height){
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        Dimension d = new Dimension(width,height);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setSize(d);
        this.Fundo.setLocation(((1280 - width) /2)*(-1), (int) (((864 - height)*.8)*(-1)));
        
        this.CloseButton.setLocation((width - 32), 0);
        this.MinimizeButton.setLocation(width - 64, 0);
        this.BackButton.setLocation(width - 96, 0);
        this.Relogio.setLocation((width - Relogio.getWidth())/2, 10);
        this.Setas.setLocation((width - Setas.getWidth())/2, Fundo.getY() + 122);
        this.Pla1.setLocation(-80, height - Pla1.getHeight() + 20);
        this.Pla2.setLocation(width - Pla2.getWidth() + 80,Pla1.getY());
        this.setLocationRelativeTo(null);   
    }

    private void atualizaHabilidade1() {
        switch(hab1){
            case 0:
                Q1.setBorderPainted(true);
                W1.setBorderPainted(false);
                E1.setBorderPainted(false);
                R1.setBorderPainted(false);
                break;
            case 1:
                Q1.setBorderPainted(false);
                W1.setBorderPainted(true);
                E1.setBorderPainted(false);
                R1.setBorderPainted(false);
                break;
            case 2:
                Q1.setBorderPainted(false);
                W1.setBorderPainted(false);
                E1.setBorderPainted(true);
                R1.setBorderPainted(false);
                break;
            case 3:
                Q1.setBorderPainted(false);
                W1.setBorderPainted(false);
                E1.setBorderPainted(false);
                R1.setBorderPainted(true);
                break;
        }
        this.NomeHabilidade1.setText(personagens.get(pers1).getHabilidades().get(hab1).getNome());
        this.Descricao1.setText("<html>" + personagens.get(pers1).getHabilidades().get(hab1).getDescrição() + "</html>");
        this.titulomana1.setText("Mana: ");
        this.titulocooldown1.setText("CoolDown: ");
        this.ManaT1.setText("" + personagens.get(pers1).getHabilidades().get(hab1).getGastodemana());
        this.Cooldown1.setText(personagens.get(pers1).getHabilidades().get(hab1).getCooldownBase() / 1000+ " segundos");
    }
    
    private void atualizaHabilidade2() {
        switch(hab2){
            case 0:
                Q2.setBorderPainted(true);
                W2.setBorderPainted(false);
                E2.setBorderPainted(false);
                R2.setBorderPainted(false);
                break;
            case 1:
                Q2.setBorderPainted(false);
                W2.setBorderPainted(true);
                E2.setBorderPainted(false);
                R2.setBorderPainted(false);
                break;
            case 2:
                Q2.setBorderPainted(false);
                W2.setBorderPainted(false);
                E2.setBorderPainted(true);
                R2.setBorderPainted(false);
                break;
            case 3:
                Q2.setBorderPainted(false);
                W2.setBorderPainted(false);
                E2.setBorderPainted(false);
                R2.setBorderPainted(true);
                break;
        }
        this.NomeHabilidade2.setText(personagens.get(pers2).getHabilidades().get(hab2).getNome());
        this.Descricao2.setText("<html>" + personagens.get(pers2).getHabilidades().get(hab2).getDescrição() + "</html>");
        this.titulomana2.setText("Mana: ");
        this.titulocooldown2.setText("CoolDown: ");
        this.ManaT2.setText("" + personagens.get(pers2).getHabilidades().get(hab2).getGastodemana());
        this.Cooldown2.setText(personagens.get(pers2).getHabilidades().get(hab2).getCooldownBase() / 1000+ " segundos");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackButton = new javax.swing.JButton();
        MinimizeButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JButton();
        Relogio = new javax.swing.JLabel();
        Setas = new javax.swing.JPanel();
        Assassino2 = new javax.swing.JLabel();
        Mago2 = new javax.swing.JLabel();
        Guerreiro2 = new javax.swing.JLabel();
        Animal1 = new javax.swing.JLabel();
        Troll2 = new javax.swing.JLabel();
        Assassino1 = new javax.swing.JLabel();
        Troll1 = new javax.swing.JLabel();
        Paladino2 = new javax.swing.JLabel();
        Dragao2 = new javax.swing.JLabel();
        Zumbi1 = new javax.swing.JLabel();
        Dragao1 = new javax.swing.JLabel();
        Animal2 = new javax.swing.JLabel();
        Zumbi2 = new javax.swing.JLabel();
        Guerreiro1 = new javax.swing.JLabel();
        Paladino1 = new javax.swing.JLabel();
        Mago1 = new javax.swing.JLabel();
        Pla2 = new javax.swing.JPanel();
        Confirm2 = new javax.swing.JButton();
        Direita2 = new javax.swing.JButton();
        Esquerda2 = new javax.swing.JButton();
        Q2 = new javax.swing.JButton();
        W2 = new javax.swing.JButton();
        E2 = new javax.swing.JButton();
        R2 = new javax.swing.JButton();
        NomePersonagem2 = new javax.swing.JLabel();
        MostraPersonagemP2 = new javax.swing.JLabel();
        titulocooldown2 = new javax.swing.JLabel();
        Cooldown2 = new javax.swing.JLabel();
        ManaT2 = new javax.swing.JLabel();
        titulomana2 = new javax.swing.JLabel();
        Descricao2 = new javax.swing.JLabel();
        NomeHabilidade2 = new javax.swing.JLabel();
        Pla1 = new javax.swing.JPanel();
        Confirm1 = new javax.swing.JButton();
        Direita1 = new javax.swing.JButton();
        Esquerda1 = new javax.swing.JButton();
        Q1 = new javax.swing.JButton();
        W1 = new javax.swing.JButton();
        E1 = new javax.swing.JButton();
        R1 = new javax.swing.JButton();
        NomePersonagem1 = new javax.swing.JLabel();
        MostraPersonagemP1 = new javax.swing.JLabel();
        NomeHabilidade1 = new javax.swing.JLabel();
        Descricao1 = new javax.swing.JLabel();
        titulomana1 = new javax.swing.JLabel();
        ManaT1 = new javax.swing.JLabel();
        Cooldown1 = new javax.swing.JLabel();
        titulocooldown1 = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/back.png"))); // NOI18N
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1184, 0, 32, 32));

        MinimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/minimize.png"))); // NOI18N
        MinimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinimizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(MinimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 0, 32, 32));

        CloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/cancel.png"))); // NOI18N
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 32, 32));

        Relogio.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 48)); // NOI18N
        Relogio.setForeground(new java.awt.Color(255, 255, 255));
        Relogio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Relogio, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 170, 50));

        Setas.setOpaque(false);
        Setas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Setas.add(Assassino2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 42, 37));
        Setas.add(Mago2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 42, 37));
        Setas.add(Guerreiro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 42, 37));
        Setas.add(Animal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 42, 37));
        Setas.add(Troll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, 42, 37));
        Setas.add(Assassino1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, 42, 37));
        Setas.add(Troll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 42, 37));
        Setas.add(Paladino2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 42, 37));
        Setas.add(Dragao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, 42, 37));
        Setas.add(Zumbi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 30, 42, 37));
        Setas.add(Dragao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, 42, 37));
        Setas.add(Animal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 42, 37));
        Setas.add(Zumbi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 340, 42, 37));
        Setas.add(Guerreiro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 42, 37));
        Setas.add(Paladino1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 42, 37));
        Setas.add(Mago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 42, 37));

        getContentPane().add(Setas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 1050, 400));

        Pla2.setOpaque(false);
        Pla2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Confirm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/thumb-up (1).png"))); // NOI18N
        Confirm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Confirm2ActionPerformed(evt);
            }
        });
        Pla2.add(Confirm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 32, 32));

        Direita2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/direita.png"))); // NOI18N
        Direita2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Direita2ActionPerformed(evt);
            }
        });
        Pla2.add(Direita2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 32, 32));

        Esquerda2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/esquerda.png"))); // NOI18N
        Esquerda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Esquerda2ActionPerformed(evt);
            }
        });
        Pla2.add(Esquerda2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 32, 32));

        Q2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        Q2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        Q2.setBorderPainted(false);
        Q2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Q2MouseEntered(evt);
            }
        });
        Pla2.add(Q2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 60, 60));

        W2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        W2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        W2.setBorderPainted(false);
        W2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                W2MouseEntered(evt);
            }
        });
        Pla2.add(W2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 60, 60));

        E2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        E2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        E2.setBorderPainted(false);
        E2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                E2MouseEntered(evt);
            }
        });
        Pla2.add(E2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 60, 60));

        R2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        R2.setBorderPainted(false);
        R2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                R2MouseEntered(evt);
            }
        });
        Pla2.add(R2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 60, 60));

        NomePersonagem2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        NomePersonagem2.setForeground(new java.awt.Color(255, 255, 255));
        NomePersonagem2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NomePersonagem2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Pla2.add(NomePersonagem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 250, 50));

        MostraPersonagemP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MostraPersonagemP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/DragaoDireita.gif"))); // NOI18N
        Pla2.add(MostraPersonagemP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 480, 320));

        titulocooldown2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulocooldown2.setForeground(new java.awt.Color(255, 255, 255));
        Pla2.add(titulocooldown2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, -1, -1));

        Cooldown2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cooldown2.setForeground(new java.awt.Color(255, 255, 255));
        Pla2.add(Cooldown2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        ManaT2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ManaT2.setForeground(new java.awt.Color(255, 255, 255));
        Pla2.add(ManaT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, -1));

        titulomana2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulomana2.setForeground(new java.awt.Color(255, 255, 255));
        Pla2.add(titulomana2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, -1, -1));

        Descricao2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Descricao2.setForeground(new java.awt.Color(255, 255, 255));
        Descricao2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Descricao2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Pla2.add(Descricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, 140));

        NomeHabilidade2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NomeHabilidade2.setForeground(new java.awt.Color(255, 255, 255));
        Pla2.add(NomeHabilidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        getContentPane().add(Pla2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 360, 550, 380));

        Pla1.setOpaque(false);
        Pla1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Confirm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/thumb-up (1).png"))); // NOI18N
        Confirm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Confirm1ActionPerformed(evt);
            }
        });
        Pla1.add(Confirm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 32, 32));

        Direita1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/direita.png"))); // NOI18N
        Direita1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Direita1ActionPerformed(evt);
            }
        });
        Pla1.add(Direita1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 32, 32));

        Esquerda1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/esquerda.png"))); // NOI18N
        Esquerda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Esquerda1ActionPerformed(evt);
            }
        });
        Pla1.add(Esquerda1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 32, 32));

        Q1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        Q1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        Q1.setBorderPainted(false);
        Q1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Q1MouseEntered(evt);
            }
        });
        Pla1.add(Q1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 60, 60));

        W1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        W1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        W1.setBorderPainted(false);
        W1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                W1MouseEntered(evt);
            }
        });
        Pla1.add(W1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 60, 60));

        E1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        E1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        E1.setBorderPainted(false);
        E1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                E1MouseEntered(evt);
            }
        });
        Pla1.add(E1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 60, 60));

        R1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/E_PrisaoMagicaG.png"))); // NOI18N
        R1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        R1.setBorderPainted(false);
        R1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                R1MouseEntered(evt);
            }
        });
        Pla1.add(R1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 60, 60));

        NomePersonagem1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        NomePersonagem1.setForeground(new java.awt.Color(255, 255, 255));
        NomePersonagem1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Pla1.add(NomePersonagem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 250, 50));

        MostraPersonagemP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MostraPersonagemP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/DragaoEsquerda.gif"))); // NOI18N
        Pla1.add(MostraPersonagemP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 50, 480, 320));

        NomeHabilidade1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NomeHabilidade1.setForeground(new java.awt.Color(255, 255, 255));
        Pla1.add(NomeHabilidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        Descricao1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Descricao1.setForeground(new java.awt.Color(255, 255, 255));
        Descricao1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Descricao1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Pla1.add(Descricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 170, 140));

        titulomana1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulomana1.setForeground(new java.awt.Color(255, 255, 255));
        Pla1.add(titulomana1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, -1, -1));

        ManaT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ManaT1.setForeground(new java.awt.Color(255, 255, 255));
        Pla1.add(ManaT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, -1));

        Cooldown1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cooldown1.setForeground(new java.awt.Color(255, 255, 255));
        Pla1.add(Cooldown1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, -1));

        titulocooldown1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulocooldown1.setForeground(new java.awt.Color(255, 255, 255));
        Pla1.add(titulocooldown1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, -1));

        getContentPane().add(Pla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 360, 550, 380));

        Fundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/SelecaoPersonagens/TelaPS2.png"))); // NOI18N
        Fundo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(Fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -72, 1280, 864));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MinimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinimizeButtonActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        tempo.cancel();
        if(comandos != null){
            comandos.cancel();
        }
        this.setEnabled(false);
        this.dispose();
        if(audio != null){
            audio.stop();
        }
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void limpasetas(int id){
        switch(id){
            case 1:
                this.Mago1.setIcon(null);
                this.Assassino1.setIcon(null);
                this.Guerreiro1.setIcon(null);
                this.Dragao1.setIcon(null);
                this.Troll1.setIcon(null);
                this.Animal1.setIcon(null);
                this.Zumbi1.setIcon(null);
                this.Paladino1.setIcon(null);
                break;
            case 2:
                this.Mago2.setIcon(null);
                this.Assassino2.setIcon(null);
                this.Guerreiro2.setIcon(null);
                this.Dragao2.setIcon(null);
                this.Troll2.setIcon(null);
                this.Animal2.setIcon(null);
                this.Zumbi2.setIcon(null);
                this.Paladino2.setIcon(null);
                break;
        }
        
    }
    
    private void mostraPrevia(){
        switch(pers1){
            case 0:
                limpasetas(1);
                this.Guerreiro1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 1:
                limpasetas(1);
                this.Paladino1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 2:
                limpasetas(1);
                this.Mago1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 3:
                limpasetas(1);
                this.Assassino1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 4:
                limpasetas(1);
                this.Animal1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 5:
                limpasetas(1);
                this.Troll1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 6:
                limpasetas(1);
                this.Zumbi1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
            case 7:
                limpasetas(1);
                this.Dragao1.setIcon(new ImageIcon(getClass().getResource("p1.png")));
                this.NomePersonagem1.setIcon(personagens.get(pers1).getImagemNomeGrande());
                this.MostraPersonagemP1.setIcon(personagens.get(pers1).getEfeitoEsquerda());
                this.Q1.setIcon(personagens.get(pers1).getHabilidades().get(0).getIcone());
                this.W1.setIcon(personagens.get(pers1).getHabilidades().get(1).getIcone());
                this.E1.setIcon(personagens.get(pers1).getHabilidades().get(2).getIcone());
                this.R1.setIcon(personagens.get(pers1).getHabilidades().get(3).getIcone());
                atualizaHabilidade1();
                break;
        }
        
        switch(pers2){
            case 0:
                limpasetas(2);
                this.Guerreiro2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 1:
                limpasetas(2);
                this.Paladino2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 2:
                limpasetas(2);
                this.Mago2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 3:
                limpasetas(2);
                this.Assassino2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 4:
                limpasetas(2);
                this.Animal2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 5:
                limpasetas(2);
                this.Troll2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 6:
                limpasetas(2);
                this.Zumbi2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
            case 7:
                limpasetas(2);
                this.Dragao2.setIcon(new ImageIcon(getClass().getResource("p2.png")));
                this.NomePersonagem2.setIcon(personagens.get(pers2).getImagemNomeGrande());
                this.MostraPersonagemP2.setIcon(personagens.get(pers2).getEfeitoDireita());
                this.Q2.setIcon(personagens.get(pers2).getHabilidades().get(0).getIcone());
                this.W2.setIcon(personagens.get(pers2).getHabilidades().get(1).getIcone());
                this.E2.setIcon(personagens.get(pers2).getHabilidades().get(2).getIcone());
                this.R2.setIcon(personagens.get(pers2).getHabilidades().get(3).getIcone());
                atualizaHabilidade2();
                break;
        }
    }
    
    private void Confirm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Confirm1ActionPerformed
        conf1 = true;
        Confirm1.setIcon(new ImageIcon(getClass().getResource("thumb-up (2).png")));
        if(conf2 == true){
            comecabatalha();
        }
    }//GEN-LAST:event_Confirm1ActionPerformed

    private void Direita1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Direita1ActionPerformed
        if(conf1){
            return;
        }
        if(pers1 == perslib){
            pers1 = 0;
            this.mostraPrevia();
            return;
        }
        this.pers1++;
        this.mostraPrevia();
    }//GEN-LAST:event_Direita1ActionPerformed

    private void Esquerda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Esquerda2ActionPerformed
        if(conf2){
            return;
        }
        if(pers2 == 0){
            pers2 = 7;
            this.mostraPrevia();
            return;
        }
        this.pers2--;
        this.mostraPrevia();
    }//GEN-LAST:event_Esquerda2ActionPerformed

    private void Direita2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Direita2ActionPerformed
        if(conf2){
            return;
        }
        if(pers2 == 7){
            pers2 = 0;
            this.mostraPrevia();
            return;
        }
        this.pers2++;
        this.mostraPrevia();
    }//GEN-LAST:event_Direita2ActionPerformed

    private void limpaDesc1(){
        this.NomeHabilidade1.setText("");
        this.Descricao1.setText("");
        this.titulomana1.setText("");
        this.titulocooldown1.setText("");
        this.ManaT1.setText("");
        this.Cooldown1.setText("");
    }
    
    private void limpaDesc2(){
        this.NomeHabilidade2.setText("");
        this.Descricao2.setText("");
        this.titulomana2.setText("");
        this.titulocooldown2.setText("");
        this.ManaT2.setText("");
        this.Cooldown2.setText("");
    }
    
    private void Q1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q1MouseEntered
        this.hab1 = 0;
        atualizaHabilidade1();
    }//GEN-LAST:event_Q1MouseEntered

    private void W1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_W1MouseEntered
        this.hab1 = 1;
        atualizaHabilidade1();
    }//GEN-LAST:event_W1MouseEntered

    private void E1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E1MouseEntered
        this.hab1 = 2;
        atualizaHabilidade1();
    }//GEN-LAST:event_E1MouseEntered

    private void R1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R1MouseEntered
        this.hab1 = 3;
        atualizaHabilidade1();
    }//GEN-LAST:event_R1MouseEntered

    private void Q2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q2MouseEntered
        this.hab2 = 0;
        atualizaHabilidade2();
    }//GEN-LAST:event_Q2MouseEntered

    private void W2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_W2MouseEntered
        this.hab2 = 1;
        atualizaHabilidade2();
    }//GEN-LAST:event_W2MouseEntered

    private void E2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E2MouseEntered
        this.hab2 = 2;
        atualizaHabilidade2();
    }//GEN-LAST:event_E2MouseEntered

    private void R2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R2MouseEntered
        this.hab2 = 3;
        atualizaHabilidade2();
    }//GEN-LAST:event_R2MouseEntered

    private void Esquerda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Esquerda1ActionPerformed
        if(conf1){
            return;
        }
        if(pers1 == 0){
            pers1 = perslib;
            this.mostraPrevia();
            return;
        }
        this.pers1--;
        this.mostraPrevia();
    }//GEN-LAST:event_Esquerda1ActionPerformed

    private void Confirm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Confirm2ActionPerformed
        conf2 = true;
        Confirm2.setIcon(new ImageIcon(getClass().getResource("thumb-up (2).png")));
        if(conf1 == true){
            comecabatalha();
        }
    }//GEN-LAST:event_Confirm2ActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
            MenuPrincipal menu = new MenuPrincipal(width, height, controller1, controller2, musica);
            menu.setVisible(true);
            this.CloseButtonActionPerformed(null);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void comecabatalha() {
        Personagem player1;
        Personagem player2;
        
        switch(pers1){
            case 0:
                player1 = new Guerreiro();
                break;
            case 1:
                player1 = new Paladino();
                break;
            case 2:
                player1 = new Mago();
                break;
            case 3:
                player1 = new Assassino();
                break;
            case 4:
                player1 = new Animal();
                break;
            case 5:
                player1 = new Troll();
                break;
            case 6:
                player1 = new Zumbi();
                break;
            default:
                player1 = new Dragao();
                break;
        }
        
        switch(pers2){
            case 0:
                player2 = new Guerreiro();
                break;
            case 1:
                player2 = new Paladino();
                break;
            case 2:
                player2 = new Mago();
                break;
            case 3:
                player2 = new Assassino();
                break;
            case 4:
                player2 = new Animal();
                break;
            case 5:
                player2 = new Troll();
                break;
            case 6:
                player2 = new Zumbi();
                break;
            default:
                player2 = new Dragao();
                break;
        }
        Tela t = new Tela(player1, player2, width, height, controller1, controller2, musica, p2bot, idp2);
        t.setVisible(true);
        this.CloseButtonActionPerformed(null);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Animal1;
    private javax.swing.JLabel Animal2;
    private javax.swing.JLabel Assassino1;
    private javax.swing.JLabel Assassino2;
    private javax.swing.JButton BackButton;
    private javax.swing.JButton CloseButton;
    private javax.swing.JButton Confirm1;
    private javax.swing.JButton Confirm2;
    private javax.swing.JLabel Cooldown1;
    private javax.swing.JLabel Cooldown2;
    private javax.swing.JLabel Descricao1;
    private javax.swing.JLabel Descricao2;
    private javax.swing.JButton Direita1;
    private javax.swing.JButton Direita2;
    private javax.swing.JLabel Dragao1;
    private javax.swing.JLabel Dragao2;
    private javax.swing.JButton E1;
    private javax.swing.JButton E2;
    private javax.swing.JButton Esquerda1;
    private javax.swing.JButton Esquerda2;
    private javax.swing.JLabel Fundo;
    private javax.swing.JLabel Guerreiro1;
    private javax.swing.JLabel Guerreiro2;
    private javax.swing.JLabel Mago1;
    private javax.swing.JLabel Mago2;
    private javax.swing.JLabel ManaT1;
    private javax.swing.JLabel ManaT2;
    private javax.swing.JButton MinimizeButton;
    private javax.swing.JLabel MostraPersonagemP1;
    private javax.swing.JLabel MostraPersonagemP2;
    private javax.swing.JLabel NomeHabilidade1;
    private javax.swing.JLabel NomeHabilidade2;
    private javax.swing.JLabel NomePersonagem1;
    private javax.swing.JLabel NomePersonagem2;
    private javax.swing.JLabel Paladino1;
    private javax.swing.JLabel Paladino2;
    private javax.swing.JPanel Pla1;
    private javax.swing.JPanel Pla2;
    private javax.swing.JButton Q1;
    private javax.swing.JButton Q2;
    private javax.swing.JButton R1;
    private javax.swing.JButton R2;
    private javax.swing.JLabel Relogio;
    private javax.swing.JPanel Setas;
    private javax.swing.JLabel Troll1;
    private javax.swing.JLabel Troll2;
    private javax.swing.JButton W1;
    private javax.swing.JButton W2;
    private javax.swing.JLabel Zumbi1;
    private javax.swing.JLabel Zumbi2;
    private javax.swing.JLabel titulocooldown1;
    private javax.swing.JLabel titulocooldown2;
    private javax.swing.JLabel titulomana1;
    private javax.swing.JLabel titulomana2;
    // End of variables declaration//GEN-END:variables
}
