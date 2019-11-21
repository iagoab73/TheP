package Habilidades.Assassino;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class EfeitoAgilidadeSombria extends EfeitoEspecial {
    
    private static final String NOME = "Agilidade Sombria";
    private static final int DURACAO = 100000;
    private static final int TIPO = 8;
    private int nivel = 1;

    public EfeitoAgilidadeSombria() {
        super(NOME, DURACAO, TIPO);
    }
    
    
    @Override
    public boolean respostaEspecífica(){
        Random rand = new Random();
        if(rand.nextInt(100) < (nivel*34)){
            return true;
        }
        nivel ++;
        return false;
    }
   
    @Override
    public void acaoEfeito(int dano, Personagem defesa, Personagem atacante, Tela tela){
        tela.adicionaTexto(" O " + atacante.getClasse() + " desviou do ataque com " + this.getNome() + "!");
        atacante.ataqueBásico(defesa);
        super.diminuiduracao(100000);
    }
}
