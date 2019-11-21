package Habilidades.Zumbi;

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
public class DefesaDosMortos extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Defesa Dos Mortos";
    private static final String DESCRICAO = "Por um período retorna parte do dano que recebe ao seu agressor.";
    private static final int GASTODEMANA = 20;
    private static final int COOLDOWN = 25000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("DefesaMortos1.64.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("DefesaMortos1.64.gif"));

    // Construtor da classe.

    public DefesaDosMortos() {
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
        return new ImageIcon(getClass().getResource("W_DefesaMortosG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoDefesaDosMortos();
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
        tela.mostraEfeito(atacante, 0, this, 1640);
    }
}
