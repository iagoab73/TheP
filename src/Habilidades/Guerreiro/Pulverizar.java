package Habilidades.Guerreiro;

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
public class Pulverizar extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Pulverizar";
    private static final String DESCRICAO = "Dá uma sequência de golpes, causando dano mágico e deixando o alvo atordoado.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 30000;
    private static final int MULTIPLICADOR = 300;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("Pulverizar2.06-Esquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("Pulverizar2.06-Direita.gif"));

    // Construtor da classe.

    public Pulverizar() {
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
        return new ImageIcon(getClass().getResource("E_PulverizarG.png"));
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
        return new CC(0, 5000);
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 2060);
    }
}