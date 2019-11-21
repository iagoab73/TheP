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
public class ModoDeus extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Modo Deus";
    private static final String DESCRICAO = "Ascende, ganhando um buff em todos os seus stats e ficando imune à qualquer controle direcionado a ele.";
    private static final int GASTODEMANA = 35;
    private static final int COOLDOWN = 70000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("ModoDeus1.50.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("ModoDeus1.50.gif"));

    // Construtor da classe.

    public ModoDeus() {
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
        return new ImageIcon(getClass().getResource("R_ModoDeusG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoModoDeus();
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecialinimigo(){
        return null;
    }

    @Override
    public List<BuffouDebuff> getBuffs(){
        return Arrays.asList(new BuffouDebuff(0, 0.30f, 10000,this.getNome()), new BuffouDebuff(1, 0.30f, 10000,this.getNome()), new BuffouDebuff(2, 0.30f, 10000,this.getNome()), new BuffouDebuff(3, 0.30f, 10000,this.getNome()), new BuffouDebuff(4, 0.30f, 10000,this.getNome()));
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
        tela.mostraEfeito(atacante, 0, this, 1500);
    }
}
