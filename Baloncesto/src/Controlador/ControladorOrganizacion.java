/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.DorsalException;
import Excepciones.EquipoException;
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
            miVista.getCampoErroDNI().setVisible(true);
            
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
            miVista.getCampoErrorFecha().setVisible(true);

        }
 
        //Telefono
        campo = miVista.getCampoTelefono();
        if (!Comprobaciones.esTlfbueno(campo.getText())) {
            
            insertar=false;
            miVista.getCampoErrorTelefono().setVisible(true);
            
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
           
            //Altura
            campo = miVista.getCampoAltura();
            try {
                double altura = Double.parseDouble(campo.getText());
                this.jug.setAltura(altura);
                
                
            }catch (NumberFormatException e) {
                insertar=false;
                miVista.getCampoErrorAltura().setVisible(true);
                
            } 
            
            //Nombre equipo
            campo = miVista.getCampoNombreEquipoJugador();
            int id_equipo = 0;
            try {
                EquipoDAO.comprobarEquipo(campo.getText());
                id_equipo = EquipoDAO.obtenerIdEquipo(campo.getText());
                this.jug.setIdEquipo(id_equipo);
                
            } catch (EquipoException ex) {
                insertar = false;
                miVista.getCampoErrorEquipoJugador().setVisible(true);
            }
            
            
            
            //Dorsal
            int dorsal = 0;
            campo = miVista.getCampoDorsal();
            boolean cont;
            try {
                dorsal = Integer.parseInt(campo.getText());
                this.jug.setDorsal(dorsal);
                JugadorDAO.comprobarDorsal(dorsal, id_equipo);
                
            }catch (NumberFormatException e) {
                insertar=false;
                miVista.getCampoErrorDorsal().setVisible(true);
                
            } catch (DorsalException ex) {
                insertar=false;
                miVista.getCampoErrorDorsal().setVisible(true);
            } 
            
            
            
            //INSERCIONES
            if (insertar) {
                System.out.println("LocuraaaaaJ");
                //mostrar Etiqueta de todo bien
                //vaciar campos
                //JugadorDAO.insertarJugador(jug);
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
                //ArbitroDAO.insertarArbitro(arbitro);
            } else {
                System.out.println("Fallo de campos");
            }
            
            System.out.println(arbitro);

        } else if (rol == miVista.getRol_entrenador()) {
            this.entrenador = new Entrenador();

            //Nivel
            campo = miVista.getCampoNivel();
            
            try {
                int nivel = Integer.parseInt(campo.getText());
                this.entrenador.setNivel(nivel);
                
                
            }catch (NumberFormatException e) {
                insertar=false;
                miVista.getCampoErrorNivel().setVisible(true);
                
            } 
           

            //Equipo entrenador            
            campo = miVista.getCampoEquipoEntrenador();
            int id_equipo = 0;
            try {
            
                EquipoDAO.comprobarEquipo(campo.getText());
                id_equipo = EquipoDAO.obtenerIdEquipo(campo.getText());
                this.entrenador.setIdEquipo(id_equipo);
                
            } catch (EquipoException ex){
                
               insertar = false;
                miVista.getCampoErrorEquipoEntrenador().setVisible(true);
            }
            
            
            //INSERCIONES
            if (insertar) {
                //EntrenadorDAO.insertarEntrenador(entrenador);
                System.out.println("LocuraaaaaE");
                //mostrar Etiqueta de todo bien
                
            } else {
                System.out.println("Fallo de campos");
            }
            
            System.out.println(entrenador);
            
        } else {
            
            //INSERCIONES
            if (insertar) {
                //PersonaDAO.insertarPersona(persona);
                System.out.println("LocuraaaaaP");
                //mostrar Etiqueta de todo bien
                
            } else {
                System.out.println("Fallo de campos");
            }
            
        }

        System.out.println(persona);

    }

}
