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
public class ControladorJogo {
    //Atributos:
    public static final int MAX_POSICOES = 10;
    private Dificuldade dificuldade;
    private TelaJogo telaJogo;
    private Inimigo[] inimigos;
    
    //Construtor:
    public ControladorJogo() {
        this.telaJogo = new TelaJogo(this);
        this.inimigos = new Inimigo[5];
        
    }
    
    public void iniciarJogo() throws InterruptedException {
        
        for (int i = 0; i < 5; i++) {
            this.inimigos[i] = new Inimigo(i+1,i+2,i,this);
            this.inimigos[i].start();
        }
        
        for (int i = 0; i < 5; i++) {
            this.inimigos[i].join();
        }
        
    }
    
    
    //Metodos:
    public synchronized void atualizarInimigo (int posicaoX, int posicaoY, int novaPosicaoX, int novaPosicaoY) {
        this.telaJogo.atualizarInimigo(posicaoX, posicaoY, novaPosicaoX, novaPosicaoY);
        this.atualizarTela();
    }
    
    public synchronized void inserirInimigo (int posicaoX, int posicaoY, int numeroInimigo) {
        this.telaJogo.inserirInimigo(posicaoX, posicaoY, numeroInimigo);
    }
    
    public synchronized boolean verificarAcerto (int posicaoX, int posicaoY, int numeroInimigo) {
        boolean acertou = this.inimigos[numeroInimigo].verificarAcertoEmInimigo(posicaoX, posicaoY, numeroInimigo);
        
        return acertou;
    }
    
    public synchronized void removerInimigo (int posicaoX, int posicaoY) {
        this.telaJogo.removerInimigo(posicaoX, posicaoY);
    }
    
    private void atualizarTela () {
        this.telaJogo.atualizarTela();
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public Inimigo[] getInimigos() {
        return inimigos;
    }
    
}
