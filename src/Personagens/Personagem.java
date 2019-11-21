package Personagens;
import Telas.TelaCombate.Tela;
import Habilidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author 115111168
 */

// Classe personagem, mãe de todas as classes de personagens do jogo.
public abstract class Personagem {
    
    // Atributos comuns de todos os personagens.
    private final String classe;
    private int vida;
    private int mana;
    private int forcafisica;
    private int forcamagica;
    private int resistenciafisica;
    private int resistenciamagica;
    private int agilidade;
    private final List<Habilidade> habilidades;
    private final ArrayList<BuffouDebuff> buffs = new ArrayList<>();
    private final ArrayList<BuffouDebuff> debuffs = new ArrayList<>();
    private final ArrayList<CC> ccs = new ArrayList<>();
    private final ArrayList<EfeitoEspecial> efeitos = new ArrayList<>();
    private boolean ataquebasico = true;
    private final ArrayList<EfeitoEspecial> efeitospexcluir = new ArrayList<>();
    private Tela tela = null;
    

    // Construtor da classe, que atribui os respectivos valores com base no tipo de personagem determinado nas sub-classes.
    public Personagem(String classe, int vida, int mana, int forcafisica, int forcamagica, int resistenciafisica,
            int resistenciamagica, int agilidade, List<Habilidade> habilidades) {
        this.classe = classe;
        this.vida = vida;
        this.mana = mana;
        this.forcafisica = forcafisica;
        this.forcamagica = forcamagica;
        this.resistenciafisica = resistenciafisica;
        this.resistenciamagica = resistenciamagica;
        this.agilidade = agilidade;
        this.habilidades = habilidades;
    }
    
    // Funcões abstratas para recuperar os valores de vida e mana base, além das pocentagens do especial.
    
    public abstract ImageIcon getEfeitoEsquerda();
    
    public abstract ImageIcon getEfeitoDireita();
    
    public abstract ImageIcon getEfeitoAtaqueBasicoEsquerda();
    
    public abstract ImageIcon getEfeitoAtaqueBasicoDireita();
    
    public abstract ImageIcon getImagemNomeGrande();
    
    public abstract ImageIcon getCenario();
    
    public abstract int getVidaBase();
    
    public abstract int getManaBase();
    
    public abstract int getForcaFisicaBase();
    
    public abstract int getForcaMagicaBase();
    
    public abstract int getResistenciaFisicaBase();
    
    public abstract int getResistenciaMagicaBase();
    
