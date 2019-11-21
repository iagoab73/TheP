package Personagens.Inumanos;

import Habilidades.Troll.Trollagem;
import Habilidades.Troll.Curiosidade;
import Habilidades.Troll.ChegaDeBrincadeira;
import Habilidades.Troll.Pancada;
import Habilidades.*;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrador
 */
public class Troll extends Personagem{
    
    // Constantes dos atributos do personagem.
    private static final String CLASSE = "Troll";
    private static final int VIDA = 3200;
    private static final int MANA = 80;
    private static final int FORCAFISICA = 90;
    private static final int FORCAMAGICA = 30;
    private static final int RESISTENCIAFISICA = 90;
    private static final int RESISTENCIAMAGICA = 50;
    private static final int AGILIDADE = 70;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("TrollEsquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("TrollDireita.gif"));
    private final ImageIcon NOMEGRANDE = new ImageIcon(getClass().getResource("NickTroll_1.png"));
    private final ImageIcon EFEITOATAQUEBASICOESQUERDA = new ImageIcon(getClass().getResource("AtaqueBasico1.20-Esquerda.gif"));
    private final ImageIcon EFEITOATAQUEBASICODIREITA = new ImageIcon(getClass().getResource("AtaqueBasico1.20-Direita.gif"));
    
    private static final List<Habilidade> HABILIDADES = Arrays.asList(new Pancada(), new Curiosidade(), new Trollagem(), new ChegaDeBrincadeira());
    // Construtor da classe, que atribui as constantes deste personagem à sua super-classe.
    public Troll() {
        super(CLASSE, VIDA, MANA, FORCAFISICA, FORCAMAGICA, RESISTENCIAFISICA, RESISTENCIAMAGICA, AGILIDADE, Arrays.asList(new Pancada(), new Curiosidade(), new Trollagem(), new ChegaDeBrincadeira()));
    }
    
    // Métodos sobrescritos da super-classe.
    
    @Override
    public int getVidaBase(){
        return VIDA;
    }
    @Override
    public int getManaBase(){
        return MANA;
    }
    
    // Gets estáticos da classe.

    public static String getCLASSE() {
        return CLASSE;
    }

    public static List<Habilidade> getHABILIDADES() {
        return HABILIDADES;
    }
    
    
    @Override
    public ImageIcon getEfeitoEsquerda(){
        return EFEITOESQUERDA;
    }
    
    @Override
    public ImageIcon getEfeitoDireita(){
        return EFEITODIREITA;
    }
    
    @Override
    public ImageIcon getImagemNomeGrande(){
        return NOMEGRANDE;
    }
    
    @Override
    public ImageIcon getCenario(){
        return new ImageIcon(getClass().getResource("TrollCenario.gif"));
    }

    @Override
    public int getForcaFisicaBase() {
        return FORCAFISICA;
    }

    @Override
    public int getForcaMagicaBase() {
        return FORCAMAGICA;
    }

    @Override
    public int getResistenciaFisicaBase() {
        return RESISTENCIAFISICA;
    }

    @Override
    public int getResistenciaMagicaBase() {
        return RESISTENCIAMAGICA;
    }

    @Override
    public int getAgilidadeBase() {
        return AGILIDADE;
    }

    @Override
    public ImageIcon getEfeitoAtaqueBasicoEsquerda() {
        return this.EFEITOATAQUEBASICOESQUERDA;
    }

    @Override
    public ImageIcon getEfeitoAtaqueBasicoDireita() {
        return this.EFEITOATAQUEBASICODIREITA;
    }
    
    @Override
    public void mostraefeitoab(Personagem aThis, Tela tela) {
        tela.mostraEfeitoAtaqueBasico(aThis,1200);
    }
}
