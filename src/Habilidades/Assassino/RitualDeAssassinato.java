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
public class RitualDeAssassinato extends Habilidade {
    
    // Constantes do nome, dano e gasto da magia.
    private static final String NOME = "Ritual De Assassinato";
    private static final String DESCRICAO = "Seus próximos ataques deixam lâminas presas em seu alvo. Reativar essa habilidade dá dano ao alvo com base no número de laminas acumuladas.";
    private static final int GASTODEMANA = 14;
    private static final int COOLDOWN = 60000;
    private static final int MULTIPLICADOR = 0;
    private final ImageIcon EFEITOESQUERDA = new ImageIcon(getClass().getResource("RitualAssasinatAtivo1.92.gif"));
    private final ImageIcon EFEITODIREITA = new ImageIcon(getClass().getResource("RitualAssasinatAtivo1.92.gif"));
    private int status = 0;

    // Construtor da classe.

    public RitualDeAssassinato() {
        super(NOME, DESCRICAO, GASTODEMANA, COOLDOWN, MULTIPLICADOR);
    }

    @Override
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        for(EfeitoEspecial i : defesa.getEfeitos()){
            if(i.getNome().equals(this.getEfeitoespecialinimigo().getNome())){
                super.reset();
                
                int dano = (int) (150 + 25*(Math.pow((double) i.getValorEspecifico(),(double) 2)) * (atacante.getForcamagica()));
                
                i.diminuiduracao(100000);
                
                this.mostraefeito(atacante, tela);
                status = 0;
                
                if(super.verificaBloqueio(atacante, defesa, dano,tela)){
                    return;
                }
                defesa.recebeDanoMagico(dano, atacante);
                return;
            }
        }
        this.mostraefeito(atacante, tela);
        status = 1;
        tela.adicionaTexto("O " + atacante.getClasse() + " está se preparando para assassinar o alvo!");
        super.acao(atacante, defesa);
    }

    @Override
    public ImageIcon getEfeitoEsquerda() {
        if(status == 1){
            return new ImageIcon(getClass().getResource("RitualDeAssasinato0.84.gif"));
        }
        return EFEITOESQUERDA;
    }
    
    @Override
    public ImageIcon getEfeitoDireita(){
        if(status == 1){
            return new ImageIcon(getClass().getResource("RitualDeAssasinato0.84.gif"));
        }
        return EFEITODIREITA;
    }
    
    @Override
    public int getCooldownBase() {
        return COOLDOWN;
    }
    
    @Override
    public ImageIcon getIcone(){
        return new ImageIcon(getClass().getResource("R_RitualAssasinatoG.png"));
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecial(){
        return null;
    }
    
    @Override
    public EfeitoEspecial getEfeitoespecialinimigo(){
        return new EfeitoRitualDeAssassinato();
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
            tela.mostraEfeito(atacante, 0, this, 1920);
        }else{
            tela.mostraEfeito(atacante, 1, this, 840);
        }
    }
}
