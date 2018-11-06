/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
class Inimigo extends Thread{
    //Atributos:
    private int posicaoX;
    private int posicaoY;
    private int numeroInimigo;
    private Random random = new Random(Calendar.getInstance().get(Calendar.MILLISECOND)); //Passar como seed para o numero aleatorio a o tempo em milisegundos atual
    private ControladorJogo controlador;
    private boolean derrotado;
    
    //Construtor:
    public Inimigo(int posicaoX, int posicaoY, int numeroInimigo, ControladorJogo controladorJogo) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.numeroInimigo = numeroInimigo;
        this.controlador = controladorJogo;
        this.derrotado = false;
    }
    
    //Metodos:
    @Override
    public synchronized void run() {
        
        this.controlador.inserirInimigo(this.posicaoX, this.posicaoY, this.numeroInimigo);
        
        while (!this.derrotado) {
            try {
                this.sleep(3000l);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inimigo.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Saber para onde vai se movimentar:
            int movimento = this.random.nextInt(4); //Retorna 0 - drieita, 1 - esquerda, 2 - baixo, 3 - cima
            switch (movimento) { //Precisa ser sincronizado
                case 0: //Andar para a direita
                    if (this.possuiEspacoEmX()) {
                        int posicaoXAntiga = this.posicaoX;
                        this.posicaoX++;
                        this.controlador.atualizarInimigo(posicaoXAntiga, this.posicaoY, this.posicaoX, this.posicaoY); //Precisa ser sincronizado
                    }
                    break;

                case 1: //Andar para a esquerda
                    if (this.possuiEspacoEmX()) {
                        int posicaoXAntiga = this.posicaoX;
                        this.posicaoX--;
                        this.controlador.atualizarInimigo(posicaoXAntiga, this.posicaoY, this.posicaoX, this.posicaoY);
                    }
                    break;

                case 2: //Andar para baixo
                    if (this.possuiEspacoEmY()) {
                        int posicaoYAntiga = this.posicaoY;
                        this.posicaoY++;
                        this.controlador.atualizarInimigo(this.posicaoX, posicaoYAntiga, this.posicaoX, this.posicaoY);
                    }
                    break;

                case 3: //Andar para cima
                    if (this.possuiEspacoEmY()) {
                        int posicaoYAntiga = this.posicaoY;
                        this.posicaoY--;
                        this.controlador.atualizarInimigo(this.posicaoX, posicaoYAntiga, this.posicaoX, this.posicaoY);
                    }
                    break;
            }
        }
        
        //Remover da GUI esse inimigo
        this.controlador.removerInimigo(this.posicaoX,this.posicaoY); //Precisa ser sincornizado
        
    }

    private boolean possuiEspacoEmX() {
        if (this.posicaoX-1 < 0 || this.posicaoX+1 > (ControladorJogo.MAX_POSICOES-1)) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean possuiEspacoEmY() {
        if (this.posicaoY-1 < 0 || this.posicaoY+1 > (ControladorJogo.MAX_POSICOES-1)) {
            return false;
        } else {
            return true;
        }
    }
    
    public synchronized boolean verificarAcertoEmInimigo(int posicaoX, int posicaoY, int numeroInimigo) {
        if (this.numeroInimigo == numeroInimigo && this.posicaoX == posicaoX && this.posicaoY == posicaoY) {
            //this.derrotado = true;
            return true;
        }
        
        return false;
    }
    
    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public int getNumeroInimigo() {
        return numeroInimigo;
    }
    
}
