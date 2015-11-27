/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clases.Equipo;
import baloncesto.FicherosTipo;
import Modelo.Clases.Partido;
import Modelo.Clases.PartidoJugado;
import Modelo.DAO.EquipoDAO;
import Modelo.DAO.PartidoDAO;
import Modelo.DAO.PartidoJugadoDAO;
import Vistas.VistaOrganizacion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inftel08
 */
public class ControladorClasificacion {

    private VistaOrganizacion miVista;
    javax.swing.JTable campoTabla;
    DefaultTableModel modelo;
    private JTable campoTabla1;
    private DefaultTableModel modelito;

    public ControladorClasificacion(VistaOrganizacion miVista) {
        this.miVista = miVista;
        listarClasificacion();
    }

    public void listarClasificacion() {

        String tipo_competicion = FicherosTipo.leerFichero();

        if (tipo_competicion.equals("Copa")) {

            miVista.getjScrollCopa().setVisible(true);
            miVista.getjScrollLiga().setVisible(false);
            campoTabla = miVista.getTablaClasificacionCopa();
            modelo = (DefaultTableModel) campoTabla.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            int jornada_maxima = PartidoDAO.obtenerJornadaActual();

            for (int i = jornada_maxima; i > 0; i--) {
                List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(i);
                for (Partido p : listarPartidosJornada) {

                    List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());

                    int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
                    int equipoB = listarEquiposporPartido.get(1).getIdEquipo();

                    modelo.addRow(new Object[]{i, EquipoDAO.obtenerNombreEquipo(equipoA), EquipoDAO.obtenerNombreEquipo(equipoB), p.getResultado()});

                }
            }

        } else if (tipo_competicion.equals("Liga")) {

            miVista.getjScrollLiga().setVisible(true);
            miVista.getjScrollCopa().setVisible(false);

            campoTabla = miVista.getTablaClasificacionLiga();
            modelo = (DefaultTableModel) campoTabla.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            //List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(0);
            List<Equipo> obtenerTodosEquipos = EquipoDAO.obtenerTodosEquipos();
            Collections.sort(obtenerTodosEquipos);
            System.out.println(obtenerTodosEquipos);
            for (Equipo p : obtenerTodosEquipos) {
                modelo.addRow(new Object[]{p.getNombre()});

            }

        } else {
            /*
             miVista.getjScrollLiga().setVisible(true);
             miVista.getjScrollCopa().setVisible(true);

             campoTabla1 = miVista.getTablaClasificacionCopa();
             modelito = (DefaultTableModel) campoTabla1.getModel();
             while (modelito.getRowCount() > 0) {
             modelito.removeRow(0);
             }

             campoTabla = miVista.getTablaClasificacionLiga();
             modelo = (DefaultTableModel) campoTabla.getModel();
             while (modelo.getRowCount() > 0) {
             modelo.removeRow(0);
             }
            
             //pintar liga
             List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(0);
             List<Equipo> obtenerTodosEquipos = new ArrayList<>();
            
             for (Partido p : listarPartidosJornada) {
             /*for (Equipo obtenerTodosEquipos) {
             }
             List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());
             listarEquiposporPartido.get(0).getIdEquipo();
             listarEquiposporPartido.get(1).getIdEquipo();
                
             obtenerTodosEquipos.add(null);
                
                
             }
             Collections.sort(obtenerTodosEquipos);
             System.out.println(obtenerTodosEquipos);
             for (Equipo p : obtenerTodosEquipos) {
             modelo.addRow(new Object[]{p.getNombre()});

             }*/

        }

    }

    public void generarPDF() throws FileNotFoundException, DocumentException, IOException {

        Calendar cal = Calendar.getInstance();
        String time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(cal.getTime());

        File file = new File("Clasificacion.pdf");
        FileOutputStream fileout = new FileOutputStream(file);
        Document document = new Document();
        PdfWriter.getInstance(document, fileout);

        Font fuente1 = new Font();
        Font fuente2 = new Font();
        Font fuente3 = new Font();
        fuente1.setStyle(Font.BOLD | Font.ITALIC | Font.UNDERLINE);
        fuente1.setSize(18);
        fuente1.setColor(0, 51, 204);
        fuente2.setStyle(Font.BOLD | Font.ITALIC);
        fuente2.setColor(0, 51, 204);
        fuente2.setSize(13);
        fuente3.setStyle(Font.BOLD | Font.ITALIC);
        fuente3.setSize(16);
        fuente3.setColor(255, 153, 0);
        
        document.open();

        Paragraph pa = new Paragraph("Fecha y hora: " + time); 
        
        pa.setAlignment(Element.ALIGN_RIGHT);
        document.add(pa);
        
        String url = "src/files/imagenPDF.jpg";
        Image imagen = Image.getInstance(url);  
       
        imagen.scalePercent(20);
        document.add(imagen);
        document.add(new Paragraph("\n"));
        Paragraph p3 = new Paragraph("GESTOR DE CAMPEONATO DE BALONCESTO", fuente1);
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);
        
        document.add(new Paragraph("\n"));
        String tipo_competicion = FicherosTipo.leerFichero();

        if (tipo_competicion.equals("Copa")) {
            Paragraph pa1 = new Paragraph("COPA", fuente3);
            pa1.setAlignment(Element.ALIGN_CENTER);
            document.add(pa1);
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));
            int jornada_maxima = PartidoDAO.obtenerJornadaActual();

            for (int i = jornada_maxima; i > 0; i--) {
                List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(i);
                for (Partido p : listarPartidosJornada) {

                    List<PartidoJugado> listarEquiposporPartido = PartidoJugadoDAO.listarEquiposporPartido(p.getIdPartido());

                    int equipoA = listarEquiposporPartido.get(0).getIdEquipo();
                    int equipoB = listarEquiposporPartido.get(1).getIdEquipo();

                    Paragraph p2 = new Paragraph("Equipo A: " + EquipoDAO.obtenerNombreEquipo(equipoA) + " - Equipo B: " + EquipoDAO.obtenerNombreEquipo(equipoB) + " - Resultado: " + p.getResultado(), fuente2);
                    p2.setAlignment(Element.ALIGN_LEFT);
                    document.add(p2);
                }

            }

        } else if (tipo_competicion.equals("Liga")) {
            Paragraph pa1 = new Paragraph("LIGA", fuente3);
            pa1.setAlignment(Element.ALIGN_LEFT);
            document.add(pa1);
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
            document.add(new Paragraph("\n"));
            //List<Partido> listarPartidosJornada = PartidoDAO.listarPartidosJornada(0);
            List<Equipo> obtenerTodosEquipos = EquipoDAO.obtenerTodosEquipos();
            Collections.sort(obtenerTodosEquipos);
            System.out.println(obtenerTodosEquipos);
            for (Equipo p : obtenerTodosEquipos) {
                Paragraph pa2 = new Paragraph(p.getNombre(),fuente2);
                pa2.setAlignment(Element.ALIGN_LEFT);
                document.add(pa2);
            }
            
        }
        document.close();
        File myfile = new File("Clasificacion.pdf");
        Desktop.getDesktop().open(myfile);

    }
}
