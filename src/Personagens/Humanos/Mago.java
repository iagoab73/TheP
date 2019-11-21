package Personagens.Humanos;

import Habilidades.Mago.RitualArcano;
import Habilidades.Mago.BencaoRunica;
import Habilidades.Mago.PrisaoMagica;
import Habilidades.Mago.FlamaGelada;
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
public class Mago extends Personagem{
    
    // Constantes dos atributos do personagem.
    private static final String CLASSE = "Mago";
    private static final int VIDA = 2500;
    private static final int MANA = 100;
    private static final int FORCAFISICA = 40;
    private static final int FORCAMAGICA = 100;
    private static final int RESISTENCIAFISICA = 50;
    private static final int RESISTENCIAMAGICA = 80;
    private static final int AGILIDADE = 65;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("MagoEsquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("MagoDireita.gif"));
    private final ImageIcon NOMEGRANDE = new ImageIcon(getClass().getResource("NickMago_1.png"));
    private final ImageIcon EFEITOATAQUEBASICOESQUERDA = new ImageIcon(getClass().getResource("AtakBasic1.68.gif"));
    private final ImageIcon EFEITOATAQUEBASICODIREITA = new ImageIcon(getClass().getResource("AtakBasic1.68.gif"));
    
    private static final List<Habilidade> HABILIDADES = Arrays.asList(new FlamaGelada(), new BencaoRunica(), new PrisaoMagica(), new RitualArcano());

    // Construtor da classe, que atribui as constantes deste personagem à sua super-classe.
    public Mago() {
        super(CLASSE, VIDA, MANA, FORCAFISICA, FORCAMAGICA, RESISTENCIAFISICA, RESISTENCIAMAGICA, AGILIDADE, Arrays.asList(new FlamaGelada(), new BencaoRunica(), new PrisaoMagica(), new RitualArcano()));
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
        return new ImageIcon(getClass().getResource("MagoCenario.gif"));
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
        tela.mostraEfeitoAtaqueBasico(aThis,1680);
    }
}
