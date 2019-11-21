package Habilidades.Zumbi;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class EfeitoMordidaMortal extends EfeitoEspecial {
    
    private static final String NOME = "Infecção";
    private static final int DURACAO = 10000;
    private static final int TIPO = 7;

    public EfeitoMordidaMortal() {
        super(NOME, DURACAO, TIPO);
    }
    
    @Override
    public void segundo(Personagem usuario, Tela tela){
        this.diminuiduracao();
        usuario.recebeDano((int) (usuario.getVida() * 0.05), null);
        if(super.getDuracao()<= 0){
            this.desabilitaEfeito(usuario);
            usuario.excluiEfeito(this);
        }
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTextoLog(" A " + this.getNome() + " em " + usuario.getClasse() + " acabou!");
    }
}
