package Habilidades.Assassino;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class EfeitoLaminaEnvenenadaInimigo extends EfeitoEspecial {
    
    private static final String NOME = "Veneno Parasita";
    private static final int DURACAO = 5000;
    private static final int TIPO = 4;

    public EfeitoLaminaEnvenenadaInimigo() {
        super(NOME, DURACAO, TIPO);
    }
    
    @Override
    public void segundo(Personagem usuario, Tela tela){
        this.diminuiduracao();
        
        usuario.recebeDano((int) (usuario.getVidaBase() * 0.02), null);
        
        if(super.getDuracao()<= 0){
            this.desabilitaEfeito(usuario);
            usuario.excluiEfeito(this);
        }
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" O " + this.getNome() + " em " + usuario.getClasse() + " acabou!");
    }
}