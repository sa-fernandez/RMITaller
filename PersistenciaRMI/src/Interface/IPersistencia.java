package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IPersistencia extends Remote {

    public boolean verificarEstudiante(String idEstudiante, String contrasena) throws RemoteException;
    public boolean verificarProfesor(String idProfesor, String contrasena) throws RemoteException;
    public void introducirNota(String idAsignatura, String idAlumno, double nota) throws RemoteException;
    public void borrarNota(String idAsignatura, String idAlumno) throws RemoteException;
    public void modificarNota(String idAsignatura, String idAlumno, double nota) throws RemoteException;
    public double consultarNota (String idAsignatura, String idAlumno ) throws RemoteException;
    public ArrayList<String> asignaturasEstudiante(String idEstudiante) throws RemoteException;
    public ArrayList<String> asignaturasProfesor(String idProfesor) throws RemoteException;

}
