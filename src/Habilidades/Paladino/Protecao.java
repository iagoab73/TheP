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
public class Protecao extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Proteção";
    private static final String DESCRICAO = "Se prepara pra defender o próximo ataque, e causa dano ao atacante com base no dano original.";
    private static final int GASTODEMANA = 30;
    private static final int COOLDOWN = 35000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("Protecao(Ativando)1.35.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("Protecao(Ativando)1.35.gif"));
    private int status = 0;
    
    // Construtor da classe.

    public Protecao() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTextoLog("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
        this.mostraefeito(atacante, tela);
        status = 1;
        super.acao(atacante, defesa);
    }

    @Override
    public ImageIcon getEfeitoEsquerda() {
        if(status == 1){
            status = 0;
            return new ImageIcon(getClass().getResource("Protecao1.44-Esquerda.gif"));
        }
        return EFEITOESQUERDA;
    }
    
    @Override
    public ImageIcon getEfeitoDireita(){
        if(status == 1){
            status = 0;
            return new ImageIcon(getClass().getResource("Protecao1.44-Direita.gif"));
        }
        return EFEITODIREITA;
    }
    
    @Override
    public int getCooldownBase() {
        return COOLDOWN;
    }
    
    @Override
    public ImageIcon getIcone(){
        return new ImageIcon(getClass().getResource("W_ProtecaoG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoProtecao();
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
        tela.mostraEfeito(atacante, 0, this, 1350);
    }
}
