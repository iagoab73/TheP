package Habilidades.Zumbi;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoDefesaDosMortos extends EfeitoEspecial {
    
    private static final String NOME = "Defesa dos Mortos";
    private static final int DURACAO = 10000;
    private static final int TIPO = 3;

    public EfeitoDefesaDosMortos() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(Personagem defesa) {
        defesa.recebeDano(80, null);
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}
