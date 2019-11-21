package Personagens.Inumanos;

import Habilidades.Zumbi.PactoComAMorte;
import Habilidades.Zumbi.MordidaMortal;
import Habilidades.Zumbi.DefesaDosMortos;
import Habilidades.Zumbi.AtaqueMacabro;
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
public class Zumbi extends Personagem{
    
    // Constantes dos atributos do personagem.
    private static final String CLASSE = "Zumbi";
    private static final int VIDA = 2800;
    private static final int MANA = 70;
    private static final int FORCAFISICA = 40;
    private static final int FORCAMAGICA = 40;
    private static final int RESISTENCIAFISICA = 70;
    private static final int RESISTENCIAMAGICA = 70;
    private static final int AGILIDADE = 60;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("ZumbiEsquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("ZumbiDireita.gif"));
    private final ImageIcon NOMEGRANDE = new ImageIcon(getClass().getResource("NickZumbi_1.png"));
    private final ImageIcon EFEITOATAQUEBASICOESQUERDA = new ImageIcon(getClass().getResource("AtaqueBasico1.97-Esquerda.gif"));
    private final ImageIcon EFEITOATAQUEBASICODIREITA = new ImageIcon(getClass().getResource("AtaqueBasico1.97-Direita.gif"));
    
    private static final List<Habilidade> HABILIDADES = Arrays.asList(new AtaqueMacabro(), new DefesaDosMortos(), new PactoComAMorte(), new MordidaMortal());
    // Construtor da classe, que atribui as constantes deste personagem à sua super-classe.
    public Zumbi() {
        super(CLASSE, VIDA, MANA, FORCAFISICA, FORCAMAGICA, RESISTENCIAFISICA, RESISTENCIAMAGICA, AGILIDADE, Arrays.asList(new AtaqueMacabro(), new DefesaDosMortos(), new PactoComAMorte(), new MordidaMortal()));
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
        return new ImageIcon(getClass().getResource("ZumbiCenario.gif"));
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
        tela.mostraEfeitoAtaqueBasico(aThis,1970);
    }
}
