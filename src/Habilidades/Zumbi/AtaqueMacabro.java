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
public class AtaqueMacabro extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Ataque Macabro";
    private static final String DESCRICAO = "Ataca o alvo, causando dano mágico e diminuindo suas resistências.";
    private static final int GASTODEMANA = 15;
    private static final int COOLDOWN = 15000;
    private static final int MULTIPLICADOR = 250;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("AtaqueMacabro1.85.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("AtaqueMacabro1.85.gif"));

    // Construtor da classe.

    public AtaqueMacabro() {
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
        return new ImageIcon(getClass().getResource("Q_AtaqueMacabroG.png"));
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
        return (Arrays.asList(new BuffouDebuff(2,0.30f,10000,this.getNome()), new BuffouDebuff(3,0.30f,10000,this.getNome())));
    }
    
    @Override
    public CC getCc(){
        return null;
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1850);
    }
}

