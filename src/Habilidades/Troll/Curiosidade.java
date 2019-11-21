package Habilidades.Troll;

import Habilidades.BuffouDebuff;
import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class Curiosidade extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Curiosidade";
    private static final String DESCRICAO = "Com sua curiosidade, aprende e utiliza a habilidade b´sica do alvo nele mesmo.";
    private static final int GASTODEMANA = 20;
    private static final int COOLDOWN = 20000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = null;
    private final ImageIcon EFEITODIREITA = null;
    private Habilidade hab;
    // Construtor da classe.

    public Curiosidade() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
        
        hab = defesa.getHabilidades().get(0);
        
        if(this.verificaBloqueio(atacante, defesa, hab.getMultiplicador() * atacante.getForcamagica(), tela)){
            return;
        }
        tela.adicionaTexto(" O " + atacante.getClasse() + " usou a habilidade " + hab.getNome() + "!");
        this.mostraefeito(atacante, tela);
        hab.acao(atacante, defesa);
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
        return new ImageIcon(getClass().getResource("W_AtaqueCuriosoG.png"));
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
        tela.mostraEfeito(atacante, 1, hab, 2000);
    }
}
