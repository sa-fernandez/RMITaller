package Controlador;

import Interface.IPersistencia;
import Modelo.Nota;

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

    public boolean autenticarEstudiante(String idEstudiante, String contrasena){
        try {
            return iPersistencia.autenticarEstudiante(idEstudiante, contrasena);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return false;
        }
    }

    public boolean verificarEstudiante(String idEstudiante){
        try{
            return iPersistencia.verificarEstudiante(idEstudiante);
        }catch (Exception e){
            System.err.println("System exception" + e);
            return false;
        }
    }

    public boolean autenticarProfesor(String idProfesor, String contrasena) {
        try{
            return iPersistencia.autenticarProfesor(idProfesor, contrasena);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return false;
        }
    }

    public void introducirNota(String idAsignatura, String idAlumno, String idNota, double nota) {
        try{
            iPersistencia.introducirNota(idAsignatura, idNota, idAlumno, nota);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public void borrarNota(String idAsignatura, String idNota, String idAlumno){
        try{
            iPersistencia.borrarNota(idAsignatura, idNota, idAlumno);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public void modificarNota(String idAsignatura, String idAlumno, String idNota, double nota){
        try{
            iPersistencia.modificarNota(idAsignatura, idAlumno, idNota, nota);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public Nota consultarNota (String idAsignatura, String idAlumno, String idNota){
        try{
            return iPersistencia.consultarNota(idAsignatura, idAlumno, idNota );
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return null;
        }
    }

    public ArrayList<Nota> consultarNotaXAsignatura (String idAsignatura, String idAlumno){
        try{
            return iPersistencia.consultarNotasXAsignatura(idAsignatura, idAlumno);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return null;
        }
    }

    public ArrayList<Nota> consultarNotas (String idAlumno){
        try{
            return iPersistencia.consultarNotas(idAlumno);
        } catch(Exception e) {
            System.err.println("System exception" + e);
            return null;
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
