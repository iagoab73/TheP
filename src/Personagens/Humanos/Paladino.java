package Personagens.Humanos;

import Habilidades.Paladino.ModoDeus;
import Habilidades.Paladino.Tempestade;
import Habilidades.Paladino.Protecao;
import Habilidades.Paladino.BencaoCelestial;
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
public class Paladino extends Personagem{
    
    // Constantes dos atributos do personagem.
    private static final String CLASSE = "Paladino";
    private static final int VIDA = 3200;
    private static final int MANA = 80;
    private static final int FORCAFISICA = 80;
    private static final int FORCAMAGICA = 70;
    private static final int RESISTENCIAFISICA = 60;
    private static final int RESISTENCIAMAGICA = 60;
    private static final int AGILIDADE = 80;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("PaladinoEsquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("PaladinoDireita.gif"));
    private final ImageIcon NOMEGRANDE = new ImageIcon(getClass().getResource("NickPaladino_1.png"));
    private final ImageIcon EFEITOATAQUEBASICOESQUERDA = new ImageIcon(getClass().getResource("AtaqBasicoDireita1.47.gif"));
    private final ImageIcon EFEITOATAQUEBASICODIREITA = new ImageIcon(getClass().getResource("AtaqBasicoEsquerda1.47.gif"));
    
    private static final List<Habilidade> HABILIDADES = Arrays.asList(new Tempestade(), new Protecao(), new BencaoCelestial(), new ModoDeus());

    // Construtor da classe, que atribui as constantes deste personagem à sua super-classe.
    public Paladino() {
        super(CLASSE, VIDA, MANA, FORCAFISICA, FORCAMAGICA, RESISTENCIAFISICA, RESISTENCIAMAGICA, AGILIDADE, Arrays.asList(new Tempestade(), new Protecao(), new BencaoCelestial(), new ModoDeus()));
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
        return new ImageIcon(getClass().getResource("PaladinoCenario.gif"));
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
        tela.mostraEfeitoAtaqueBasico(aThis,1470);
    }
}
