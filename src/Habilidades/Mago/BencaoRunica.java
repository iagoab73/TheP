package Habilidades.Mago;

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
public class BencaoRunica extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Benção Rúnica";
    private static final String DESCRICAO = "Cura o usuário.";
    private static final int GASTODEMANA = 20;
    private static final int COOLDOWN = 25000;
    private static final int MULTIPLICADOR = 5;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("BencaoRunica1.95.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("BencaoRunica1.95.gif"));

    // Construtor da classe.

    public BencaoRunica() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        super.reset();
        this.mostraefeito(atacante, tela);
        atacante.recebeCura(MULTIPLICADOR * atacante.getForcaMagicaBase());
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
        return new ImageIcon(getClass().getResource("W_BencaoRunicaG.png"));
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
        tela.mostraEfeito(atacante, 0, this, 1950);
    }
}