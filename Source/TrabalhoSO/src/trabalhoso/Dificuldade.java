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
    FACIL(300,20),
    INTERMEDIARIO(200,10),
    DIFICIL(100,5);
    
    //Atributos:
    private int tempoMaximoJogo;
    private int tempoMovimentoFicha;
    
    //Construtor:
    private Dificuldade(int tempoMaximoJogo, int tempoMovimentoFicha) {
        this.tempoMaximoJogo = tempoMaximoJogo;
        this.tempoMovimentoFicha = tempoMovimentoFicha;
    }
    
    
    //Metodos:
    public int getTempoMaximoJogo() {
        return tempoMaximoJogo;
    }

    public int getTempoMovimentoFicha() {
        return tempoMovimentoFicha;
    }
}
