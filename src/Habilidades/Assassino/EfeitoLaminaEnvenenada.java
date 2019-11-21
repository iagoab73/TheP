package Habilidades.Assassino;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class EfeitoLaminaEnvenenada extends EfeitoEspecial {
    
    private static final String NOME = "Sede venenosa";
    private static final int DURACAO = 5000;
    private static final int TIPO = 4;

    public EfeitoLaminaEnvenenada() {
        super(NOME, DURACAO, TIPO);
    }
    
    @Override
    public void segundo(Personagem usuario, Tela tela){
        this.diminuiduracao();
        
        usuario.recebeCura((int) (usuario.getVidaBase() * 0.02));
        
        if(super.getDuracao()<= 0){
            this.desabilitaEfeito(usuario);
            usuario.excluiEfeito(this);
        }
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}