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
public class HalitoDeFogo extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Hálito De Fogo";
    private static final String DESCRICAO = "Cospe fogo, causando dano mágico ao alvo.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 12000;
    private static final int MULTIPLICADOR = 350;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("HalitoDeFogo-1,69-Esquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("HalitoDeFogo1.69-Direita.gif"));

    // Construtor da classe.

    public HalitoDeFogo() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
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
        return new ImageIcon(getClass().getResource("Q_HalitoFogoG.png"));
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
        tela.mostraEfeito(atacante, 1, this, 1690);
    }
}
