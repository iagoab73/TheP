package Habilidades.Assassino;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;

/**
 *
 * @author Administrator
 */
public class EfeitoRitualDeAssassinato extends EfeitoEspecial {
    
    private static final String NOME = "Preparação de Assassinato";
    private static final int DURACAO = 100000;
    private static final int TIPO = 3;
    private int laminas = 0;

    public EfeitoRitualDeAssassinato() {
        super(NOME, DURACAO, TIPO);
    }
   
    public int getValorEspecifico(){
        return laminas;
    }
    
    @Override
    public void acaoEfeito(Personagem defesa) {
        this.laminas++;
    }
}
