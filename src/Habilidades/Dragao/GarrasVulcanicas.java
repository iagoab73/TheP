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
public class GarrasVulcanicas extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Garras Vulcânicas";
    private static final String DESCRICAO = "Usa sua afinidade com o fogo para deixar suas garras muito quentes, fazendo com que seus ataques básicos cusem dano mágico bônus.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 25000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("GarrasVulcanicas-0,98-.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("GarrasVulcanicas-0,98-.gif"));

    // Construtor da classe.

    public GarrasVulcanicas() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
        this.mostraefeito(atacante, tela);
        super.acao(atacante, defesa);
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
        return new ImageIcon(getClass().getResource("E_GarrasG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoGarrasVulcanicas();
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
        tela.mostraEfeito(atacante, 0, this, 980);
    }
}
