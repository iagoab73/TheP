package Habilidades.Paladino;

import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoModoDeus extends EfeitoEspecial {
    
    private static final String NOME = "Ascenção";
    private static final int DURACAO = 10000;
    private static final int TIPO = 1;

    public EfeitoModoDeus() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(CC cc,Personagem atacante,Personagem usuario) {
        
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}
