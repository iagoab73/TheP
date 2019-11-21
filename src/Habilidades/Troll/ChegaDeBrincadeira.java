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
public class ChegaDeBrincadeira extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Chega De Brincadeira";
    private static final String DESCRICAO = "Deixa as trollagens de lado, usando um ataque peculiar que causa dano mágico e atordoa o alvo.";
    private static final int GASTODEMANA = 60;
    private static final int COOLDOWN = 70000;
    private static final int MULTIPLICADOR = 1000;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("ChegaBrincadeira2.86.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("ChegaBrincadeira2.86.gif"));


    // Construtor da classe.

    public ChegaDeBrincadeira() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
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
        return new ImageIcon(getClass().getResource("R_ChegaBrincadeiraG.png"));
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
        return new CC(0,10000);
    }
    
    @Override
    public void mostraefeito(Personagem atacante, Tela tela) {
        tela.mostraEfeito(atacante, 1, this, 2860);
    }
}

