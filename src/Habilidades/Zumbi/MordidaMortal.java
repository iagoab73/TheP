package Habilidades.Zumbi;

import Habilidades.BuffouDebuff;
import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class MordidaMortal extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Mordida Mortal";
    private static final String DESCRICAO = "Morde o alvo, causando dano mágico e o deixando sangrando por um período.";
    private static final int GASTODEMANA = 30;
    private static final int COOLDOWN = 65000;
    private static final int MULTIPLICADOR = 400;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("MordidaMortal2.75.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("MordidaMortal2.75.gif"));

    // Construtor da classe.

    public MordidaMortal() {
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
        return new ImageIcon(getClass().getResource("R_MordidaMortalG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return null;
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecialinimigo(){
        return new EfeitoMordidaMortal();
    }

    @Override
    public List<BuffouDebuff> getBuffs(){
        return null;
    }

    @Override
    public List<BuffouDebuff> getDebuffs(){
        return null;
    }
    
    @Override
    public CC getCc(){
        return null;
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 2750);
    }
}
