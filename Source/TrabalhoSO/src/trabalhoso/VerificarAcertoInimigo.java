/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

import javax.swing.SwingWorker;

/**
 *
 * @author joaov
 */
public class VerificarAcertoInimigo extends SwingWorker{

    private TelaJogo tela;
    private int i;
    private int j;

    public VerificarAcertoInimigo(TelaJogo tela, int i, int j) {
        this.tela = tela;
        this.i = i;
        this.j = j;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        tela.controlador.verificarAcerto(i, j, tela.botoes[i][j].getNumeroInimigo());
        return null;
    }
    
}
