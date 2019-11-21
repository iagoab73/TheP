package Habilidades.Troll;

import Habilidades.BuffouDebuff;
import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class Pancada extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Pancada";
    private static final String DESCRICAO = "Dá um pancada no alvo, causando dano mágico e impedindo que o alvo use habilidades por um período de tempo.";
    private static final int GASTODEMANA = 15;
    private static final int COOLDOWN = 20000;
    private static final int MULTIPLICADOR = 500;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("PancadaDireita1.53.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("PancadaEsquerda1.53.gif"));

    // Construtor da classe.

    public Pancada() {
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
        return new ImageIcon(getClass().getResource("Q_PancadaG.png"));
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
        return null;
    }
    
    @Override
    public CC getCc(){
        return new CC(1,10000);
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1530);
    }
}
