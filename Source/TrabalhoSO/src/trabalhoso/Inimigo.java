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
    private long tempoMovimento;
    private Random random = new Random(Calendar.getInstance().get(Calendar.MILLISECOND)); //Passar como seed para o numero aleatorio a o tempo em milisegundos atual
    private ControladorJogo controlador;
    private boolean derrotado;
    
    //Construtor:
    public Inimigo(int posicaoX, int posicaoY, int numeroInimigo, long tempoMovimento, ControladorJogo controladorJogo) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.numeroInimigo = numeroInimigo;
        this.tempoMovimento = tempoMovimento;
        this.controlador = controladorJogo;
        this.derrotado = false;
    }
    
    //Metodos:
    @Override
    public void run() {
        //Mostrar o inimigo na tela
        this.controlador.inserirInimigo(this.posicaoX, this.posicaoY, this.numeroInimigo);
        //Ficar rodando:
        while (!this.derrotado) {
            try {
                this.sleep(this.tempoMovimento);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inimigo.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Saber para onde vai se movimentar:
            int movimento = this.random.nextInt(4); //Retorna 0 - drieita, 1 - esquerda, 2 - baixo, 3 - cima
            
            try {
                this.verificarMovimento(movimento);
            } catch (NullPointerException ex) {            
                this.interrupt();
                this.derrotado = true;
                break;
            }
            
        }
        
    }
    
    private synchronized void verificarMovimento(int movimento) {
        if (!this.derrotado) {
            switch (movimento) { //Precisa ser sincronizado
                case 0: //Andar para a direita
                    if (this.possuiEspacoDireita()) {
                        this.andarDireita();
                    } else {
                        this.andarEsquerda();
                    }
                    break;

                case 1: //Andar para a esquerda
                    if (this.possuiEspacoEsquerda()) {
                        this.andarEsquerda();
                    } else {
                        this.andarDireita();
                    }
                    break;

                case 2: //Andar para baixo
                    if (this.possuiEspacoBaixo()) {
                        this.andarBaixo();
                    } else {
                        this.andarCima();
                    }
                    break;

                case 3: //Andar para cima
                    if (this.possuiEspacoCima()) {
                        this.andarCima();
                    } else {
                        this.andarBaixo();
                    }
                    break;
            }
        }
    }

    private void andarCima() {
        int posicaoYAntiga = this.posicaoY;
        this.posicaoY--;
        this.controlador.atualizarInimigo(this.posicaoX, posicaoYAntiga, this.posicaoX, this.posicaoY, this.numeroInimigo);
    }

    private void andarBaixo() {
        int posicaoYAntiga = this.posicaoY;
        this.posicaoY++;
        this.controlador.atualizarInimigo(this.posicaoX, posicaoYAntiga, this.posicaoX, this.posicaoY, this.numeroInimigo);
    }

    private void andarEsquerda() {
        int posicaoXAntiga = this.posicaoX;
        this.posicaoX--;
        this.controlador.atualizarInimigo(posicaoXAntiga, this.posicaoY, this.posicaoX, this.posicaoY, this.numeroInimigo);
    }

    private void andarDireita() {
        int posicaoXAntiga = this.posicaoX;
        this.posicaoX++;
        this.controlador.atualizarInimigo(posicaoXAntiga, this.posicaoY, this.posicaoX, this.posicaoY, this.numeroInimigo); //Precisa ser sincronizado
    }

    private boolean possuiEspacoDireita() {
        if (this.posicaoX+1 > (ControladorJogo.MAX_POSICOES-1)) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean possuiEspacoEsquerda() {
        if (this.posicaoX-1 < 0) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean possuiEspacoCima() {
        if (this.posicaoY-1 < 0) {
            return false;
        } else {
            return true;
        }
    }
    
    private boolean possuiEspacoBaixo() {
        if (this.posicaoY+1 > (ControladorJogo.MAX_POSICOES-1)) {
            return false;
        } else {
            return true;
        }
    }
    
    public synchronized boolean verificarAcertoEmInimigo(int posicaoX, int posicaoY, int numeroInimigo) {
        if (this.numeroInimigo == numeroInimigo && this.posicaoX == posicaoX && this.posicaoY == posicaoY) {
            //Remover da GUI esse inimigo
            this.controlador.removerInimigo(this.posicaoX,this.posicaoY); //Precisa ser sincornizado
            this.derrotado = true;
            this.controlador.registrarPonto();
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
