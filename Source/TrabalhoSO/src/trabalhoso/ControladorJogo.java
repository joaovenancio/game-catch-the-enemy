/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

import trabalhoso.views.TelaJogo;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import trabalhoso.views.TelaFinalDoJogo;
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
    private TelaFinalDoJogo telaFinal;
    private Inimigo[] inimigos;
    final JFXPanel fxPanel = new JFXPanel();
    private int pontos;
    private boolean terminou;
    private boolean semTempo;
    
    //Construtor:
    public ControladorJogo() {
        this.telaInicial = new TelaInicial(this);
        this.telaJogo = new TelaJogo(this);
        this.telaFinal = new TelaFinalDoJogo(this);
        this.inimigos = new Inimigo[5];
        this.pontos = 0;
        this.terminou = false;
        this.semTempo = false;
        
        //Instanciar logo a musica para jah ficar armazenado na memoria
        String musica = "Laser.wav";
        Media arquivoMusica = new Media(new File(musica).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(arquivoMusica);
        mediaPlayer.play();
        
    }
    
    public void iniciarJogo() {
        
        this.telaJogo.ligar();
        
        //Iniciar o Timer:
        Timer temporizador = new Timer();
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                ControladorJogo.this.semTempo = true;
                ControladorJogo.this.telaJogo.desligar();
            }
        }, 100000);
        
        //Iniciar os inimigos:
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
        
        //Esperar pelos inimigos serem eliminados ou 
        while (!semTempo && (this.pontos < 5)) {
            try {
                sleep(0l);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        
        
        terminou = true;
        mediaPlayer.stop();
        if (semTempo) {
            this.telaFinal.definirMensagemPerdeu();
        } else {
            this.telaFinal.definirMensagemGanhou();
        }
        this.telaJogo.desligar();
        this.telaJogo = null;
        this.telaFinal.ligar();
        
    }
    
    public void jogarNovamente() {
        this.telaJogo = new TelaJogo(this);
        System.gc();
        this.telaInicial.ligar();
        this.telaFinal.desligar();
    }
    
    public synchronized void registrarPonto() {
        this.pontos++;
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
