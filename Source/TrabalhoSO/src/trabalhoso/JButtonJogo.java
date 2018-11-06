/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author joaov
 */
public class JButtonJogo extends JButton {
    //Atributo:
    private int numeroInimigo; //Indica qual inimigo ocupa esse botao, -1 se tiver nenhum inimigo
    
    //Construtores:
    public JButtonJogo(int numeroInimigo) {
        this.numeroInimigo = numeroInimigo;
    }

    public JButtonJogo(Icon icon, int numeroInimigo) {
        super(icon);
        this.numeroInimigo = numeroInimigo;
    }

    public JButtonJogo(String text, int numeroInimigo) {
        super(text);
        this.numeroInimigo = numeroInimigo;
    }

    public JButtonJogo(Action a, int numeroInimigo) {
        super(a);
        this.numeroInimigo = numeroInimigo;
    }

    public JButtonJogo(String text, Icon icon, int numeroInimigo) {
        super(text, icon);
        this.numeroInimigo = numeroInimigo;
    }
    
    //Metodos:
    public int getNumeroInimigo() {
        return numeroInimigo;
    }

    public void setNumeroInimigo(int numeroInimigo) {
        this.numeroInimigo = numeroInimigo;
    }
    
}
