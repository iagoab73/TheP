package Habilidades.Zumbi;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoPactoComAMorte extends EfeitoEspecial {
    
    private static final String NOME = "Imortalidade";
    private static final int DURACAO = 20000;
    private static final int TIPO = 6;

    public EfeitoPactoComAMorte() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(Personagem usuario) {
        usuario.recebeCura((int) (usuario.getVidaBase()*0.50));
        usuario.excluiEfeito(this);
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}
