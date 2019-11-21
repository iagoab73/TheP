package Personagens.Inumanos;

import Habilidades.Dragao.HalitoDeFogo;
import Habilidades.Dragao.GarrasVulcanicas;
import Habilidades.Dragao.RugidoMortal;
import Habilidades.Dragao.VooDoDragao;
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
public class Dragao extends Personagem{
    
    
    private static final String CLASSE = "Dragão";
    private static final int VIDA = 3000;
    private static final int MANA = 80;
    private static final int FORCAFISICA = 100;
    private static final int FORCAMAGICA = 50;
    private static final int RESISTENCIAFISICA = 60;
    private static final int RESISTENCIAMAGICA = 60;
    private static final int AGILIDADE = 80;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("DragaoEsquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("DragaoDireita.gif"));
    private final ImageIcon NOMEGRANDE = new ImageIcon(getClass().getResource("NickDragao_1.png"));
    private final ImageIcon EFEITOATAQUEBASICOESQUERDA = new ImageIcon(getClass().getResource("AtaqBasico-1,89-.gif"));
    private final ImageIcon EFEITOATAQUEBASICODIREITA = new ImageIcon(getClass().getResource("AtaqBasico-1,89-.gif"));
    
    private static final List<Habilidade> HABILIDADES = Arrays.asList(new HalitoDeFogo(), new RugidoMortal(), new GarrasVulcanicas(), new VooDoDragao());
    // Construtor da classe, que atribui as constantes deste personagem à sua super-classe.
    public Dragao() {
        super(CLASSE, VIDA, MANA, FORCAFISICA, FORCAMAGICA, RESISTENCIAFISICA, RESISTENCIAMAGICA, AGILIDADE, Arrays.asList(new HalitoDeFogo(), new RugidoMortal(), new GarrasVulcanicas(), new VooDoDragao()));
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
        return new ImageIcon(getClass().getResource("DragaoCenario.gif"));
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
        for(EfeitoEspecial i : super.getEfeitos()){
            if(i.getNome().equals("Afinidade com o Fogo")){
                return new ImageIcon(getClass().getResource("BasicoComBuff1.89.gif"));
            }
        }
        return this.EFEITOATAQUEBASICOESQUERDA;
    }

    @Override
    public ImageIcon getEfeitoAtaqueBasicoDireita() {
        for(EfeitoEspecial i : super.getEfeitos()){
            if(i.getNome().equals("Afinidade com o Fogo")){
                return new ImageIcon(getClass().getResource("BasicoComBuff1.89.gif"));
            }
        }
        return this.EFEITOATAQUEBASICODIREITA;
    }

    @Override
    public void mostraefeitoab(Personagem aThis, Tela tela) {
        tela.mostraEfeitoAtaqueBasico(aThis, 1890);
    }
}
