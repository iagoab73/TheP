package Habilidades;

import Personagens.Personagem;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class BuffouDebuff {
    
    private final int stat;
    private final float porcentagem;
    private long duração;
    private final String fonte;

    public BuffouDebuff(int stat, float porcentagem, long duração, String fonte) {
        this.stat = stat;
        this.porcentagem = porcentagem;
        this.duração = duração;
        this.fonte = fonte;
    }
    
    public void segundo(Personagem usuario, ArrayList<BuffouDebuff> bd){
        this.duração = this.duração - 1000;
        
        if(this.duração <= 0){
            bd.add(this);
        }
    }

    public int getStat() {
        return stat;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public long getDuração() {
        return duração;
    }

    public String getFonte() {
        return fonte;
    } 
}
