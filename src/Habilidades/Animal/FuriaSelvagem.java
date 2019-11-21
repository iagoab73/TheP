package Habilidades.Animal;

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
public class FuriaSelvagem extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Fúria Selvagem";
    private static final String DESCRICAO = "Diminui a força fisíca e mágica do alvo.";
    private static final int GASTODEMANA = 20;
    private static final int COOLDOWN = 40000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("FuriaSelvagem-1,26-Esquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("FuriaSelvagem-1,26-Direita.gif"));

    // Construtor da classe.

    public FuriaSelvagem() {
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
        return new ImageIcon(getClass().getResource("E_FuriaSelvagemG.png"));
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
        return (Arrays.asList(new BuffouDebuff(0,0.30f,20000,this.getNome()), new BuffouDebuff(1,0.30f,20000,this.getNome())));
    }
    
    @Override
    public CC getCc(){
        return null;
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 1260);
    }
}
