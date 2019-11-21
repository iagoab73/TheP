package Habilidades.Dragao;

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
public class RugidoMortal extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Rugido Mortal";
    private static final String DESCRICAO = "Dá um rugido que amedronta o alvo, diminuindo sua resistência física por um período.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 30000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("RugidoMortal1.30-Esquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("RugidoMortal1.30-Direita.gif"));

    // Construtor da classe.

    public RugidoMortal() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
        this.mostraefeito(atacante, tela);
        super.acao(atacante, defesa);
    }

    public ImageIcon getEfeitoEsquerda() {
        return EFEITOESQUERDA;
    }
    
    public ImageIcon getEfeitoDireita(){
        return EFEITODIREITA;
    }
    
    @Override
    public int getCooldownBase() {
        return COOLDOWN;
    }
    
    @Override
    public ImageIcon getIcone(){
        return new ImageIcon(getClass().getResource("W_RugidoMortalG.png"));
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
        return Arrays.asList(new BuffouDebuff(2, 0.5f, 7000,this.getNome()));
    }
    
    @Override
    public CC getCc(){
        return null;
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1300);
    }
}
