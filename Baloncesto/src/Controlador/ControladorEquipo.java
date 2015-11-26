/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Excepciones.EquipoException;
import Modelo.Clases.Equipo;
import Modelo.DAO.EquipoDAO;
import Vistas.VistaOrganizacionOK;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inftel08
 */
public class ControladorEquipo {

    private VistaOrganizacionOK miVista;

    private Equipo equipo;

    public ControladorEquipo(VistaOrganizacionOK miVista) {
        this.miVista = miVista;

    }

    public void insertarEquipo() {

        boolean ok = true;
        boolean actualizar = false;
        this.equipo = new Equipo(); //vamos a crear un nuevo equipo

        javax.swing.JTextField insequipo;

        //Nombre del equipo
        insequipo = miVista.getCampoNombreEquipo();

        try {
            EquipoDAO.comprobarEquipov2(insequipo.getText());
            equipo.setNombre(insequipo.getText());
        } catch (EquipoException ex) {
            ok = false;
            miVista.getCampoErrorNombreEquipo().setVisible(true);
        }

        //Provincia del equipo
        insequipo = miVista.getCampoProvinciaEquipo();
        equipo.setProvincia(insequipo.getText());

        //Categoria del equipo
        insequipo = miVista.getCampoCategoriaEquipo();
        try {
            int categoria = Integer.parseInt(insequipo.getText());
            equipo.setCategoria(categoria);

        } catch (NumberFormatException e) {
            ok = false;
            miVista.getCampoErrorCategoria().setVisible(true);

        }

        //INSERCIONES
        if (ok) {
            miVista.getCampoExitoEquipo().setVisible(true);

            EquipoDAO.insertarEquipo(equipo);

        }
    }
}
