/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

/**
 *
 * @author andre
 */
public enum Dificuldade {
    //Enums:
    FACIL(193000,2000),
    INTERMEDIARIO(80000,1000),
    DIFICIL(20000,380);
    
    //Atributos:
    private int tempoMaximoJogo;
    private long tempoMovimentoFicha;
    
    //Construtor:
    private Dificuldade(int tempoMaximoJogo, int tempoMovimentoFicha) {
        this.tempoMaximoJogo = tempoMaximoJogo;
        this.tempoMovimentoFicha = tempoMovimentoFicha;
    }
    
    
    //Metodos:
    public int getTempoMaximoJogo() {
        return tempoMaximoJogo;
    }

    public long getTempoMovimentoFicha() {
        return tempoMovimentoFicha;
    }
}
