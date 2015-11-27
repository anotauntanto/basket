/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.DorsalException;
import Excepciones.EquipoException;
import Modelo.Clases.Arbitro;
import baloncesto.Comprobaciones;
import Modelo.Clases.Entrenador;
import Modelo.Clases.Jugador;
import Modelo.Clases.Persona;
import Modelo.DAO.ArbitroDAO;
import Modelo.DAO.EntrenadorDAO;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.JugadorDAO;
import Modelo.DAO.PersonaDAO;
import Vistas.VistaOrganizacion;
import static java.lang.System.exit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase EntrenadorResultado
 * @author grupo_baloncesto
 */
public class ControladorOrganizacion {
    
    private VistaOrganizacion miVista;
    private Jugador jug;
    private Persona persona;
    private Arbitro arbitro;
    private Entrenador entrenador;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    boolean ok;
    boolean actualizar;
    
    /**
     * Constructor por defecto
     * @param miVista VistaOrganizacion
     */
    public ControladorOrganizacion(VistaOrganizacion miVista) {
        this.miVista = miVista;
    }
    
    /**
     * Metodo para insertar persona por rol
     * @param rol int, rol a insertar
     */
    public void insertarPersona(int rol) {

        ok = true;
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
        Persona temp = PersonaDAO.obtenerPersonaPorDni(campo.getText());
        if (temp != null) {
            persona.setIdPersona(temp.getIdPersona());
            actualizar=true;
        }
        if (temp != null && rol != temp.getRol()) {
            
            ok=false;
        }
        if (!Comprobaciones.esDNIbueno(campo.getText())) {
            ok=false;
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
            ok=false;
            miVista.getCampoErrorFecha().setVisible(true);

        }
 
        //Telefono
        campo = miVista.getCampoTelefono();
        if (!Comprobaciones.esTlfbueno(campo.getText())) {
            
            ok=false;
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
        
        //COMPROBACIONES DE ROL
        if (rol == miVista.getRol_jugador()) {

            this.jug = new Jugador(persona);
           
            //Altura
            campo = miVista.getCampoAltura();
            try {
                double altura = Double.parseDouble(campo.getText());
                this.jug.setAltura(altura);
                
                
            }catch (NumberFormatException e) {
                ok=false;
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
                ok = false;
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
                ok=false;
                miVista.getCampoErrorDorsal().setVisible(true);
                
            } catch (DorsalException ex) {
                ok=false;
                miVista.getCampoErrorDorsal().setVisible(true);
            } 
            
            
            //INSERCIONES
            if (ok) {
                miVista.getCampoExito().setVisible(true);
                
                if (actualizar) {
                    
                    JugadorDAO.modificarJugador(jug);
                } else {
                    JugadorDAO.insertarJugador(jug);
                }
                
            } 
            
            
            
        } else if (rol == miVista.getRol_arbitro()) {
            this.arbitro = new Arbitro(persona);

            //Provincia
            javax.swing.JComboBox combo;
            combo = miVista.getCampoProvincia();
            this.arbitro.setProvincia(combo.getSelectedItem().toString());


            //INSERCIONES
            if (ok) {
                miVista.getCampoExito().setVisible(true);
                
                if (actualizar) {
                    ArbitroDAO.modificarArbitro(arbitro);
                } else {
                    ArbitroDAO.insertarArbitro(arbitro); 
                }

            } 
            

        } else if (rol == miVista.getRol_entrenador()) {
            this.entrenador = new Entrenador(persona);

            //Nivel
            campo = miVista.getCampoNivel();
            
            try {
                int nivel = Integer.parseInt(campo.getText());
                this.entrenador.setNivel(nivel);
                
                
            }catch (NumberFormatException e) {
                ok=false;
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
                
               ok = false;
                miVista.getCampoErrorEquipoEntrenador().setVisible(true);
            }
            
            //INSERCIONES
            if (ok) {
                miVista.getCampoExito().setVisible(true);
                if (actualizar) {
                   
                    EntrenadorDAO.modificarEntrenador(entrenador);
                } else {
                    EntrenadorDAO.insertarEntrenador(entrenador);
                }  
            } 
            
     
            
        } else {
            
            //INSERCIONES
            if (ok) {
                miVista.getCampoExito().setVisible(true);
                
                if (actualizar) {
                    PersonaDAO.modificarPersona(persona);
                } else {
                    PersonaDAO.insertarPersona(persona);
                }
            } 
            
        }


    }

}
