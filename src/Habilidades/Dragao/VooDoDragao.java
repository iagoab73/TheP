package Habilidades.Dragao;

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
public class VooDoDragao extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Voo Do Dragão";
    private static final String DESCRICAO = "Se prepara por até 20 segundos para atacar com toda sua força, causando dano mágico com base no tempo de preparação.";
    private static final int GASTODEMANA = 25;
    private static final int COOLDOWN = 65000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("VooDragao(Ativando)1.78.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("VooDragao(Ativando)1.78.gif"));
    private int status = 0;
    // Construtor da classe.

    public VooDoDragao() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }
    
    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        for(EfeitoEspecial i: atacante.getEfeitos()){
            if(i.getNome().equals(this.getEfeitoespecial().getNome())){
                tela.adicionaTexto(" O " + atacante.getClasse() + " atacou com " + this.getNome() + "!");
                super.reset();
                
                int x = (20000 - i.getDuracao())/1000;
                int dano = (int) (200 + (1.5*(Math.pow(x,2.5))) * atacante.getForcamagica());
                i.diminuiduracao(20000);
                
                this.mostraefeito(atacante, tela);
                status = 0;
        
                if(super.verificaBloqueio(atacante, defesa, dano, tela)){
                    return;
                }
                
                defesa.recebeDanoMagico(dano, atacante);

                return;
            }
        }
        this.mostraefeito(atacante, tela);
        status = 1;
        tela.adicionaTexto("O " + atacante.getClasse() + " está preparando um ataque aéreo!");
        super.acao(atacante, defesa);
    }

    @Override
    public ImageIcon getEfeitoEsquerda() {
        if(status == 1){
            return new ImageIcon(getClass().getResource("VooDragao-2,52-.gif"));
        }
        return EFEITOESQUERDA;
    }
    
    @Override
    public ImageIcon getEfeitoDireita(){
        if(status == 1){
            return new ImageIcon(getClass().getResource("VooDragao-2,52-.gif"));
        }
        return EFEITODIREITA;
    }

    @Override
    public int getCooldownBase() {
        return COOLDOWN;
    }
    
    @Override
    public ImageIcon getIcone(){
        return new ImageIcon(getClass().getResource("R_VooDragaoG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return new EfeitoVooDoDragao();
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
        if(status == 0){
            tela.mostraEfeito(atacante, 0, this, 1780);
        }else{
            tela.mostraEfeito(atacante, 1, this, 2520);
        }
    }
}
