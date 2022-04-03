package Persistencia;

import Interface.IPersistencia;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

public class Persistencia extends UnicastRemoteObject implements IPersistencia {

    Registry registry;
    Connection connection = null;

    public Persistencia(String name) throws RemoteException {
        super();
        try{
            connectDatabase();
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, this);
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }

    public void connectDatabase() {
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=123Mateo*");
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
                if(idProfesor.equals(resultSet.getString("idProfesor"))) {
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

    @Override
    public double consultarNota(String idAsignatura, String idAlumno) throws RemoteException{
        double nota = -1.0;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from NotaXEstudiante");
            while(resultSet.next()){
                if(resultSet.getString("Estudiante_idEstudiante").equals(idAlumno) && resultSet.getString("Asignatura_idAsignatura").equals(idAsignatura))
                nota = resultSet.getDouble("nota");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return nota;
    }

    @Override
    public ArrayList<String> asignaturasEstudiante(String idEstudiante) throws RemoteException{
        ArrayList<String> asignaturas = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from NotaXEstudiante ");
            while(resultSet.next()){
                if(resultSet.getString("Estudiante_idEstudiante").equals(idEstudiante)) {
                    asignaturas.add(resultSet.getString("Asignatura_idAsignatura"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return asignaturas;
    }

    @Override
    public ArrayList<String> asignaturasProfesor(String idProfesor) throws RemoteException{
        ArrayList<String> asignaturas = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Asignatura");
            while(resultSet.next()){
                if(resultSet.getString("Profesor_idProfesor").equals(idProfesor)) {
                    asignaturas.add(resultSet.getString("idAsignatura"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return asignaturas;
    }


}
