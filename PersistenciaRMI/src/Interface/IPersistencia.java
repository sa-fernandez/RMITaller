package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPersistencia extends Remote {

    public void connectDatabase() throws RemoteException;
    public boolean verificarEstudiante(String idEstudiante, String contrasena) throws RemoteException;
    public boolean verificarProfesor(String idProfesor, String contrasena) throws RemoteException;
    public void introducirNota (String idAsignatura , String idAlumno , double nota) throws RemoteException;
    public void borrarNota (String idAsignatura , String idAlumno) throws RemoteException;
    public void modificarNota (String idAsignatura , String idAlumno , double nota) throws RemoteException;

}
