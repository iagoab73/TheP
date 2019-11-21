package Habilidades.Guerreiro;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;

/**
 *
 * @author Administrator
 */
public class EfeitoEscudoDaAlma extends EfeitoEspecial {
    
    private static final String NOME = "Proteção da Alma";
    private static final int DURACAO = 60000;
    private static final int TIPO = 2;
    private int escudo = 600;

    public EfeitoEscudoDaAlma() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(int dano,Personagem usuario) {
        escudo = escudo - dano;
        if (escudo <= 0){
            usuario.excluiEfeito(this);
        }
    }
}
