/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

import trabalhoso.views.TelaJogo;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import trabalhoso.views.TelaInicial;

/**
 *
 * @author andre
 */
public class ControladorJogo {
    //Atributos:
    public static final int MAX_POSICOES = 10;
    private Dificuldade dificuldade;
    private TelaInicial telaInicial;
    private TelaJogo telaJogo;
    private Inimigo[] inimigos;
    final JFXPanel fxPanel = new JFXPanel();
    private boolean terminou;
    
    //Construtor:
    public ControladorJogo() {
        this.telaInicial = new TelaInicial(this);
        this.telaJogo = new TelaJogo(this);
        this.inimigos = new Inimigo[5];
        this.terminou = false;
        
        //Instanciar logo a musica para jah ficar armazenado na memoria
        String musica = "Laser.wav";
        Media arquivoMusica = new Media(new File(musica).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(arquivoMusica);
        mediaPlayer.play();
        
    }
    
    public void iniciarJogo() {
        
        for (int i = 0; i < 5; i++) {
            this.inimigos[i] = new Inimigo(i+1,i+2,i,this);
            this.inimigos[i].start();
            
        }
       
        //Musica no jogo:
        //Eh necessario fazer em uma thread separada porque o player precisa ficar tocando
        String musica = "Negas.mp3";
        Media arquivoMusica = new Media(new File(musica).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(arquivoMusica);
        Thread threadMusica = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!terminou) {
                    mediaPlayer.play();
                }
            }
        });
        threadMusica.start();
        
        for (int i = 0; i < 5; i++) {
            try {
                this.inimigos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        terminou = true;
        System.out.println("VENCEU!");
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
        String musica = "Laser.wav";
        Media arquivoMusica = new Media(new File(musica).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(arquivoMusica);
        mediaPlayer.play();
        
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

    void mostrarTelaInicial() {
        this.telaInicial.ligar();
    }
    
}
