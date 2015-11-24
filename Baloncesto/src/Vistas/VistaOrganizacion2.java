/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
 
/* MenuSelectionManagerDemo.java requires images/middle.gif. */
 
/*
 * This class is just like MenuDemo, except every second (thanks
 * to a Timer) the selected path of the menu is printed in the text area.
 */
public class VistaOrganizacion2 implements ActionListener, ItemListener {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    public final static int ONE_SECOND = 1000;
    private final String nombre_insercion = "Inserción";
    private final String nombre_insercion_jugador = "Insertar jugador";
    private final String nombre_insercion_arbitro = "Insertar árbitro";
    private final String nombre_insercion_entrenador = "Insertar entrenador";
    private final String nombre_insercion_equipo = "Insertar equipo";
 
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
 
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu(nombre_insercion);
      
        menu.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.addActionListener(this);
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
 
     
        //Build the second menu.
        menu = new JMenu("Jornadas");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
      
        return menuBar;
    }
 
    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        return contentPane;
    }
 
    public void actionPerformed(ActionEvent e) {
        
        JMenu source = (JMenu)(e.getSource());
        String caso = source.getText();
        System.out.println ("wiiii" + source.getText());
        switch (caso) {
            
            case nombre_insercion:
                System.out.println ("Pruebaaaa");
            break;
                
            case nombre_insercion_entrenador:
                System.out.println ("Jugador");
            break;
        }
            
            
        
        String s = "Action event detected."
                   + newline
                   + "    Event source: ("+ e.getModifiers()+ ")" + source.getText()
                   + " (an instance of " + getClassName(source) + ")";
        
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }
 
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Item event detected."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        output.append(s + newline);
        output.setCaretPosition(output.getDocument().getLength());
    }
 
    // Returns just the class name -- no package info.
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
 
   /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuSelectionManagerDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        VistaOrganizacion2 demo = new VistaOrganizacion2();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
 
        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}