    public abstract int getAgilidadeBase();

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }
    
    
    public void segundo(){
        for(EfeitoEspecial i : this.efeitospexcluir){
            
            this.efeitos.remove(i);
        }
        
        this.efeitospexcluir.clear();
        
        
        this.regeneraMana();
        
        ArrayList<BuffouDebuff> b = new ArrayList<>(); 
        
        ArrayList<BuffouDebuff> d = new ArrayList<>(); 
        
        ArrayList<CC> c = new ArrayList<>(); 
        
        for(CC i : this.getCcs()){
            i.segundo(this,c);
        }
        
        for(CC i : c){
            this.ccs.remove(i);
            switch(i.getTipo()){
                case 0:
                    for(Habilidade j : this.habilidades){
                        if(j.getCooldown()<= 0 && j.getGastodemana() > this.getMana()){
                            j.setDisponivel(2);
                        }else if(j.getCooldown()<=0){
                            j.setDisponivel(1);
                        }else{
                            j.setDisponivel(0);
                        }
                    }
                    this.setAtaquebasico(true);
                    this.agilidade = this.getAgilidadeBase();
                    this.reaplicaBuffseDebuffs();
                    tela.adicionaTexto(" O " + this.classe + " se livrou do atordoamento!");
                    break;
                case 1:
                    for(Habilidade j : this.habilidades){
                        boolean selv = false;
                        for(EfeitoEspecial e : this.efeitos){
                            if(e.getNome().equals("Selvageria")){
                                selv = true;
                            }
                        }
                        if(selv != true){
                            if(j.getCooldown()<= 0 && j.getGastodemana() > this.getMana()){
                                j.setDisponivel(2);
                            }else if(j.getCooldown()<=0){
                                j.setDisponivel(1);
                            }else{
                                j.setDisponivel(0);
                            }
                        }
                    }
                    tela.adicionaTexto(" O " + this.classe + " se livrou do silenciar!");
                    break;
                case 2:
                    tela.adicionaTexto(" O " + this.classe + " se livrou da paralisia!");
                    break;
                case 3:
                    tela.adicionaTexto(" O " + this.classe + " se livrou da confusão!");
                    break;
            }
        }
        
        for(BuffouDebuff i : this.buffs){
            i.segundo(this,b);
        }
        for(BuffouDebuff i : this.debuffs){
            i.segundo(this,d);
        }
        
        for(BuffouDebuff i : b){
            this.buffs.remove(i);
            String stat;
            switch(i.getStat()){
                case 0:
                    stat = "Força Física";
                    break;
                case 1:
                    stat = "Força Mágica";
                    break;
                case 2:
                    stat = "Resistência Física";
                    break;
                case 3:
                    stat = "Resistência Mágica";
                    break;
                default:
                    stat = "Agilidade";
                    break;
            }
            tela.adicionaTexto(" O Buff em " + stat + " em " + this.classe + " acabou!");
        }
        
        for(BuffouDebuff i : d){
            this.debuffs.remove(i);
            String stat;
            switch(i.getStat()){
                case 0:
                    stat = "Força Física";
                    break;
                case 1:
                    stat = "Força Mágica";
                    break;
                case 2:
                    stat = "Resistência Física";
                    break;
                case 3:
                    stat = "Resistência Mágica";
                    break;
                default:
                    stat = "Agilidade";
                    break;
            }
            tela.adicionaTexto(" O Debuff em " + stat + " em " + this.classe + " acabou!");
        }
        
        this.reaplicaBuffseDebuffs();
        
        
        for(Habilidade i : this.habilidades){
            i.segundo(this);
        }
        
        for(EfeitoEspecial i : this.efeitos){
            i.segundo(this,tela);
        }
        
        /*for(EfeitoEspecial i : this.efeitospexcluir){
            this.efeitos.remove(i);
        }
        
        this.efeitospexcluir.clear();*/
    }
    
    public void excluiEfeito(EfeitoEspecial e){
        this.efeitospexcluir.add(e);
    }

    public boolean isAtaquebasico() {
        return ataquebasico;
    }

    public void setAtaquebasico(boolean ataquebasico) {
        this.ataquebasico = ataquebasico;
    }
    
    public void usaHabilidade(int id, Personagem defesa){
        for(CC i : this.ccs){
            switch(i.getTipo()){
                case 2:
                    Random rng = new Random();
                    if(rng.nextInt(100) < 20){
                        tela.adicionaTexto("O " + this.classe + " está paralizado!");
                        return;
                    }
                    break;
                case 3:
                    Random rand = new Random();
                    if(rand.nextInt(100) < 15){
                        tela.adicionaTexto("O " + this.classe + " está confuso!");
                        tela.adicionaTexto(" O " + this.classe + " atacou a si mesmo!");
                        this.recebeDano(100,null);
                        return;
                    }
                    break;
            }
        }
        
        this.mana = this.mana - this.habilidades.get(id).getGastodemana();
        this.habilidades.get(id).acaoHabilidade(this, defesa, tela);
    }
    
    public void recebeCura(int cura){
        if(this.vida == this.getVidaBase()){
                tela.adicionaTexto(" O " + this.classe + " já tem o maximo de pontos de vida!");

        }else if(this.vida + cura > this.getVidaBase()){
            cura = this.getVidaBase() - this.vida;
            this.vida = this.getVidaBase();
            if(tela.isTempocorre()){
                tela.adicionaTexto(" O " + this.classe + " recuperou " + cura + " pontos de vida!");
                tela.atualizaVidaMana();
            }else{
                tela.atualizaVidaCura(this);
                tela.adicionaTexto(" O " + this.classe + " recuperou " + cura + " pontos de vida!");
            }
        }else{
            this.vida = this.vida + cura;
            if(tela.isTempocorre()){
                tela.adicionaTexto(" O " + this.classe + " recuperou " + cura + " pontos de vida!");
                tela.atualizaVidaMana();
            }else{
                tela.atualizaVidaCura(this);
                tela.adicionaTexto(" O " + this.classe + " recuperou " + cura + " pontos de vida!");
            }
        }
    }
    
    public EfeitoEspecial temEfeitoEspecifico(int tipo){
        if(this.efeitos != null){
            for(EfeitoEspecial i : this.efeitos){
                if(i.getTipo() == tipo){
                    return i;
                }
            }
        }
        return null;
    }
    
    public void ataqueBásico(Personagem defesa){
        for(CC i : this.ccs){
            switch(i.getTipo()){
                case 2:
                    Random rng = new Random();
                    if(rng.nextInt(100) < 20){
                        tela.adicionaTexto("O " + this.classe + " está paralizado!");
                        return;
                    }
                    break;
                case 3:
                    Random rand = new Random();
                    if(rand.nextInt(100) < 15){
                        tela.adicionaTexto("O " + this.classe + " foi afetado pela confusão!");
                        tela.adicionaTexto(" O " + this.classe + " atacou a si mesmo!");
                        this.recebeDano(100,null);
                        return;
                    }
                    break;
            }
        }
        
        tela.adicionaTexto("O " + this.classe + " atacou!");
        
        EfeitoEspecial ef = this.temEfeitoEspecifico(5);
        if(ef != null){
            this.mostraefeitoab(this,tela);
            EfeitoEspecial ef1 = defesa.temEfeitoEspecifico(8);
            if(ef1 != null){
                if(ef1.respostaEspecífica()){
                    ef1.acaoEfeito(this.forcafisica * this.agilidade, this, defesa, tela);
                    return;
                }
            }
            if(ef.respostaEspecífica()){
                ef.acaoEfeito(this, defesa);
                return;
            }
        }
        
        this.mostraefeitoab(this,tela);
        
        ef = defesa.temEfeitoEspecifico(8);
        if(ef != null){
            if(ef.respostaEspecífica()){
                ef.acaoEfeito(this.forcafisica * this.agilidade, this, defesa, tela);
                return;
            }
        }
        
        defesa.recebeDanoFisico((int) (this.forcafisica*(1.5)* this.agilidade), this);
    }
    
    public void mostraefeitoab(Personagem aThis, Tela tela){
        
    }
    
    // Método que regenera a mana do personagem, que é chama todo turno em que o personagem não usa uma magia.
    
    public void regeneraMana(){
        if(mana == this.getManaBase()){
            // Se a mana já estiver cheia, sai do método.
            return;
        }
        if((mana + 2) > this.getManaBase()){
            mana = this.getManaBase();
        }else{
            mana = mana + 2;
        }
    }
    
    public void adicionaEfeito(EfeitoEspecial efeitoespecial, Personagem oponente){
        this.efeitos.add(efeitoespecial);
        EfeitoEspecial ef = this.temEfeitoEspecifico(0);
        if(ef != null){
            ef.acaoEfeito(this,oponente);
        }
    }
    
    public void reaplicaBuffseDebuffs(){
        this.forcafisica = this.getForcaFisicaBase();
        this.forcamagica = this.getForcaMagicaBase();
        this.resistenciafisica = this.getResistenciaFisicaBase();
        this.resistenciamagica = this.getResistenciaMagicaBase();
        if(this.getAgilidade() != 0){
            this.agilidade = this.getAgilidadeBase();
        }
        
            
        for(BuffouDebuff i : buffs){
            switch(i.getStat()){
                case 0:
                    this.forcafisica = (this.forcafisica + (int) (this.forcafisica * i.getPorcentagem()));
                    break;
                case 1:
                    this.forcamagica = (this.forcamagica + (int) (this.forcamagica * i.getPorcentagem()));
                    break;
                case 2:
                    this.resistenciafisica = (this.resistenciafisica + (int) (this.resistenciafisica * i.getPorcentagem()));
                    break;
                case 3:
                    this.resistenciamagica = (this.resistenciamagica + (int) (this.resistenciamagica * i.getPorcentagem()));
                    break;
                case 4:
                    this.agilidade = (this.agilidade + (int) (this.agilidade * i.getPorcentagem()));
                    break;
            }
        }
        for(BuffouDebuff i : debuffs){
            switch(i.getStat()){
                case 0:
                    this.forcafisica = (this.forcafisica - (int) (this.forcafisica * i.getPorcentagem()));
                    break;
                case 1:
                    this.forcamagica = (this.forcamagica - (int) (this.forcamagica * i.getPorcentagem()));
                    break;
                case 2:
                    this.resistenciafisica = (this.resistenciafisica - (int) (this.resistenciafisica * i.getPorcentagem()));
                    break;
                case 3:
                    this.resistenciamagica = (this.resistenciamagica - (int) (this.resistenciamagica * i.getPorcentagem()));
                    break;
                case 4:
                    this.agilidade = (this.agilidade - (int) (this.agilidade * i.getPorcentagem()));
                    break;
            }
        }
    }

    public void adicionaBuff(List<BuffouDebuff> buffs) {
        for(BuffouDebuff i : buffs){
            this.buffs.add(i);
            String stat;
            switch(i.getStat()){
                case 0:
                    stat = "Força Física";
                    this.forcafisica = (this.forcafisica + (int) (this.forcafisica * i.getPorcentagem()));
                    break;
                case 1:
                    stat = "Força Mágica";
                    this.forcamagica = (this.forcamagica + (int) (this.forcamagica * i.getPorcentagem()));
                    break;
                case 2:
                    stat = "Resistência Física";
                    this.resistenciafisica = (this.resistenciafisica + (int) (this.resistenciafisica * i.getPorcentagem()));
                    break;
                case 3:
                    stat = "Resistência Mágica";
                    this.resistenciamagica = (this.resistenciamagica + (int) (this.resistenciamagica * i.getPorcentagem()));
                    break;
                default:
                    stat = "Agilidade";
                    this.agilidade = (this.agilidade + (int) (this.agilidade * i.getPorcentagem()));
                    break;
            }
            tela.adicionaTexto(" O " + this.classe + " recebeu um Buff em " + stat + "!");
        }
    }

    public void adicionaDebuff(List<BuffouDebuff> debuffs) {
        for(BuffouDebuff i : debuffs){
            this.debuffs.add(i);
            String stat;
            switch(i.getStat()){
                case 0:
                    stat = "Força Física";
                    this.forcafisica = (this.forcafisica - (int) (this.forcafisica * i.getPorcentagem()));
                    break;
                case 1:
                    stat = "Força Mágica";
                    this.forcamagica = (this.forcamagica - (int) (this.forcamagica * i.getPorcentagem()));
                    break;
                case 2:
                    stat = "Resistência Física";
                    this.resistenciafisica = (this.resistenciafisica - (int) (this.resistenciafisica * i.getPorcentagem()));
                    break;
                case 3:
                    stat = "Resistência Mágica";
                    this.resistenciamagica = (this.resistenciamagica - (int) (this.resistenciamagica * i.getPorcentagem()));
                    break;
                default:
                    stat = "Agilidade";
                    this.agilidade = (this.agilidade - (int) (this.agilidade * i.getPorcentagem()));
                    break;
            }
            tela.adicionaTexto(" O " + this.classe + " recebeu um Debuff em " + stat + "!");
        }
    }

    public void adicionaCC(CC cc, Personagem atacante) {
        this.ccs.add(cc);
        
        switch(cc.getTipo()){
            case 0:
                for(Habilidade i : this.habilidades){
                    i.setDisponivel(-1);
                }
                this.setAtaquebasico(false);
                this.agilidade = 0;
                tela.adicionaTexto(" O " + this.classe + " foi atordoado!");
                break;
            case 1:
                for(Habilidade i : this.habilidades){
                    i.setDisponivel(-1);
                }
                tela.adicionaTexto(" O " + this.classe + " foi silenciado!");
                break;
            case 2:
                tela.adicionaTexto(" O " + this.classe + " foi paralizado!");
                break;
            case 3:
                tela.adicionaTexto(" O " + this.classe + " está confuso!");
                break;
                
        }
        
        if(this.efeitos.isEmpty() != true){
            for(EfeitoEspecial i : this.efeitos){
                if(i.getNome().equals("Voo do Dragão")){
                    tela.adicionaTexto(" A preparação de " + i.getNome() + " de " + this.getClasse() + " foi interrompida!");
                    int cont = 0;
                    for(Habilidade j : this.getHabilidades()){
                        if(j.getNome().equals("Voo Do Dragão")){
                                tela.adicionahabilidadefila(cont, this, atacante);
                        }
                        cont++;
                    }
                }
            }
        }
    }
    
    public void recebeDanoMagico(int danobase, Personagem atacante){
        int dano = danobase/this.resistenciamagica;
        EfeitoEspecial ef = this.temEfeitoEspecifico(3);
        if(ef != null){
            ef.acaoEfeito(atacante);
        }
        this.recebeDano(dano, atacante);
    }
    
    public void recebeDanoMisto(int danofisico, int danomagico, Personagem atacante) {
        int dano = (danofisico/this.resistenciafisica) + (danomagico/this.resistenciamagica);

        EfeitoEspecial ef = this.temEfeitoEspecifico(3);
        if(ef != null){
            ef.acaoEfeito(atacante);
        }
        this.recebeDano(dano, atacante);
    }

    
    public void recebeDanoFisico(int danobase, Personagem atacante) {
        int dano = danobase/this.resistenciafisica;
        EfeitoEspecial ef = this.temEfeitoEspecifico(3);
        if(ef != null){
            ef.acaoEfeito(atacante);
        }
        this.recebeDano(dano, atacante);
    }
    
    public void recebeDano(int dano, Personagem atacante){
        EfeitoEspecial ef = this.temEfeitoEspecifico(2);
        if(ef != null){
            tela.adicionaTexto(" O " + this.getClasse() + " se defendeu com " + ef.getNome() + "!");
            ef.acaoEfeito(dano,this);
        }else{
            int vidaantes = this.vida;
            if(vida - dano <= 0){
                this.vida = 0;
                dano = vidaantes;
            }else{
                this.vida = vida - dano;
            }
            if(tela.isTempocorre()){
                tela.atualizaVidaMana();
                tela.adicionaTexto(" O " + this.getClasse() + " recebeu " + dano + " de dano!");
            }else{
                tela.atualizaVidaDano(this);
                tela.adicionaTexto(" O " + this.getClasse() + " recebeu " + dano + " de dano!");
            }
            if(vida <= 0){
                this.vida = 0;
                ef = this.temEfeitoEspecifico(6);
                if(ef != null){
                    ef.acaoEfeito(this);
                }else{
                    tela.personagemMorte(this);
                }
            }
            if(atacante != null){
                ef = atacante.temEfeitoEspecifico(9);
                if(ef != null){
                    ef.acaoEfeito(dano, atacante);
                }
            }
        }
    }

    // Gets da classe.
    
    public String getClasse() {
        return classe;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    public int getForcafisica() {
        return forcafisica;
    }

    public int getForcamagica() {
        return forcamagica;
    }

    public int getResistenciafisica() {
        return resistenciafisica;
    }

    public int getResistenciamagica() {
        return resistenciamagica;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public List<BuffouDebuff> getBuffs() {
        return buffs;
    }

    public List<BuffouDebuff> getDebuffs() {
        return debuffs;
    }

    public List<CC> getCcs() {
        return ccs;
    }

    public List<EfeitoEspecial> getEfeitos() {
        return efeitos;
    }

}