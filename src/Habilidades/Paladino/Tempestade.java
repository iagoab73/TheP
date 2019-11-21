package Habilidades.Paladino;

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
public class Tempestade extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Tempestade";
    private static final String DESCRICAO = "Cria uma rápida tempestade que causa dano mágico ao alvo.";
    private static final int GASTODEMANA = 10;
    private static final int COOLDOWN = 12000;
    private static final int MULTIPLICADOR = 350;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("Tempestade1.81.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("Tempestade1.81.gif"));

    // Construtor da classe.

    public Tempestade() {
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
        return new ImageIcon(getClass().getResource("Q_TempestadeG.png"));
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
        return null;
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1810);
    }
}
