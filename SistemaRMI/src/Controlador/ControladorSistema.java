package Controlador;

import Interface.IPersistencia;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class ControladorSistema {

    IPersistencia iPersistencia;

    public ControladorSistema(String host){
        try {
            System.out.println("Binding rmi://" + host + ":" + 1099 + "/PersistenciaRMI...");
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            iPersistencia = (IPersistencia) registry.lookup("PersistenciaRMI");
            System.out.println("[Server PersistenciaRMI] : ON");
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public boolean verificarEstudiante(String idEstudiante, String contrasena){
        try {
            return iPersistencia.verificarEstudiante(idEstudiante, contrasena);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return false;
        }
    }

    public boolean verificarProfesor(String idProfesor, String contrasena) {
        try{
            return iPersistencia.verificarProfesor(idProfesor, contrasena);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return false;
        }
    }

    public void introducirNota(String idAsignatura, String idAlumno, double nota) {
        try{
            iPersistencia.introducirNota(idAsignatura, idAlumno, nota);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public void borrarNota(String idAsignatura, String idAlumno){
        try{
            iPersistencia.borrarNota(idAsignatura, idAlumno);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public void modificarNota(String idAsignatura, String idAlumno, double nota){
        try{
            iPersistencia.modificarNota(idAsignatura, idAlumno, nota);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public double consultarNota (String idAsignatura, String idAlumno ){
        try{
            return iPersistencia.consultarNota(idAsignatura, idAlumno );
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return -1;
        }
    }

    public ArrayList<String> asignaturasProfesor(String idProfesor){
        try{
            return iPersistencia.asignaturasProfesor(idProfesor);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return null;
        }
    }

    public ArrayList<String> asignaturasEstudiante(String idEstudiante){
        try{
            return iPersistencia.asignaturasEstudiante(idEstudiante);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return null;
        }
    }

}
