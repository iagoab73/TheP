package Habilidades;

import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public abstract class EfeitoEspecial {
    private final String nome;
    private int duracao;
    private final int tipo;
            
    public EfeitoEspecial(String nome, int duracao, int tipo) {
        this.nome = nome;
        this.duracao = duracao;
        this.tipo = tipo;
    }
    
    public void diminuiduracao(){
        this.duracao = duracao - 1000;
    }
    
    public void diminuiduracao(int quant){
        this.duracao = duracao - quant;
    }

    public void segundo(Personagem usuario, Tela tela){
        this.diminuiduracao();
        if(this.duracao <= 0){
            this.mostramsg(usuario, tela);
            this.desabilitaEfeito(usuario);
            usuario.excluiEfeito(this);
        }
    }
 
    
    public void desabilitaEfeito(Personagem usuario){
        
    }
    
    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean respostaEspecífica(){
        return true;
    }
    
    public int getValorEspecifico(){
        return 1;
    }
    
    public void acaoEfeito(Personagem usuario){
        
    }
    
    public void acaoEfeito(int dano, Personagem atacante, Personagem defesa, Tela tela){
        
    }

    public void acaoEfeito(CC cc, Personagem atacante, Personagem defesa){
        
    }

    public void acaoEfeito(int uso, Personagem usuario){
        
    }
    
    public void acaoEfeito(Personagem atacante, Personagem defesa){
        
    }

    public void mostramsg(Personagem usuario, Tela tela) {
        
    }
}
