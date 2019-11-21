package Habilidades;

import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator        
 */
public abstract class Habilidade {
    
    //Atributos comuns em todas as magias.
    private final String nome;
    private final String descrição;
    private final int gastodemana;
    private int cooldown;
    private final int multiplicador;
    private int disponivel = 1;
    
    // -1 = desabilitado, 0 = Indisponivel, 1 = disponivel, 2 = disponivel mas sem mana.

    // Construtor da classe, que atribui os respectivos valores com base no tipo de magia determinado na sub-classe.

    public Habilidade(String nome, String descrição, int gastodemana, int cooldown, int multiplicador) {
        this.nome = nome;
        this.descrição = descrição;
        this.gastodemana = gastodemana;
        this.cooldown = 0;
        this.multiplicador = multiplicador;
    }
    
    public void resetaCooldown(){
        this.cooldown = this.getCooldownBase();
    }
    
    public void segundo(Personagem usuario){
        if(this.cooldown > 0){
            this.cooldown = this.cooldown - 1000;
        }
        
        if(this.cooldown <= 0 && this.disponivel != -1 && this.disponivel != 1){
            this.setDisponivel(1);
        }
        
        if(this.gastodemana > usuario.getMana() && this.disponivel == 1){
            this.setDisponivel(2);
        }
    }
    
    public void reset(){
        this.resetaCooldown();
        this.setDisponivel(0);
    }
    
    public boolean verificaBloqueio(Personagem atacante, Personagem defesa, int dano, Tela tela){
        EfeitoEspecial ef = defesa.temEfeitoEspecifico(8);
        
        if(ef != null){
            if(ef.respostaEspecífica()){
                ef.acaoEfeito(dano, atacante, defesa,tela);
                return true;
            }
        }
        return false;
    }
    
    public void acao(Personagem atacante, Personagem defesa){

        
        if(multiplicador != 0){
            defesa.recebeDanoMagico(multiplicador * atacante.getForcamagica(), atacante);
        }
        
        EfeitoEspecial efeitoespecial = this.getEfeitoespecial();
        EfeitoEspecial efeitoespecialinimigo = this.getEfeitoespecialinimigo();
        List<BuffouDebuff> buffs = this.getBuffs();
        List<BuffouDebuff> debuffs = this.getDebuffs();
        CC cc = this.getCc();
        
        if(efeitoespecial != null){
            atacante.adicionaEfeito(efeitoespecial, defesa);
        }
        if(efeitoespecialinimigo != null){
            defesa.adicionaEfeito(efeitoespecialinimigo, atacante);
        }
        if(buffs != null){
            atacante.adicionaBuff(buffs);
        }
        if(debuffs != null){
            defesa.adicionaDebuff(debuffs);
        }
        if(cc != null){
            EfeitoEspecial ef = defesa.temEfeitoEspecifico(1);
            if(ef != null){
                ef.acaoEfeito(cc,atacante,defesa);
            }else{
                defesa.adicionaCC(cc, atacante);
            }
        }
    }
    
    
    public void mostraefeito(Personagem atacante,Tela tela){
        
    }
    
    public void acaoHabilidade(Personagem atacante, Personagem defesa, Tela tela){
        tela.adicionaTexto("O " + atacante.getClasse() + " usou a habilidade " + this.getNome() + "!");
        this.reset();
        
        this.mostraefeito(atacante, tela);
        
        if(this.verificaBloqueio(atacante, defesa, multiplicador * atacante.getForcamagica(), tela)){
            return;
        }
           
        this.acao(atacante, defesa);
    }
    
    // Gets da classe.
    public String getNome() {
        return nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public int getGastodemana() {
        return gastodemana;
    }

    public int getCooldown() {
        return cooldown;
    }
    
    public abstract ImageIcon getIcone();

    public abstract EfeitoEspecial getEfeitoespecial();
    
    public abstract EfeitoEspecial getEfeitoespecialinimigo();

    public abstract List<BuffouDebuff> getBuffs();

    public abstract List<BuffouDebuff> getDebuffs();
    
    public abstract CC getCc();

    public int getMultiplicador() {
        return multiplicador;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }
    
    public abstract int getCooldownBase();
    public abstract ImageIcon getEfeitoEsquerda();
    public abstract ImageIcon getEfeitoDireita();

    
}
