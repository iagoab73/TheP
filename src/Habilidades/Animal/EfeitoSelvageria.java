package Habilidades.Animal;

import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;

/**
 *
 * @author Administrator
 */
public class EfeitoSelvageria extends EfeitoEspecial {
    
    private static final String NOME = "Selvageria";
    private static final int DURACAO = 8000;
    private static final int TIPO = 0;

    public EfeitoSelvageria() {
        super(NOME, DURACAO, TIPO);
    }
   
    @Override
    public void acaoEfeito(Personagem usuario, Personagem oponente){
        for(Habilidade i : usuario.getHabilidades()){
            i.setDisponivel(-1);
        }
    }
    
    @Override
    public void desabilitaEfeito(Personagem usuario){
        boolean cece = false;
        for(CC c : usuario.getCcs()){
            if(c.getTipo() == 1){
                cece = true;
            }
        }
        if(cece != true){
            for(Habilidade i : usuario.getHabilidades()){
                if(i.getCooldown()<=0){
                    i.setDisponivel(1);
                }else if(i.getGastodemana() > usuario.getMana()){
                    i.setDisponivel(2);
                }else{
                    i.setDisponivel(0);
                }
            }
        }
    }
    
    @Override
    public void mostramsg(Personagem usuario, Tela tela) {
        tela.adicionaTexto(" A " + this.getNome() + " de " + usuario.getClasse() + " acabou!");
    }
}
