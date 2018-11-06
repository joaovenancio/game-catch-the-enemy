/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoso;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class TrabalhoSO {
    
    public int amendoim;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorJogo controlador = new ControladorJogo();
        try {
            controlador.iniciarJogo();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrabalhoSO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
