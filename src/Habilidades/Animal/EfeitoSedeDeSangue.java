package Habilidades.Animal;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoSedeDeSangue extends EfeitoEspecial {
    
    private static final String NOME = "Sede de Sangue";
    private static final int DURACAO = 10000;
    private static final int TIPO = 9;

    public EfeitoSedeDeSangue() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(int dano,Personagem usuario) {
        usuario.recebeCura((int) (dano*0.40));
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}
