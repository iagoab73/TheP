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
public class LaminaEnvenenada extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Lâmina Envenenada";
    private static final String DESCRICAO = "Atinge o alvo com uma lamina envenenada, que dá dano por segundo ao alvo e cura o usuário.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 12000;
    private static final int MULTIPLICADOR = 80;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("LaminaPoison1.14-Esquerda.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("LaminaPoison1.14-Direita.gif"));

    // Construtor da classe.

    public LaminaEnvenenada() {
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
        return new ImageIcon(getClass().getResource("E_LaminaEnvenenadaG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoLaminaEnvenenada();
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecialinimigo(){
        return new EfeitoLaminaEnvenenadaInimigo();
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
        tela.mostraEfeito(atacante, 1, this, 1140);
    }
}
