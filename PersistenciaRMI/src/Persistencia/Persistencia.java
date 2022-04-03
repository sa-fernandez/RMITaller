package Persistencia;

import Interface.IPersistencia;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class Persistencia extends UnicastRemoteObject implements IPersistencia {

    Registry registry;
    Connection connection = null;

    public Persistencia(String name) throws RemoteException {
        super();
        try{
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, this);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    @Override
    public void connectDatabase() throws RemoteException {
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?user=root&password=123Mateo*");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verificarEstudiante(String idEstudiante, String contrasena) throws RemoteException {
        boolean result = false;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Estudiante");
            while(resultSet.next()){
                if(idEstudiante.equals(resultSet.getString("idEstudiante"))) {
                    if (contrasena.equals(resultSet.getString("contrasena"))) {
                        result = true;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean verificarProfesor(String idProfesor, String contrasena) throws RemoteException {
        boolean result = false;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Profesor");
            while(resultSet.next()){
                if(idProfesor.equals(resultSet.getString("idEstudiante"))) {
                    if (contrasena.equals(resultSet.getString("contrasena"))) {
                        result = true;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void introducirNota(String idAsignatura, String idAlumno, double nota) throws RemoteException {
        try{
            String query = "insert into NotaXEstudiante values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idAlumno);
            preparedStatement.setString(2, idAsignatura);
            preparedStatement.setDouble(3, nota);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void borrarNota(String idAsignatura, String idAlumno) throws RemoteException {
        try{
            String query = "delete from NotaXEstudiante where Estudiante_idEstudiante = ? and Asignatura_idAsignatura = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idAlumno);
            preparedStatement.setString(2, idAsignatura);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificarNota(String idAsignatura, String idAlumno, double nota) throws RemoteException {
        try{
            String query = "update NotaXEstudiante set nota = ? where Estudiante_idEstudiante = ? and Asignatura_idAsignatura = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, nota);
            preparedStatement.setString(2, idAlumno);
            preparedStatement.setString(3, idAsignatura);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
