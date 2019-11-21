package Habilidades.Paladino;

import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoProtecao extends EfeitoEspecial {
    
    private static final String NOME = "Proteção";
    private static final int DURACAO = 30000;
    private static final int TIPO = 8;

    public EfeitoProtecao() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(int dano, Personagem defesa, Personagem atacante, Tela tela) {
        tela.adicionaTexto(" O " + atacante.getClasse() + " defendeu-se do ataque com a " + this.getNome() + "!");
        tela.adicionaTexto(" O " + atacante.getClasse() + " usou um contra ataque!");
        dano = (int) (dano * (0.70));
        for(Habilidade i : atacante.getHabilidades()){
            if(i.getNome().equals("Proteção")){
                tela.mostraEfeito(atacante, 1, i, 1440);
            }
        }
        defesa.recebeDanoMagico(dano, atacante);
        super.diminuiduracao(30000);
    }
}
