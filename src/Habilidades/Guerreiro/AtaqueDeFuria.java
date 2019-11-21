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
public class AtaqueDeFuria extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Ataque de Fúria";
    private static final String DESCRICAO = "Usa sua fúria para atacar duas vezes de uma vez.";
    private static final int GASTODEMANA = 5;
    private static final int COOLDOWN = 8000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = null;
    private final ImageIcon EFEITODIREITA = null;

    // Construtor da classe.

    public AtaqueDeFuria() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }

    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
                
        this.acao(atacante, defesa);
    }
    
    @Override
    public void acao(Personagem atacante, Personagem defesa){
        atacante.ataqueBásico(defesa);
        atacante.ataqueBásico(defesa);
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
        return new ImageIcon(getClass().getResource("Q_AtaqueFuriaG.png"));
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
}