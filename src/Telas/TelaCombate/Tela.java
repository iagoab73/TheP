package Telas.TelaCombate;

import Habilidades.*;
import Personagens.Personagem;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.lwjgl.input.Controller;

/**
 *
 * @author 115111168
 */
public final class Tela extends javax.swing.JFrame {

    private static final int V = 350;
    private final Personagem player1;
    private final Personagem player2;
    private boolean tempocorre;
    private boolean segundoo = false;
    private int seg = 0;
    private int min = 0;
    private final ArrayList<int[]> fila = new ArrayList<>();
    private String log = "";
    private final int width;
    private final int height;
    private final boolean musica;
    private final Controller controller1;
    private final Controller controller2;
    private int hab1 = 0;
    private int hab2 = 0;
    private AudioClip audio = null;
    private Thread ataque = null;
    private Timer tempo;
    private Timer comandos;
    private int cont_decimos = 0;
    private boolean pause = false;
    private final boolean p2bot;
    private final int idp2;
    
    private int p1 = V - 200;
    private int p2 = V - 200;
    
    
    
    public Tela(Personagem player1, Personagem player2, int width, int height, Controller controller1, Controller controller2, boolean musica, boolean p2bot, int idp2) {
        initComponents();
        this.idp2 = idp2;
        this.p2bot = p2bot;
        this.width = width;
        this.height = height;
        this.musica = musica;
        this.controller1 = controller1;
        this.controller2 = controller2;
        this.Cenario.setIcon(player2.getCenario());
        setResolucao(width, height);
        
        Color vida = new Color(0f,0.8f,0.4f,.8f );
        Vida1.setForeground(vida);
        Vida2.setForeground(vida);
        Color mana = new Color(0f,0.6f,1f,.8f );
        Mana1.setForeground(mana);
        Mana2.setForeground(mana);
        Vida1.setOpaque(false);
        Vida2.setOpaque(false);
        Mana1.setOpaque(false);
        Mana2.setOpaque(false);
        CD1.setOpaque(false);
        CD2.setOpaque(false);
        
        this.Descricao3.setText("<html>" + log + "</html>");
        
        NomePersonagem1.setIcon(player1.getImagemNomeGrande());
        Personagem1.setIcon(player1.getEfeitoEsquerda());
        Vida1.setMaximum(player1.getVidaBase());
        Mana1.setMaximum(player1.getManaBase());
        
        NomePersonagem2.setIcon(player2.getImagemNomeGrande());
        Personagem2.setIcon(player2.getEfeitoDireita());
        Vida2.setMaximum(player2.getVidaBase());
        Mana2.setMaximum(player2.getManaBase());
        
        this.player1 = player1;
        this.player2 = player2;
        player1.setTela(this);
        player2.setTela(this);
        CD1.setMaximum(V);
        CD2.setMaximum(V);
        CD1.setValue(p1);
        CD2.setValue(p2);
        botanatela();
        mostraPrevia1();
        mostraPrevia2();
        player1.segundo();
        player2.segundo();
        check();
        
        if(musica){
            URL url = getClass().getResource("Soul Edge OST (ARCADE)  18 - Bravely Folk Song.wav");
            audio = Applet.newAudioClip(url);
            audio.loop();
        }
        
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);
        }
        iniciaTempo();
    }
    
    private void paraTempo(){
        tempocorre = false;
        tempo.cancel();
        this.Relogio.setForeground(Color.red);
    }
    
    private void iniciaTempo(){
        if(pause){
            return;
        }
        tempocorre = true;
        tempo = new Timer();
        tempo.schedule(new Tempo(), 0, 100);
        this.Relogio.setForeground(Color.white);
    }

    public boolean isTempocorre() {
        return tempocorre;
    }   

    void continua() {
        pause = false;
        if(ataque == null){
            iniciaTempo(); 
        }else if(ataque.isAlive() == false){
            iniciaTempo();
        }
        this.setEnabled(true);
        if(controller1 != null){
            comandos = new Timer();
            comandos.schedule(new Comandos(), 0, 16);
        }
    }
    
    private class Tempo extends TimerTask{

        @Override
        public void run() {
            segundoo = true;
            if(p1 < V){
                if((p1 + (player1.getAgilidade()/10)) > V){
                    p1 = V;
                }else{
                    p1 = p1 + (player1.getAgilidade()/10);
                }
            }
            if(p2 < V){
                if((p2 + (player2.getAgilidade()/10)) >= V){
                    p2 = V;
                }else{
                    p2 = p2 + (player2.getAgilidade()/10);
                }
            }
            if(p2 == V && p2bot){
                switch (player2.getClasse()) {
                    case "Mago":
                        escolheAtaqueMago(player2, player1);
                        break;
                    case "Zumbi":
                        escolheAtaqueZumbi(player2, player1);
                        break;
                    case "Dragão":
                        escolheAtaqueDragao(player2, player1);
                        break;
                    case "Animal":
                        escolheAtaqueAnimal(player2, player1);
                        break;
                    case "Troll":
                        escolheAtaqueTroll(player2, player1);
                        break;
                }
            }
            CD1.setValue(p1);
            CD2.setValue(p2);
            check();
            cont_decimos++;
            if(cont_decimos == 10){
                cont_decimos = 0;
                seg++;
                if(seg == 60){
                    min++;
                    seg = 0;
                }
                String minu = String.format ("%02d", min);
                String segu = String.format ("%02d", seg);
                Relogio.setText(minu + " : " + segu);
                player1.segundo();
                player2.segundo();
                mostraPrevia1();
                mostraPrevia2();
                atualizaVidaMana();
                check();
            }
            segundoo = false; 
            
            if(fila.isEmpty() != true){
                    Personagem pl;
                    Personagem alvo;
                    if(fila.get(0)[0] == 1){                        
                        pl = player1;
                        alvo = player2;
                    }else{
                        pl = player2;
                        alvo = player1;
                    }
                    switch(fila.get(0)[1]){
                        case 4:
                            if(fila.get(0)[0] == 1){
                                iniciaAtaqueBasico(player1, player2);
                            }else{
                                iniciaAtaqueBasico(player2, player1);
                            }
                            break;
                        default:
                            iniciaAtaque(fila.get(0)[1], pl, alvo);
                            break;
                        }
                fila.remove(fila.get(0));
                paraTempo();
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
                    if(hab1 >= 1){
                        hab1--;
                        mostraPrevia1();
                    }else{
                        hab1 = 4;
                        mostraPrevia1();
                    }
                    if(repet1 == false){
                        cont1 = 0;
                        repet1 = true;
                    }else{
                        cont1 = DELAY - 5;
                    }
                }else if(controller1.isButtonPressed(5) && cont1 == DELAY){
                    if(hab1 <= 3){
                        hab1++;
                        mostraPrevia1();
                    }else{
                        hab1 = 0;
                        mostraPrevia1();
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
            if(bdelay < 15){
                bdelay++;
            }else{
                if(controller1.isButtonPressed(0) || controller1.isButtonPressed(1) || controller1.isButtonPressed(2) || controller1.isButtonPressed(3)){
                    boolean go = true;
                    switch(hab1){
                        case 0:
                            if(AtaqueBasico1.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 1:
                            if(Q1.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 2:
                            if(W1.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 3:
                            if(E1.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 4:
                            if(R1.isEnabled() != true){
                                go = false;
                            }
                            break;
                    }
                    if(go){
                        confirmaEscolha(1);
                    }
                }
                if(controller1.isButtonPressed(9)){
                    PauseButtonActionPerformed(null);
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
                    if(hab2 >= 1){
                        hab2--;
                        mostraPrevia2();
                    }else{
                        hab2 = 4;
                        mostraPrevia2();
                    }
                    if(repet2 == false){
                        cont2 = 0;
                        repet2 = true;
                    }else{
                        cont2 = DELAY - 5;
                    }
                }else if(controller2.isButtonPressed(5) && cont2 == DELAY){
                    if(hab2 <= 3){
                        hab2++;
                        mostraPrevia2();
                    }else{
                        hab2 = 0;
                        mostraPrevia2();
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
                if(controller2.isButtonPressed(0) || controller2.isButtonPressed(1) || controller2.isButtonPressed(2) || controller2.isButtonPressed(3)){
                    boolean go = true;
                    switch(hab2){
                        case 0:
                            if(AtaqueBasico2.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 1:
                            if(Q2.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 2:
                            if(W2.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 3:
                            if(E2.isEnabled() != true){
                                go = false;
                            }
                            break;
                        case 4:
                            if(R2.isEnabled() != true){
                                go = false;
                            }
                            break;
                    }
                    if(go){
                        confirmaEscolha(2);
                    }
                }
                if(controller2.isButtonPressed(9)){
                    PauseButtonActionPerformed(null);
                }
            }
            
        }   
    
    }
    
    private void escolheAtaqueMago(Personagem ataque, Personagem defesa){
        boolean ab = ataque.isAtaquebasico();
        boolean q = (ataque.getHabilidades().get(0).getDisponivel() == 1);
        boolean w = (ataque.getHabilidades().get(1).getDisponivel() == 1);
        boolean e = (ataque.getHabilidades().get(2).getDisponivel() == 1);
        boolean r = (ataque.getHabilidades().get(3).getDisponivel() == 1);
        
        float at = ataque.getVida();
        float ba = ataque.getVidaBase();
        float perc = at/ba;
        
        if(perc <= 0.5 && w){
            this.usahabilidade(1, ataque, defesa);
        }else if(r){
            this.usahabilidade(3, ataque, defesa);
        }else if(q){
            this.usahabilidade(0, ataque, defesa);
        }else if(e){
            this.usahabilidade(2, ataque, defesa);
        }else{
            this.AtaqueBasico2ActionPerformed(null);
        }
    }
    
    
    private void escolheAtaqueZumbi(Personagem ataque, Personagem defesa){
        boolean ab = ataque.isAtaquebasico();
        boolean q = (ataque.getHabilidades().get(0).getDisponivel() == 1);
        boolean w = (ataque.getHabilidades().get(1).getDisponivel() == 1);
        boolean e = (ataque.getHabilidades().get(2).getDisponivel() == 1);
        boolean r = (ataque.getHabilidades().get(3).getDisponivel() == 1);
        
        float at = ataque.getVida();
        float ba = ataque.getVidaBase();
        float perc = at/ba;
        if(perc <= 0.25 && e){
            this.usahabilidade(2, ataque, defesa);
        }else if(r){
            this.usahabilidade(3, ataque, defesa);
        }else if(q){
            this.usahabilidade(0, ataque, defesa);
        }else if(w){
            this.usahabilidade(1, ataque, defesa);
        }else{
            this.AtaqueBasico2ActionPerformed(null);
        }
    }
    
    private void escolheAtaqueDragao(Personagem ataque, Personagem defesa){
        boolean ab = ataque.isAtaquebasico();
        boolean q = (ataque.getHabilidades().get(0).getDisponivel() == 1);
        boolean w = (ataque.getHabilidades().get(1).getDisponivel() == 1);
        boolean e = (ataque.getHabilidades().get(2).getDisponivel() == 1);
        boolean r = (ataque.getHabilidades().get(3).getDisponivel() == 1);
        boolean r2 = false;
        boolean e2 = false;
        for(EfeitoEspecial i : ataque.getEfeitos()){
            if(i.getTipo() == 0){
                r2 = true;
            }else if(i.getNome().equals("Afinidade com o Fogo")){
                e2 = true;
            }
        }
        
        if(r2){
            
        }else if(e2){
            this.AtaqueBasico2ActionPerformed(null);
        }else if(r){
            this.usahabilidade(3, ataque, defesa);
        }else if(q){
            this.usahabilidade(0, ataque, defesa);
        }else if(e){
            this.usahabilidade(2, ataque, defesa);
        }else if(w){
            this.usahabilidade(1, ataque, defesa);
        }else{
            this.AtaqueBasico2ActionPerformed(null);
        }
    }
    
    private void escolheAtaqueAnimal(Personagem ataque, Personagem defesa){
        boolean ab = ataque.isAtaquebasico();
        boolean q = (ataque.getHabilidades().get(0).getDisponivel() == 1);
        boolean w = (ataque.getHabilidades().get(1).getDisponivel() == 1);
        boolean e = (ataque.getHabilidades().get(2).getDisponivel() == 1);
        boolean r = (ataque.getHabilidades().get(3).getDisponivel() == 1);
        boolean r2 = false;
        
        for(EfeitoEspecial i : ataque.getEfeitos()){
            if(i.getTipo() == 0){
                r2 = true;
            }
        }
        
        float at = ataque.getVida();
        float ba = ataque.getVidaBase();
        float perc = at/ba;
        
        if(r2){
            this.AtaqueBasico2ActionPerformed(null);
        }else if(perc <= 0.7 && w){
            this.usahabilidade(1, ataque, defesa);
        }else if(r){
            this.usahabilidade(3, ataque, defesa);
        }else if(q){
            this.usahabilidade(0, ataque, defesa);
        }else if(e){
            this.usahabilidade(2, ataque, defesa);
        }else{
            this.AtaqueBasico2ActionPerformed(null);
        }
    }
    
    private void escolheAtaqueTroll(Personagem ataque, Personagem defesa){
        boolean ab = ataque.isAtaquebasico();
        boolean q = (ataque.getHabilidades().get(0).getDisponivel() == 1);
        boolean w = (ataque.getHabilidades().get(1).getDisponivel() == 1);
        boolean e = (ataque.getHabilidades().get(2).getDisponivel() == 1);
        boolean r = (ataque.getHabilidades().get(3).getDisponivel() == 1);
        boolean r2 = false;
        boolean cc = false;
        
        for(CC i : defesa.getCcs()){
            cc = true;
        }
        
        if(cc){
            if(w){
                this.usahabilidade(1, ataque, defesa);
            }else{
                this.AtaqueBasico2ActionPerformed(null);
            }
        }else if(r){
            this.usahabilidade(3, ataque, defesa);
        }else if(q){
            this.usahabilidade(0, ataque, defesa);
        }else if(e){
            this.usahabilidade(2, ataque, defesa);
        }else{
            this.AtaqueBasico2ActionPerformed(null);
        }
    }
    
    public void setResolucao(int width, int height){
        this.setLayout(null);
        int y;
        if((height - 538) < 240){
            y = height -538 -240;
        }else{
            y = 0;
        }
        this.Cenario.setLocation(((1280 - width) /2)*(-1), y);
        this.FundoMenu.setLocation(((1280 - width) /2)*(-1), y + 538);
        this.Habilidades1.setLocation(Habilidades1.getX(), y + 478);
        this.Habilidades2.setLocation(width -510 + 120, y + 478);
        this.Borda.setLocation((width/2)-Borda.getWidth()/2, y + 518);
        
        this.log1.setSize(width/2 - 300, (height - FundoMenu.getY())/2 - 20);
        this.log1.setLocation(300, FundoMenu.getY() + 34);
        this.log2.setSize(log1.getWidth(), log1.getHeight());
        this.log2.setLocation(width / 2, log1.getY());
        this.log3.setSize(log1.getWidth(), log1.getHeight());
        this.log3.setLocation(log1.getX(), log1.getY() + log1.getHeight());
        this.log4.setSize(log1.getWidth(), log1.getHeight());
        this.log4.setLocation(log2.getX(), log3.getY());
        
        this.Descricao3.setSize((log1.getWidth() * 2) - 20, (log1.getHeight()*2)-80);
        this.Descricao3.setLocation(log1.getX() + 50, log1.getY() + 40);
        
        this.Vida1.setLocation(Cenario.getX() + 160, Vida1.getY());
        this.Mana1.setLocation(Cenario.getX() + 160, Mana1.getY());
        this.Skill1.setLocation(Cenario.getX() + 50, FundoMenu.getY()-360);
        this.NomePersonagem1.setLocation(Cenario.getX() + 160, NomePersonagem1.getY());
        this.Personagem1.setLocation(Cenario.getX() + 100, FundoMenu.getY()-360);
        this.Vida2.setLocation(width - Vida1.getX() - Vida2.getWidth(), Vida2.getY());
        this.Mana2.setLocation(width - Mana1.getX() - Mana2.getWidth(), Mana2.getY());
        this.Skill2.setLocation(width - Skill1.getX() - Skill2.getWidth(), FundoMenu.getY()-360);
        this.NomePersonagem2.setLocation(width - NomePersonagem1.getX() - NomePersonagem2.getWidth(), NomePersonagem2.getY());
        this.Personagem2.setLocation(width - Personagem1.getX() - Personagem2.getWidth(), FundoMenu.getY()-360);
        this.Relogio.setLocation((width/2)-Relogio.getWidth()/2, Relogio.getY());
        this.PauseButton.setLocation((width)-PauseButton.getWidth(), PauseButton.getY());
        this.MinimizeButton.setLocation((width)-(MinimizeButton.getWidth() + PauseButton.getWidth()), MinimizeButton.getY());
        Dimension d = new Dimension(width,height);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setSize(d);
        this.setLocationRelativeTo(null);
    }
    
    public void mostraEfeito(Personagem usuario, int tipo, Habilidade hab, int duracao){ 
        if(usuario.equals(player1)){
            if(tipo == 0){
                Skill1.setIcon(hab.getEfeitoEsquerda());
            }else{
                Skill2.setIcon(hab.getEfeitoDireita());
            }
        }else{
            if(tipo == 0){
                Skill2.setIcon(hab.getEfeitoDireita());
            }else{
                Skill1.setIcon(hab.getEfeitoEsquerda());
            }
        }
        try {
            Thread.sleep(duracao + 400);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            Skill1.setIcon(null);
            Skill2.setIcon(null);
        }
        Skill1.setIcon(null);
        Skill2.setIcon(null);
    }
    
    
    public void mostraEfeitoAtaqueBasico(Personagem usuario, int duracao){
        if(usuario.equals(player1)){
                Skill2.setIcon(usuario.getEfeitoAtaqueBasicoDireita());
        }else{
                Skill1.setIcon(usuario.getEfeitoAtaqueBasicoEsquerda());
        }
        try {
            Thread.sleep(duracao + 400);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            Skill1.setIcon(null);
            Skill2.setIcon(null);
        }
        Skill1.setIcon(null);
        Skill2.setIcon(null);
    }
    
    
    
    private void confirmaEscolha(int idplayer){
        switch(idplayer){
            case 1:
                if(hab1 == 0){
                    this.ataqueBasico1();
                }else{
                    this.usahabilidade(hab1-1, player1, player2);
                }
                break;
            case 2:
                if(hab2 == 0){
                    this.ataqueBasico2();
                }else{
                    this.usahabilidade(hab2-1, player2, player1);
                }
                break;
        }
    }
    
    private void mostraPrevia1() {
        int then = hab1;
        switch(then){
            case 0:
                this.NomeHabilidade1.setText("Ataque Básico");
                this.Descricao1.setText("<html> Usa um ataque que causa dano físico ao inimigo.</html>");
                this.titulomana1.setText("Mana:");
                this.titulocooldown1.setText("CoolDown:");
                this.ManaT1.setText("0");
                this.Cooldown1.setText("Não há");
                this.AtaqueBasico1.setBorderPainted(true);
                this.Q1.setBorderPainted(false);
                this.W1.setBorderPainted(false);
                this.E1.setBorderPainted(false);
                this.R1.setBorderPainted(false);
                break;
            case 1:
                this.NomeHabilidade1.setText(player1.getHabilidades().get(then - 1).getNome());
                this.Descricao1.setText("<html>" +player1.getHabilidades().get(then - 1).getDescrição());
                this.titulomana1.setText("Mana:");
                this.titulocooldown1.setText("CoolDown:");
                this.ManaT1.setText("" +player1.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown1.setText(player1.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player1.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico1.setBorderPainted(false);
                this.Q1.setBorderPainted(true);
                this.W1.setBorderPainted(false);
                this.E1.setBorderPainted(false);
                this.R1.setBorderPainted(false);
                break;
            case 2:
                this.NomeHabilidade1.setText(player1.getHabilidades().get(then - 1).getNome());
                this.Descricao1.setText("<html>" +player1.getHabilidades().get(then - 1).getDescrição());
                this.titulomana1.setText("Mana:");
                this.titulocooldown1.setText("CoolDown:");
                this.ManaT1.setText("" +player1.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown1.setText(player1.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player1.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico1.setBorderPainted(false);
                this.Q1.setBorderPainted(false);
                this.W1.setBorderPainted(true);
                this.E1.setBorderPainted(false);
                this.R1.setBorderPainted(false);
                break;
            case 3:
                this.NomeHabilidade1.setText(player1.getHabilidades().get(then - 1).getNome());
                this.Descricao1.setText("<html>" +player1.getHabilidades().get(then - 1).getDescrição());
                this.titulomana1.setText("Mana:");
                this.titulocooldown1.setText("CoolDown:");
                this.ManaT1.setText("" +player1.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown1.setText(player1.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player1.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico1.setBorderPainted(false);
                this.Q1.setBorderPainted(false);
                this.W1.setBorderPainted(false);
                this.E1.setBorderPainted(true);
                this.R1.setBorderPainted(false);
                break;
            case 4:
                this.NomeHabilidade1.setText(player1.getHabilidades().get(then - 1).getNome());
                this.Descricao1.setText("<html>" +player1.getHabilidades().get(then - 1).getDescrição());
                this.titulomana1.setText("Mana:");
                this.titulocooldown1.setText("CoolDown:");
                this.ManaT1.setText("" +player1.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown1.setText(player1.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player1.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico1.setBorderPainted(false);
                this.Q1.setBorderPainted(false);
                this.W1.setBorderPainted(false);
                this.E1.setBorderPainted(false);
                this.R1.setBorderPainted(true);
                break;
        } 
    }
    
    private void mostraPrevia2() {
        int then = hab2;
        switch(then){
            case 0:
                this.NomeHabilidade2.setText("Ataque Básico");
                this.Descricao2.setText("<html> Usa um ataque que causa dano físico ao inimigo.</html>");
                this.titulomana2.setText("Mana:");
                this.titulocooldown2.setText("CoolDown:");
                this.ManaT2.setText("0");
                this.Cooldown2.setText("Não há");
                this.AtaqueBasico2.setBorderPainted(true);
                this.Q2.setBorderPainted(false);
                this.W2.setBorderPainted(false);
                this.E2.setBorderPainted(false);
                this.R2.setBorderPainted(false);
                break;
            case 1:
                this.NomeHabilidade2.setText(player2.getHabilidades().get(then - 1).getNome());
                this.Descricao2.setText("<html>" +player2.getHabilidades().get(then - 1).getDescrição());
                this.titulomana2.setText("Mana:");
                this.titulocooldown2.setText("CoolDown:");
                this.ManaT2.setText("" +player2.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown2.setText(player2.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player2.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico2.setBorderPainted(false);
                this.Q2.setBorderPainted(true);
                this.W2.setBorderPainted(false);
                this.E2.setBorderPainted(false);
                this.R2.setBorderPainted(false);
                break;
            case 2:
                this.NomeHabilidade2.setText(player2.getHabilidades().get(then - 1).getNome());
                this.Descricao2.setText("<html>" +player2.getHabilidades().get(then - 1).getDescrição());
                this.titulomana2.setText("Mana:");
                this.titulocooldown2.setText("CoolDown:");
                this.ManaT2.setText("" +player2.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown2.setText(player2.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player2.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico2.setBorderPainted(false);
                this.Q2.setBorderPainted(false);
                this.W2.setBorderPainted(true);
                this.E2.setBorderPainted(false);
                this.R2.setBorderPainted(false);
                break;
            case 3:
                this.NomeHabilidade2.setText(player2.getHabilidades().get(then - 1).getNome());
                this.Descricao2.setText("<html>" +player2.getHabilidades().get(then - 1).getDescrição());
                this.titulomana2.setText("Mana:");
                this.titulocooldown2.setText("CoolDown:");
                this.ManaT2.setText("" +player2.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown2.setText(player2.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player2.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico2.setBorderPainted(false);
                this.Q2.setBorderPainted(false);
                this.W2.setBorderPainted(false);
                this.E2.setBorderPainted(true);
                this.R2.setBorderPainted(false);
                break;
            case 4:
                this.NomeHabilidade2.setText(player2.getHabilidades().get(then - 1).getNome());
                this.Descricao2.setText("<html>" +player2.getHabilidades().get(then - 1).getDescrição());
                this.titulomana2.setText("Mana:");
                this.titulocooldown2.setText("CoolDown:");
                this.ManaT2.setText("" +player2.getHabilidades().get(then - 1).getGastodemana());
                this.Cooldown2.setText(player2.getHabilidades().get(then - 1).getCooldown()/1000 + " de " + player2.getHabilidades().get(then - 1).getCooldownBase()/1000 + " segundos");
                this.AtaqueBasico2.setBorderPainted(false);
                this.Q2.setBorderPainted(false);
                this.W2.setBorderPainted(false);
                this.E2.setBorderPainted(false);
                this.R2.setBorderPainted(true);
                break;
        } 
    }
    
    public void personagemMorte(Personagem p){
        if(controller1 != null){
            comandos.cancel();
        }
        this.paraTempo();
        this.setEnabled(false);
        int vit = 0;
        if(p.equals(player1)){
            vit = 1;
            adicionaTexto("  Player 1 perdeu");
            this.Personagem1.setIcon(new ImageIcon(getClass().getResource("Morte.gif")));
        }else{
            adicionaTexto("  Player 2 perdeu");
            this.Personagem2.setIcon(new ImageIcon(getClass().getResource("Morte.gif")));
        }
        TelaFinalBatalha tel = new TelaFinalBatalha(width, height,this, controller1, controller2,musica,p2bot,idp2,vit);
        tel.setVisible(true);
        if(ataque != null){
            ataque.stop();
        }
    }
    
    public void adicionaTexto(String msg){
        if(tempocorre){
            adicionaTextoLogInsta(msg);
        }else{
            adicionaTextoLog(msg);
        }
    }
    
    public void adicionaTextoLogInsta(String string) {
        log = log + string + "<br />";
        this.Descricao3.setText("<html>" + log + "</html>");
    }
    
    public void adicionaTextoLog(String msg){
        String[] letras = msg.split("");
        
        int velocidade = 10;
        int calc = letras.length*velocidade + 400;
        
        for (String letra : letras) {
            log = log + letra;
            this.Descricao3.setText("<html>" + log + "</html>");
            try {
                Thread.sleep(velocidade);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        log = log + "<br />";
        this.Descricao3.setText("<html>" + log + "</html>");
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() <= startTime + 400) {
        }
    }
    
    public void atualizaVidaDano(Personagem pers){
        if(pers.equals(player1)){
            while(Vida1.getValue() > player1.getVida()){
                if(Vida1.getValue() - 10 < player1.getVida()){
                    this.Vida1.setValue(player1.getVida());
                }
                this.Vida1.setValue(Vida1.getValue() - 10);
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() <= startTime + 10) {
                }
            }
        }else{
            while(Vida2.getValue() > player2.getVida()){
                if(Vida2.getValue() - 10 < player2.getVida()){
                    this.Vida2.setValue(player2.getVida());
                }
                this.Vida2.setValue(Vida2.getValue() - 10);
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() <= startTime + 10) {
                }
            }
        }    
    }
    
    public void atualizaVidaCura(Personagem pers){
        if(pers.equals(player1)){
            while(Vida1.getValue() < player1.getVida()){
                if(Vida1.getValue() + 10 > player1.getVida()){
                    this.Vida1.setValue(player1.getVida());
                }
                this.Vida1.setValue(Vida1.getValue() + 10);
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() <= startTime + 10) {
                }
            }
        }else{
            while(Vida2.getValue() < player2.getVida()){
                if(Vida2.getValue() + 10 > player2.getVida()){
                    this.Vida2.setValue(player2.getVida());
                }
                this.Vida2.setValue(Vida2.getValue() + 10);
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() <= startTime + 10) {
                }
            }
        }    
    }
    
    public class ThreadAtaque implements Runnable {

        private final int id;
        private final int tipo;
        private final Personagem ataque;
        private final Personagem alvo;

        public ThreadAtaque(int tipo, int id, Personagem ataque, Personagem alvo) {
            this.id = id;
            this.tipo = tipo;
            this.ataque = ataque;
            this.alvo = alvo;
        }

        @Override
        public void run() {
            if(tipo == 0){
                ataque.usaHabilidade(id,alvo);
            }else{
                ataque.ataqueBásico(alvo);
            }
            atualizaVidaMana();
            check();
            iniciaTempo();
        }
    }
      
    public void botanatela(){
        this.atualizaVidaMana();
        
        this.Q1.setIcon(player1.getHabilidades().get(0).getIcone());
        this.W1.setIcon(player1.getHabilidades().get(1).getIcone());
        this.E1.setIcon(player1.getHabilidades().get(2).getIcone());
        this.R1.setIcon(player1.getHabilidades().get(3).getIcone());
        
        this.Q2.setIcon(player2.getHabilidades().get(0).getIcone());
        this.W2.setIcon(player2.getHabilidades().get(1).getIcone());
        this.E2.setIcon(player2.getHabilidades().get(2).getIcone());
        this.R2.setIcon(player2.getHabilidades().get(3).getIcone());
    }
    
    public void atualizaVidaMana() {
        Vida1.setValue(player1.getVida());
        Mana1.setValue(player1.getMana());

        Vida2.setValue(player2.getVida());
        Mana2.setValue(player2.getMana());
    }
    
    private void check(){
        if(p1 < V){
            Q1.setEnabled(false);
            W1.setEnabled(false);
            E1.setEnabled(false);
            R1.setEnabled(false);
            AtaqueBasico1.setEnabled(false);
        }else{
            List<javax.swing.JButton> list1 = Arrays.asList(Q1, W1, E1, R1);
            for(int i=0; i<4; i++){
                checaStatusHabilidade(player1.getHabilidades().get(i), list1.get(i));
            }
            this.AtaqueBasico1.setEnabled(player1.isAtaquebasico());
        }
        
        if(p2 < V){
            Q2.setEnabled(false);
            W2.setEnabled(false);
            E2.setEnabled(false);
            R2.setEnabled(false);
            AtaqueBasico2.setEnabled(false);
        }else{
            if(p2bot){
                return;
            }
            List<javax.swing.JButton> list2 = Arrays.asList(Q2, W2, E2, R2);
        
            for(int i=0; i<4; i++){
                checaStatusHabilidade(player2.getHabilidades().get(i), list2.get(i));
            }      
            this.AtaqueBasico2.setEnabled(player2.isAtaquebasico());
        }
    }
    
    private void checaStatusHabilidade(Habilidade h,javax.swing.JButton botao ){
        
        switch(h.getDisponivel()){
            case -1:
                botao.setEnabled(false);
                break;
            case 1:
                botao.setEnabled(true);
                break;
            case 2:
                botao.setEnabled(false);
                break;
            default:
                botao.setEnabled(false);
                break;    
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

        MinimizeButton = new javax.swing.JButton();
        PauseButton = new javax.swing.JButton();
        Relogio = new javax.swing.JLabel();
        Vida1 = new javax.swing.JProgressBar();
        Mana1 = new javax.swing.JProgressBar();
        Vida2 = new javax.swing.JProgressBar();
        Mana2 = new javax.swing.JProgressBar();
        Descricao3 = new javax.swing.JLabel();
        NomePersonagem1 = new javax.swing.JLabel();
        NomePersonagem2 = new javax.swing.JLabel();
        Habilidades1 = new javax.swing.JPanel();
        AtaqueBasico1 = new javax.swing.JButton();
        Q1 = new javax.swing.JButton();
        W1 = new javax.swing.JButton();
        E1 = new javax.swing.JButton();
        R1 = new javax.swing.JButton();
        CD1 = new javax.swing.JProgressBar();
        Barra1 = new javax.swing.JLabel();
        FundoBarra1 = new javax.swing.JLabel();
        Cooldown1 = new javax.swing.JLabel();
        NomeHabilidade1 = new javax.swing.JLabel();
        Descricao1 = new javax.swing.JLabel();
        ManaT1 = new javax.swing.JLabel();
        titulomana1 = new javax.swing.JLabel();
        titulocooldown1 = new javax.swing.JLabel();
        Habilidades2 = new javax.swing.JPanel();
        AtaqueBasico2 = new javax.swing.JButton();
        Q2 = new javax.swing.JButton();
        W2 = new javax.swing.JButton();
        E2 = new javax.swing.JButton();
        R2 = new javax.swing.JButton();
        CD2 = new javax.swing.JProgressBar();
        Barra2 = new javax.swing.JLabel();
        FundoBarra2 = new javax.swing.JLabel();
        Descricao2 = new javax.swing.JLabel();
        NomeHabilidade2 = new javax.swing.JLabel();
        titulomana2 = new javax.swing.JLabel();
        ManaT2 = new javax.swing.JLabel();
        Cooldown2 = new javax.swing.JLabel();
        titulocooldown2 = new javax.swing.JLabel();
        log4 = new javax.swing.JLabel();
        log3 = new javax.swing.JLabel();
        log2 = new javax.swing.JLabel();
        log1 = new javax.swing.JLabel();
        Borda = new javax.swing.JLabel();
        FundoMenu = new javax.swing.JLabel();
        Skill2 = new javax.swing.JLabel();
        Skill1 = new javax.swing.JLabel();
        Personagem1 = new javax.swing.JLabel();
        Personagem2 = new javax.swing.JLabel();
        Cenario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 1280, 720));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MinimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/minimize.png"))); // NOI18N
        MinimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinimizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(MinimizeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 0, 32, 32));

        PauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/pausebutton.png"))); // NOI18N
        PauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PauseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PauseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 32, 32));

        Relogio.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        Relogio.setForeground(new java.awt.Color(255, 255, 255));
        Relogio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Relogio, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 0, 210, 88));

        Vida1.setBackground(new java.awt.Color(0, 0, 0));
        Vida1.setForeground(new java.awt.Color(0, 204, 102));
        Vida1.setToolTipText("");
        Vida1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        getContentPane().add(Vida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 230, 30));

        Mana1.setBackground(new java.awt.Color(0, 0, 0));
        Mana1.setForeground(new java.awt.Color(0, 153, 255));
        Mana1.setToolTipText("");
        Mana1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        getContentPane().add(Mana1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 230, 10));

        Vida2.setBackground(new java.awt.Color(0, 0, 0));
        Vida2.setForeground(new java.awt.Color(0, 204, 102));
        Vida2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        getContentPane().add(Vida2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 110, 230, 30));

        Mana2.setBackground(new java.awt.Color(0, 0, 0));
        Mana2.setForeground(new java.awt.Color(0, 153, 255));
        Mana2.setToolTipText("");
        Mana2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(255, 255, 255)));
        getContentPane().add(Mana2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 230, 10));

        Descricao3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Descricao3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Descricao3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Descricao3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(Descricao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 554, 470, 120));

        NomePersonagem1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        NomePersonagem1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(NomePersonagem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 250, 50));

        NomePersonagem2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        NomePersonagem2.setForeground(new java.awt.Color(255, 255, 255));
        NomePersonagem2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        getContentPane().add(NomePersonagem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 250, 50));

        Habilidades1.setOpaque(false);
        Habilidades1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AtaqueBasico1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/AtaqueBasico.png"))); // NOI18N
        AtaqueBasico1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        AtaqueBasico1.setBorderPainted(false);
        AtaqueBasico1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AtaqueBasico1MouseEntered(evt);
            }
        });
        AtaqueBasico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtaqueBasico1ActionPerformed(evt);
            }
        });
        Habilidades1.add(AtaqueBasico1, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 43, 45, 45));

        Q1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        Q1.setBorderPainted(false);
        Q1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Q1MouseEntered(evt);
            }
        });
        Q1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q1ActionPerformed(evt);
            }
        });
        Habilidades1.add(Q1, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 44, 60, 60));

        W1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        W1.setBorderPainted(false);
        W1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                W1MouseEntered(evt);
            }
        });
        W1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                W1ActionPerformed(evt);
            }
        });
        Habilidades1.add(W1, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 44, 60, 60));

        E1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        E1.setBorderPainted(false);
        E1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                E1MouseEntered(evt);
            }
        });
        E1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E1ActionPerformed(evt);
            }
        });
        Habilidades1.add(E1, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 44, 60, 60));

        R1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        R1.setBorderPainted(false);
        R1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                R1MouseEntered(evt);
            }
        });
        R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1ActionPerformed(evt);
            }
        });
        Habilidades1.add(R1, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 44, 60, 60));
        Habilidades1.add(CD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 108, 318, -1));

        Barra1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Barra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/BarraSemLetras.png"))); // NOI18N
        Habilidades1.add(Barra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 320, -1));

        FundoBarra1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FundoBarra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Fundo BarraSkills.png"))); // NOI18N
        Habilidades1.add(FundoBarra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 140));

        Cooldown1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cooldown1.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades1.add(Cooldown1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        NomeHabilidade1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NomeHabilidade1.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades1.add(NomeHabilidade1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, -1));

        Descricao1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Descricao1.setForeground(new java.awt.Color(255, 255, 255));
        Descricao1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Descricao1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Descricao1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Habilidades1.add(Descricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 330, 80));

        ManaT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ManaT1.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades1.add(ManaT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        titulomana1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulomana1.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades1.add(titulomana1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        titulocooldown1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulocooldown1.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades1.add(titulocooldown1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        getContentPane().add(Habilidades1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 420, 510, 300));

        Habilidades2.setOpaque(false);
        Habilidades2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AtaqueBasico2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/AtaqueBasico.png"))); // NOI18N
        AtaqueBasico2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        AtaqueBasico2.setBorderPainted(false);
        AtaqueBasico2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AtaqueBasico2MouseEntered(evt);
            }
        });
        AtaqueBasico2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtaqueBasico2ActionPerformed(evt);
            }
        });
        Habilidades2.add(AtaqueBasico2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 43, 45, 45));

        Q2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        Q2.setBorderPainted(false);
        Q2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Q2MouseEntered(evt);
            }
        });
        Q2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Q2ActionPerformed(evt);
            }
        });
        Habilidades2.add(Q2, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 44, 60, 60));

        W2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        W2.setBorderPainted(false);
        W2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                W2MouseEntered(evt);
            }
        });
        W2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                W2ActionPerformed(evt);
            }
        });
        Habilidades2.add(W2, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 44, 60, 60));

        E2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        E2.setBorderPainted(false);
        E2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                E2MouseEntered(evt);
            }
        });
        E2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E2ActionPerformed(evt);
            }
        });
        Habilidades2.add(E2, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 44, 60, 60));

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        R2.setBorderPainted(false);
        R2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                R2MouseEntered(evt);
            }
        });
        R2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R2ActionPerformed(evt);
            }
        });
        Habilidades2.add(R2, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 44, 60, 60));
        Habilidades2.add(CD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 108, 318, -1));

        Barra2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Barra2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/BarraSemLetras2.png"))); // NOI18N
        Habilidades2.add(Barra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 320, -1));

        FundoBarra2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FundoBarra2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Fundo BarraSkills2.png"))); // NOI18N
        Habilidades2.add(FundoBarra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 140));

        Descricao2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Descricao2.setForeground(new java.awt.Color(255, 255, 255));
        Descricao2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Descricao2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Descricao2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        Habilidades2.add(Descricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 330, 80));

        NomeHabilidade2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NomeHabilidade2.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades2.add(NomeHabilidade2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        titulomana2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulomana2.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades2.add(titulomana2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        ManaT2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ManaT2.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades2.add(ManaT2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, -1, -1));

        Cooldown2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cooldown2.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades2.add(Cooldown2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        titulocooldown2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulocooldown2.setForeground(new java.awt.Color(255, 255, 255));
        Habilidades2.add(titulocooldown2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        getContentPane().add(Habilidades2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 420, 390, 300));

        log4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        log4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Perg.png"))); // NOI18N
        log4.setText("jLabel1");
        log4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(log4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 614, 340, 100));

        log3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Perg.png"))); // NOI18N
        log3.setText("jLabel1");
        log3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(log3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 614, 340, 100));

        log2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        log2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Perg.png"))); // NOI18N
        log2.setText("jLabel1");
        log2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(log2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 514, 340, 100));

        log1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Perg.png"))); // NOI18N
        log1.setText("jLabel1");
        log1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(log1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 514, 340, 100));

        Borda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Borda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/Borda.png"))); // NOI18N
        getContentPane().add(Borda, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 610, 80));

        FundoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Telas/TelaCombate/FundoMenu.jpg"))); // NOI18N
        FundoMenu.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        FundoMenu.setMaximumSize(new java.awt.Dimension(1280, 864));
        FundoMenu.setMinimumSize(new java.awt.Dimension(1280, 720));
        FundoMenu.setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().add(FundoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 1280, 540));

        Skill2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Skill2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 600, 500));

        Skill1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Skill1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 600, 500));

        Personagem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Personagem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Personagens/Inumanos/AnimalEsquerda.gif"))); // NOI18N
        Personagem1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(Personagem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 480, 320));

        Personagem2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Personagem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Personagens/Humanos/PaladinoDireita.gif"))); // NOI18N
        Personagem2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(Personagem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 480, 320));

        Cenario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cenario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Personagens/Inumanos/AnimalCenario.gif"))); // NOI18N
        Cenario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Cenario.setFocusable(false);
        getContentPane().add(Cenario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -58, 1280, 538));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void adicionahabilidadefila(int id, Personagem usuario, Personagem alvo) {
        int hab[] = new int[2];
        if(usuario.equals(player1)){
            hab[0] = 1;
            p1 = 0;
        }else{
            hab[0] = 2;
            p2 = 0;
        }
        hab[1] = id;
        fila.add(hab);
    }
    
    public void desabilitaUso(){
        Q1.setEnabled(false);
        W1.setEnabled(false);
        E1.setEnabled(false);
        R1.setEnabled(false);
        AtaqueBasico1.setEnabled(false);
        
        Q2.setEnabled(false);
        W2.setEnabled(false);
        E2.setEnabled(false);
        R2.setEnabled(false);
        AtaqueBasico2.setEnabled(false);
    }
    
    public void iniciaAtaque(int id, Personagem p, Personagem alvo){
        desabilitaUso();
        paraTempo();
        
        ThreadAtaque ata = new ThreadAtaque(0,id,p,alvo);
        ataque = new Thread(ata);
        ataque.start();
    }
    
    public void usahabilidade(int id, Personagem p, Personagem alvo){
        if(p.equals(player1)){
            p1 = 0;
        }else{
            p2 = 0;
        }
        CD1.setValue(p1);
        CD2.setValue(p2);
        
        check();
        
        if(this.segundoo){
            adicionahabilidadefila(id, p, alvo);
        }else{
            this.iniciaAtaque(id, p, alvo);
        }  
    }
    
    private void Q1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q1ActionPerformed
        usahabilidade(0,player1,player2);
    }//GEN-LAST:event_Q1ActionPerformed

    private void Q2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Q2ActionPerformed
        usahabilidade(0,player2,player1);
    }//GEN-LAST:event_Q2ActionPerformed

    private void W1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_W1ActionPerformed
        usahabilidade(1,player1,player2);
    }//GEN-LAST:event_W1ActionPerformed

    private void W2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_W2ActionPerformed
        usahabilidade(1,player2,player1);
    }//GEN-LAST:event_W2ActionPerformed

    private void E1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E1ActionPerformed
        usahabilidade(2,player1,player2);
    }//GEN-LAST:event_E1ActionPerformed

    private void E2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E2ActionPerformed
        usahabilidade(2,player2,player1);
    }//GEN-LAST:event_E2ActionPerformed

    private void R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1ActionPerformed
        usahabilidade(3,player1,player2);
    }//GEN-LAST:event_R1ActionPerformed

    private void R2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R2ActionPerformed
        usahabilidade(3,player2,player1);
    }//GEN-LAST:event_R2ActionPerformed

    public void iniciaAtaqueBasico(Personagem p, Personagem alvo){
        desabilitaUso();
        paraTempo();

        ThreadAtaque ata = new ThreadAtaque(1,0,p,alvo);
        ataque = new Thread(ata);
        ataque.start();
    }
    
    private void ataqueBasico1(){
        p1 = 0;
        
        CD1.setValue(p1);
        check();
        
        if(this.segundoo){
            int hab[] = new int[2];
            hab[0] = 1;
            hab[1] = 4;
            fila.add(hab);
            return;
        }
        
        this.iniciaAtaqueBasico(player1, player2);
    }
    
    private void AtaqueBasico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtaqueBasico1ActionPerformed
        ataqueBasico1();
    }//GEN-LAST:event_AtaqueBasico1ActionPerformed

    private void ataqueBasico2(){
        p2 = 0;
        
        CD2.setValue(p2);
        check();
        
        if(this.segundoo){
            int hab[] = new int[2];
            hab[0] = 2;
            hab[1] = 4;
            fila.add(hab);
            return;
        }
        
        this.iniciaAtaqueBasico(player2, player1);
    }
    
    private void AtaqueBasico2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtaqueBasico2ActionPerformed
        ataqueBasico2();
    }//GEN-LAST:event_AtaqueBasico2ActionPerformed

    public void fechatela(){
        if(ataque != null){
            ataque.stop();
        }
        tempo.cancel();
        if(controller1 != null){
            comandos.cancel();
        }
        if(audio != null){
            audio.stop();
        }
        this.setEnabled(false);
        this.dispose();
    }
    
    private void PauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PauseButtonActionPerformed
        pause = true;
        if(tempocorre){
            this.paraTempo();
        }
        if(controller1 != null){
            comandos.cancel();
        }
        this.setEnabled(false);
        Pause p = new Pause(height, width, musica, controller1,controller2,this,p2bot,idp2);
        p.setVisible(true);
    }//GEN-LAST:event_PauseButtonActionPerformed

    private void limpadetalhes1(){
        this.NomeHabilidade1.setText("");
        this.Descricao1.setText("");
        this.titulomana1.setText("");
        this.titulocooldown1.setText("");
        this.ManaT1.setText("");
        this.Cooldown1.setText("");
    } 
    
    private void limpadetalhes2(){
        this.NomeHabilidade2.setText("");
        this.Descricao2.setText("");
        this.titulomana2.setText("");
        this.titulocooldown2.setText("");
        this.ManaT2.setText("");
        this.Cooldown2.setText("");
    } 
    
    private void Q1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q1MouseEntered
        this.hab1 = 1;
        mostraPrevia1();
    }//GEN-LAST:event_Q1MouseEntered

    private void W1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_W1MouseEntered
        this.hab1 = 2;
        mostraPrevia1();
    }//GEN-LAST:event_W1MouseEntered

    private void E1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E1MouseEntered
        this.hab1 = 3;
        mostraPrevia1();
    }//GEN-LAST:event_E1MouseEntered

    private void R1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R1MouseEntered
        this.hab1 = 4;
        mostraPrevia1();
    }//GEN-LAST:event_R1MouseEntered

    private void Q2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q2MouseEntered
        this.hab2 = 1;
        mostraPrevia2();
    }//GEN-LAST:event_Q2MouseEntered

    private void W2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_W2MouseEntered
        this.hab2 = 2;
        mostraPrevia2();
    }//GEN-LAST:event_W2MouseEntered

    private void E2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_E2MouseEntered
        this.hab2 = 3;
        mostraPrevia2();
    }//GEN-LAST:event_E2MouseEntered

    private void R2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_R2MouseEntered
        this.hab2 = 4;
        mostraPrevia2();
    }//GEN-LAST:event_R2MouseEntered

    private void MinimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinimizeButtonActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_MinimizeButtonActionPerformed

    private void AtaqueBasico1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtaqueBasico1MouseEntered
        this.hab1 = 0;
        mostraPrevia1();
    }//GEN-LAST:event_AtaqueBasico1MouseEntered

    private void AtaqueBasico2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtaqueBasico2MouseEntered
        this.hab2 = 0;
        mostraPrevia2();
    }//GEN-LAST:event_AtaqueBasico2MouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtaqueBasico1;
    private javax.swing.JButton AtaqueBasico2;
    private javax.swing.JLabel Barra1;
    private javax.swing.JLabel Barra2;
    private javax.swing.JLabel Borda;
    private javax.swing.JProgressBar CD1;
    private javax.swing.JProgressBar CD2;
    private javax.swing.JLabel Cenario;
    private javax.swing.JLabel Cooldown1;
    private javax.swing.JLabel Cooldown2;
    private javax.swing.JLabel Descricao1;
    private javax.swing.JLabel Descricao2;
    private javax.swing.JLabel Descricao3;
    private javax.swing.JButton E1;
    private javax.swing.JButton E2;
    private javax.swing.JLabel FundoBarra1;
    private javax.swing.JLabel FundoBarra2;
    private javax.swing.JLabel FundoMenu;
    private javax.swing.JPanel Habilidades1;
    private javax.swing.JPanel Habilidades2;
    private javax.swing.JProgressBar Mana1;
    private javax.swing.JProgressBar Mana2;
    private javax.swing.JLabel ManaT1;
    private javax.swing.JLabel ManaT2;
    private javax.swing.JButton MinimizeButton;
    private javax.swing.JLabel NomeHabilidade1;
    private javax.swing.JLabel NomeHabilidade2;
    private javax.swing.JLabel NomePersonagem1;
    private javax.swing.JLabel NomePersonagem2;
    private javax.swing.JButton PauseButton;
    private javax.swing.JLabel Personagem1;
    private javax.swing.JLabel Personagem2;
    private javax.swing.JButton Q1;
    private javax.swing.JButton Q2;
    private javax.swing.JButton R1;
    private javax.swing.JButton R2;
    private javax.swing.JLabel Relogio;
    private javax.swing.JLabel Skill1;
    private javax.swing.JLabel Skill2;
    private javax.swing.JProgressBar Vida1;
    private javax.swing.JProgressBar Vida2;
    private javax.swing.JButton W1;
    private javax.swing.JButton W2;
    private javax.swing.JLabel log1;
    private javax.swing.JLabel log2;
    private javax.swing.JLabel log3;
    private javax.swing.JLabel log4;
    private javax.swing.JLabel titulocooldown1;
    private javax.swing.JLabel titulocooldown2;
    private javax.swing.JLabel titulomana1;
    private javax.swing.JLabel titulomana2;
    // End of variables declaration//GEN-END:variables
}
