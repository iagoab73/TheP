package Habilidades.Dragao;

import Habilidades.CC;
import Habilidades.EfeitoEspecial;
import Habilidades.Habilidade;
import Personagens.Personagem;
import Telas.TelaCombate.Tela;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class EfeitoVooDoDragao extends EfeitoEspecial {
    
    private static final String NOME = "Voo do Dragão";
    private static final int DURACAO = 20000;
    private static final int TIPO = 0;
    private Personagem alvo;

    public EfeitoVooDoDragao() {
        super(NOME, DURACAO, TIPO);
    }
    
    @Override
    public void segundo(Personagem usuario, Tela tela){
        super.diminuiduracao();
        if(super.getDuracao() <= 0){
            int cont = 0;
            for(Habilidade i : usuario.getHabilidades()){
                if(i.getNome().equals("Voo Do Dragão") && i.getDisponivel() == 1){
                    tela.adicionahabilidadefila(cont, usuario, alvo);
                }
                cont++;
            }
            this.desabilitaEfeito(usuario);
            usuario.excluiEfeito(this);
        }
    }
   
    @Override
    public void acaoEfeito(Personagem usuario,Personagem alvo){
        this.alvo = alvo;
        for(Habilidade i : usuario.getHabilidades()){
            if(i.getNome().equals("Voo Do Dragão") != true){
                i.setDisponivel(-1);
            }
        }
        usuario.setAtaquebasico(false);
    }
    
    @Override
    public void desabilitaEfeito(Personagem usuario){
        for(CC j : usuario.getCcs()){
            if(j.getTipo() == 0){
                return;
            }
        }
        for(Habilidade i : usuario.getHabilidades()){
            if(i.getCooldown()<= 0 && i.getGastodemana() > usuario.getMana()){
                i.setDisponivel(2);
            }else if(i.getCooldown() <= 0){
                i.setDisponivel(1);
            }else{
                i.setDisponivel(0);
            }
        }
        usuario.setAtaquebasico(true);
    }
}
