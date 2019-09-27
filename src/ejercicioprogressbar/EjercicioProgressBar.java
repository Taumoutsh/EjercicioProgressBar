/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioprogressbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Tomoutch
 */
public class EjercicioProgressBar extends JFrame {
    
    
    public static boolean stop = false;
    private static int i = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame fenetre = new JFrame();
        JPanel p = new JPanel();
        
        
        fenetre.setSize(400, 400);

        // create a progressbar 
        JProgressBar b = new JProgressBar(0, 1000000);
        JButton button = new JButton("Click");
  
        // set initial value 
        b.setValue(0); 
  
        b.setStringPainted(true); 
        
        button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                Thread t = new Thread(new Runnable() {
                 
                    @Override
                    public void run(){
                        for(i = 0; i < 1000000 && !stop; i++) {
                            System.out.println(i);

                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run(){
                                    b.setValue(i);
                                }
                            });

                        }
                    }
                          
                });
                
                t.start();
              
            }
        }); 
        
        fenetre.addWindowListener(new WindowAdapter() {
            
            public void WindowClosing(WindowEvent e){
                stop = true;
            }
        });
            
        p.add(b); 
        p.add(button);
        
        fenetre.add(p);
        
        fenetre.setVisible(true);
    }
    
}
