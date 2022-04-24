package Interface;

import Modelo.Nota;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public interface IPersistencia extends Remote {

    public boolean autenticarEstudiante(String idEstudiante, String contrasena) throws RemoteException;
    public boolean verificarEstudiante(String idEstudiante) throws RemoteException;
    public boolean autenticarProfesor(String idProfesor, String contrasena) throws RemoteException;
    public void introducirNota(String idAsignatura, String idAlumno, String idNota, double nota) throws RemoteException;
    public void borrarNota(String idAsignatura, String idAlumno, String idNota) throws RemoteException;
    public void modificarNota(String idAsignatura, String idAlumno, String idNota, double nota) throws RemoteException;
    public Nota consultarNota (String idAsignatura, String idAlumno, String idNota) throws RemoteException;
    public ArrayList<Nota> consultarNotasXAsignatura(String idAsignatura, String idAlumno) throws RemoteException;
    public ArrayList<Nota> consultarNotas(String idAlumno) throws RemoteException;
    public ArrayList<String> asignaturasEstudiante(String idEstudiante) throws RemoteException;
    public ArrayList<String> asignaturasProfesor(String idProfesor) throws RemoteException;

}
