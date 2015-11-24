/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Arbitro;
import Modelo.Clases.Entrenador;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import Modelo.DAO.PersonaDAO;
import Vistas.VistaOrganizacionOK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inftel08
 */
public class ControladorOrganizacion {

    private VistaOrganizacionOK miVista;
    private Jugador jug;
    private Persona persona;
    private Arbitro arbitro;
    private Entrenador entrenador;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public ControladorOrganizacion(VistaOrganizacionOK miVista) {
        this.miVista = miVista;
    }

    public void insertarPersona(int rol) {

        this.persona = new Persona();

        javax.swing.JTextField campo;

        campo = miVista.getCampoNombre();
        persona.setNombre(campo.getText());

        campo = miVista.getCampoApellidos();
        persona.setApellidos(campo.getText());

        campo = miVista.getCampoDNI();
        persona.setDni(campo.getText());

        campo = miVista.getCampoFechaN();
        Date date = null;
        try {
            date = formatter.parse(campo.getText());
        } catch (ParseException ex) {
            Logger.getLogger(ControladorOrganizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        persona.setFechaN(date);
          
                 campo = miVista.getCampoTelefono();
        persona.setTlf(campo.getText());

        campo = miVista.getCampoEmail();
        persona.setEmail(campo.getText());

        campo = miVista.getCampoContrasena();
        persona.setContrasena(campo.getText());

        persona.setRol(rol);

        if (rol == miVista.getRol_jugador()) {

            this.jug = new Jugador(persona);

            campo = miVista.getCampoDorsal();
            int dorsal = Integer.parseInt(campo.getText());
            this.jug.setDorsal(dorsal);

            campo = miVista.getCampoAltura();
            double altura = Double.parseDouble(campo.getText());
            this.jug.setAltura(altura);

                //System.out.println(jug);
                //FALTA ID EQUIPO
        } else if (rol == miVista.getRol_arbitro()) {
            this.arbitro = new Arbitro();

            javax.swing.JComboBox combo;
            combo = miVista.getCampoProvincia();
            this.arbitro.setProvincia(combo.getSelectedItem().toString());

            System.out.println(arbitro);

        } else if (rol == miVista.getRol_entrenador()) {
            this.entrenador = new Entrenador();

            campo = miVista.getCampoNivel();
            int nivel = Integer.parseInt(campo.getText());
            this.entrenador.setNivel(nivel);

            System.out.println(entrenador);
        }

        System.out.println(persona);

    }

}
