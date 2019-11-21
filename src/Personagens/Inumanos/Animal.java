package Personagens.Inumanos;

import Habilidades.Animal.Selvageria;
import Habilidades.Animal.FuriaSelvagem;
import Habilidades.Animal.SedeDeSangue;
import Habilidades.Animal.Mordida;
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
public class Animal extends Personagem{
    
    // Constantes dos atributos do personagem.
    private static final String CLASSE = "Animal";
    private static final int VIDA = 3500;
    private static final int MANA = 35;
    private static final int FORCAFISICA = 80;
    private static final int FORCAMAGICA = 20;
    private static final int RESISTENCIAFISICA = 70;
    private static final int RESISTENCIAMAGICA = 60;
    private static final int AGILIDADE = 100;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("AnimalEsquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("AnimalDireita.gif"));
    private final ImageIcon NOMEGRANDE = new ImageIcon(getClass().getResource("NickAnimal_1.png"));
    private final ImageIcon EFEITOATAQUEBASICOESQUERDA = new ImageIcon(getClass().getResource("AtaqBasico-0,94-Esquerda.gif"));
    private final ImageIcon EFEITOATAQUEBASICODIREITA = new ImageIcon(getClass().getResource("AtaqBasico-0,94-Direita.gif"));
    
    private static final List<Habilidade> HABILIDADES = Arrays.asList(new Mordida(), new SedeDeSangue(), new FuriaSelvagem(), new Selvageria());
    // Construtor da classe, que atribui as constantes deste personagem à sua super-classe.
    public Animal() {
        super(CLASSE, VIDA, MANA, FORCAFISICA, FORCAMAGICA, RESISTENCIAFISICA, RESISTENCIAMAGICA, AGILIDADE, Arrays.asList(new Mordida(), new SedeDeSangue(), new FuriaSelvagem(), new Selvageria()));
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
        return new ImageIcon(getClass().getResource("AnimalCenario.gif"));
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
        tela.mostraEfeitoAtaqueBasico(aThis,940);
    }
}

