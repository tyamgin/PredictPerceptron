/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package percdig;

import javax.swing.JFrame;
import percdig.sin.SinRecognizer;

/**
 *
 * @author tyamgin
 */
public class PercDig {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //RecognizeWindow wnd = new RecognizeWindow();
        JFrame wnd = new SinRecognizer();
        wnd.setLocation(300, 300);
        wnd.setVisible(true);   
    }
}
