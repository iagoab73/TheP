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
public class AgilidadeSombria extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Agilidade Sombria";
    private static final String DESCRICAO = "Se adapta aos ataques adversários, fazendo que desvie de um dos próximos 5 ataques inimigo e contra ataque.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 50000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("AgilidadeSombia2.25.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("AgilidadeSombia2.25.gif"));

    // Construtor da classe.

    public AgilidadeSombria() {
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
        return new ImageIcon(getClass().getResource("W_AgilidadeSombriaG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoAgilidadeSombria();
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
        tela.mostraEfeito(atacante, 0, this, 2250);
    }
}
