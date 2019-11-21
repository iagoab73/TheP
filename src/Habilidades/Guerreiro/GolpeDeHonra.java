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
public class GolpeDeHonra extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Golpe De Honra";
    private static final String DESCRICAO = "Dá um ataque final, que causa mais dano quanto menos vida o alvo tem.";
    private static final int GASTODEMANA = 40;
    private static final int COOLDOWN = 70000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("GolpeHonra2.33.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("GolpeHonra2.33.gif"));

    // Construtor da classe.

    public GolpeDeHonra() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
        
        int dano = (int) (atacante.getForcamagica() * ((defesa.getVidaBase()/ defesa.getVida()) * 1000));
        
        this.mostraefeito(atacante, tela);
        
        if(this.verificaBloqueio(atacante, defesa, dano, tela)){
            return;
        }
                
        defesa.recebeDanoMagico(dano, atacante);
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
        return new ImageIcon(getClass().getResource("R_GolpeHonraG.png"));
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
        tela.mostraEfeito(atacante, 1, this, 2330);
    }
}
