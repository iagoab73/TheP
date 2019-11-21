package Habilidades.Assassino;

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
public class ArmadilhaDeFlechas extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Armadilha de Flechas";
    private static final String DESCRICAO = "Ataca o alvo como uma armadilha de flechas, causando dano mágico ao alvo.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 10000;
    private static final int MULTIPLICADOR = 300;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("ArmadilhaFlecha1.87.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("ArmadilhaFlecha1.87.gif"));

    // Construtor da classe.

    public ArmadilhaDeFlechas() {
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
        return new ImageIcon(getClass().getResource("Q_ArmadilhaFlechasG.png"));
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
        tela.mostraEfeito(atacante, 1, this, 1870);
    }
}
