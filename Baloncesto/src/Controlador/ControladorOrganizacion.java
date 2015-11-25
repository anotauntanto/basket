/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Arbitro;
import Modelo.Clases.Comprobaciones;
import Modelo.Clases.Entrenador;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import Modelo.DAO.ArbitroDAO;
import Modelo.DAO.EntrenadorDAO;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.JugadorDAO;
import Modelo.DAO.PersonaDAO;
import Vistas.VistaOrganizacionOK;
import static java.lang.System.exit;
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
    boolean insertar;
    boolean actualizar;

    public ControladorOrganizacion(VistaOrganizacionOK miVista) {
        this.miVista = miVista;
    }

    public void insertarPersona(int rol) {

        insertar = true;
        actualizar = false;
        this.persona = new Persona();

        javax.swing.JTextField campo;

        //Nombre
        campo = miVista.getCampoNombre();
        persona.setNombre(campo.getText());

        //Apellidos
        campo = miVista.getCampoApellidos();
        persona.setApellidos(campo.getText());

        //DNI
        campo = miVista.getCampoDNI(); 
        if (PersonaDAO.obtenerPersonaPorDni(campo.getText()) != null) {
            actualizar=true;
        }
        if (!Comprobaciones.esDNIbueno(campo.getText())) {
            insertar=false;
            //queda imprimir mensaje 
        } else {
            persona.setDni(campo.getText());
        }
        
        //Fecha
        campo = miVista.getCampoFechaN();
        Date date = null;
        try {
            date = formatter.parse(campo.getText());
            persona.setFechaN(date);
        } catch (ParseException ex) {
            insertar=false;
            //mostrar mensaje
            //Logger.getLogger(ControladorOrganizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Telefono
        campo = miVista.getCampoTelefono();
        if (!Comprobaciones.esTlfbueno(campo.getText())) {
            //mostrar mensaje
            insertar=false;
        } else {
            persona.setTlf(campo.getText());
        }
        
        //Email
        campo = miVista.getCampoEmail();
        persona.setEmail(campo.getText());

        //Contraseña
        campo = miVista.getCampoContrasena();
        persona.setContrasena(campo.getText());

        //Rol
        persona.setRol(rol);

        if (rol == miVista.getRol_jugador()) {

            this.jug = new Jugador(persona);

            //Dorsal
            campo = miVista.getCampoDorsal();
            try {
                int dorsal = Integer.parseInt(campo.getText());
                this.jug.setDorsal(dorsal);
                
            }catch (NumberFormatException e) {
                insertar=false;
                
                //mostrar mensaje
            }

            //Altura
            campo = miVista.getCampoAltura();
            try {
                double altura = Double.parseDouble(campo.getText());
                this.jug.setAltura(altura);
                miVista.getCampoErrorAltura().setVisible(false);
                
            }catch (NumberFormatException e) {
                insertar=false;
                miVista.getCampoErrorAltura().setVisible(true);
                //mostrar mensaje
            } 
            
            //Nombre equipo
            campo = miVista.getCampoNombreEquipoJugador();
            int id_equipo = EquipoDAO.obtenerIdEquipo(campo.getText());
            if (id_equipo == 0) {
                insertar = false;
                //mostrar mensaje de que el equipo no existe
            } else {
                this.jug.setIdEquipo(id_equipo);
            }
            
            //INSERCIONES
            if (insertar) {
                System.out.println("LocuraaaaaJ");
                //mostrar Etiqueta de todo bien
                //vaciar campos
                JugadorDAO.insertarJugador(jug);
            } else {
                System.out.println("Fallo de campos");
            }
            
            
             System.out.println(jug);
            
        } else if (rol == miVista.getRol_arbitro()) {
            this.arbitro = new Arbitro();

            //Provincia
            javax.swing.JComboBox combo;
            combo = miVista.getCampoProvincia();
            this.arbitro.setProvincia(combo.getSelectedItem().toString());

            //INSERCIONES
            if (insertar) {
                System.out.println("LocuraaaaaA");
                //mostrar Etiqueta de todo bien
                ArbitroDAO.insertarArbitro(arbitro);
            } else {
                System.out.println("Fallo de campos");
            }
            
            System.out.println(arbitro);

        } else if (rol == miVista.getRol_entrenador()) {
            this.entrenador = new Entrenador();

            //Nivel
            campo = miVista.getCampoNivel();
            int nivel = Integer.parseInt(campo.getText());
            this.entrenador.setNivel(nivel);

            //Equipo entrenador
            campo = miVista.getCampoEquipoEntrenador();
            int id_equipo = EquipoDAO.obtenerIdEquipo(campo.getText());
            if (id_equipo == 0) {
                insertar = false;
                //mostrar mensaje de que el equipo no existe
            } else {
                this.jug.setIdEquipo(id_equipo);
            }
            
            //INSERCIONES
            if (insertar) {
                EntrenadorDAO.insertarEntrenador(entrenador);
                System.out.println("LocuraaaaaE");
                //mostrar Etiqueta de todo bien
                
            } else {
                System.out.println("Fallo de campos");
            }
            
            System.out.println(entrenador);
            
        } else {
            
            //INSERCIONES
            if (insertar) {
                PersonaDAO.insertarPersona(persona);
                System.out.println("LocuraaaaaP");
                //mostrar Etiqueta de todo bien
                
            } else {
                System.out.println("Fallo de campos");
            }
            
        }

        System.out.println(persona);

    }

}
