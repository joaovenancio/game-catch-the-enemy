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
public class ControladorJogo extends Thread {
    //Atributos:
    public static final int MAX_POSICOES = 10;
    private Dificuldade dificuldade;
    private TelaJogo telaJogo;
    private Inimigo[] inimigos;
    
    //Construtor:
    
    
    //Metodos:
    public void atualizarInimigo () {
        synchronized (inimigos) {
            
        }
    }
    
    public boolean verificarAcerto (int posicaoX, int posicaoY) {
        synchronized (inimigos) {
            
        }
        return true;
    }
    
    private synchronized void atualizarTela () {
        synchronized (inimigos) {
            
        }
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public Inimigo[] getInimigos() {
        return inimigos;
    }
    
}
