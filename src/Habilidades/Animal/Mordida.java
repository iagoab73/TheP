package Habilidades.Animal;

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
public class Mordida extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Mordida";
    private static final String DESCRICAO = "Dá uma mordida no alvo, causando dano magico e aplicando efeitos de contato.";
    private static final int GASTODEMANA = 10;
    private static final int COOLDOWN = 15000;
    private static final int MULTIPLICADOR = 700;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("Mordida1.28-Esquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("Mordida1.28-Direita.gif"));

    // Construtor da classe.

    public Mordida() {
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
        return new ImageIcon(getClass().getResource("Q_MordidaG.png"));
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
        tela.mostraEfeito(atacante, 1, this, 1280);
    }
}
