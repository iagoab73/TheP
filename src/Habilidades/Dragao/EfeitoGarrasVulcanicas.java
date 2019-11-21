package Habilidades.Dragao;

import Habilidades.EfeitoEspecial;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoGarrasVulcanicas extends EfeitoEspecial {
    
    private static final String NOME = "Afinidade com o Fogo";
    private static final int DURACAO = 15000;
    private static final int TIPO = 5;

    public EfeitoGarrasVulcanicas() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(Personagem atacante, Personagem defesa) {
        int danofisico = atacante.getAgilidade() * atacante.getForcafisica();
        int danomagico = atacante.getForcaMagicaBase() * 200;
        
        defesa.recebeDanoMisto(danofisico, danomagico, atacante);
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}
