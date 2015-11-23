/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author usuario
 */
public class MiVision  extends javax.swing.JFrame{
    private javax.swing.JButton jButton1;
    
    public MiVision(){
        super("Mi TABLA");
        final JTable myTabla = new JTable(2,4);
       
        myTabla.setPreferredScrollableViewportSize(new Dimension(500,70));
        
        JScrollPane scrollPane=new JScrollPane(myTabla);
        
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
            
    }                       
    public static void main (String args[]){
            MiVision ejemplo =new MiVision();
            ejemplo.pack();
            ejemplo.setVisible(true);
    }
}
