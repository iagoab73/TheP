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
public class Trollagem extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Trollagem";
    private static final String DESCRICAO = "Usa sua habilidade na arte da trollagem para deixar o alvo confuso, fanzendo com que possa se atacar ao tentar atacar.";
    private static final int GASTODEMANA = 20;
    private static final int COOLDOWN = 40000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("Trollagem1.52.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("Trollagem1.52.gif"));

    // Construtor da classe.

    public Trollagem() {
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
        return new ImageIcon(getClass().getResource("E_TrollagemG.png"));
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
        return new CC(3,20000);
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1520);
    }
}
