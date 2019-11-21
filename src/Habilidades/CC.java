/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

import Personagens.Personagem;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class CC {
    private final int tipo;
    private long duração;

    public CC(int tipo, long duração) {
        this.tipo = tipo;
        this.duração = duração;
    }
    
    public void segundo(Personagem usuario, ArrayList<CC> c){
        this.duração = this.duração - 1000;
        
        if(this.duração <= 0){
            c.add(this);
        }
    }

    public int getTipo() {
        return tipo;
    }

    public long getDuração() {
        return duração;
    }
}
