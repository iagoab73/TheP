package Habilidades.Mago;

import Habilidades.BuffouDebuff;
import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class FlamaGelada extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Flama Gelada";
    private static final String DESCRICAO = "Dá dano mágico ao alvo, além de diminuir sua agilidade por um período.";
    private static final int GASTODEMANA = 15;
    private static final int COOLDOWN = 10000;
    private static final int MULTIPLICADOR = 4000;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("FlamaGeladaDireita1.96.gif"));;
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("FlamaGeladaEsquerda1.96.gif"));;

    // Construtor da classe.

    public FlamaGelada() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }

    @Override
    public ImageIcon getEfeitoEsquerda() {
        return EFEITOESQUERDA;
    }
    
    @Override
    public ImageIcon getEfeitoDireita(){
        return EFEITODIREITA;
    }
    
    @Override
    public int getCooldownBase() {
        return COOLDOWN;
    }
    
    @Override
    public ImageIcon getIcone(){
        return new ImageIcon(getClass().getResource("Q_FlamaGeladaG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return null;
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecialinimigo(){
        return null;
    }

    @Override
    public List<BuffouDebuff> getBuffs(){
        return null;
    }

    @Override
    public List<BuffouDebuff> getDebuffs(){
        return Arrays.asList(new BuffouDebuff(4,0.20f,10000,this.getNome()));
    }
    
    @Override
    public CC getCc(){
        return null;
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1960);
    }
}
