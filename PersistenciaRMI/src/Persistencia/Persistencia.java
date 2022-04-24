package Persistencia;

import Interface.IPersistencia;
import Modelo.Nota;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=123Ferchito*");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean autenticarEstudiante(String idEstudiante, String contrasena) throws RemoteException {
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
    public boolean verificarEstudiante(String idEstudiante) throws RemoteException {
        boolean result = false;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Estudiante");
            while(resultSet.next()){
                if(idEstudiante.equals(resultSet.getString("idEstudiante"))) {
                    result = true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean autenticarProfesor(String idProfesor, String contrasena) throws RemoteException {
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
    public void introducirNota(String idAsignatura, String idAlumno, String idNota, double nota) throws RemoteException {
        try{
            String query = "insert into Nota values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idNota);
            preparedStatement.setDouble(2, nota);
            preparedStatement.setString(3, idAlumno);
            preparedStatement.setString(4, idAsignatura);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void borrarNota(String idAsignatura, String idAlumno, String idNota) throws RemoteException {
        try{
            String query = "delete from Nota where estudiantexasignatura_Estudiante_idEstudiante = ? and estudiantexasignatura_Asignatura_idAsignatura = ? and idNota = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idAlumno);
            preparedStatement.setString(2, idAsignatura);
            preparedStatement.setString(3, idNota);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificarNota(String idAsignatura, String idAlumno, String idNota, double nota) throws RemoteException {
        try{
            String query = "update Nota set valor = ? where estudiantexasignatura_Estudiante_idEstudiante = ? and estudiantexasignatura_Asignatura_idAsignatura = ? and idNota = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, nota);
            preparedStatement.setString(2, idAlumno);
            preparedStatement.setString(3, idAsignatura);
            preparedStatement.setString(4, idNota);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Nota consultarNota(String idAsignatura, String idAlumno, String idNota) throws RemoteException{
        Nota nota = new Nota();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Nota");
            while(resultSet.next()){
                if(resultSet.getString("estudiantexasignatura_Estudiante_idEstudiante").equals(idAlumno) && resultSet.getString("estudiantexasignatura_Asignatura_idAsignatura").equals(idAsignatura) && resultSet.getString("idNota").equals(idNota)) {
                    nota.setValor(resultSet.getDouble("valor"));
                    nota.setIdNota(resultSet.getString("idNota"));
                    nota.setIdAsignatura(resultSet.getString("estudiantexasignatura_Asignatura_idAsignatura"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return nota;
    }

    @Override
    public ArrayList<Nota> consultarNotasXAsignatura(String idAsignatura, String idAlumno) throws RemoteException{
        ArrayList<Nota> notas = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Nota");
            while(resultSet.next()){
                if(resultSet.getString("estudiantexasignatura_Estudiante_idEstudiante").equals(idAlumno) && resultSet.getString("estudiantexasignatura_Asignatura_idAsignatura").equals(idAsignatura)) {
                    Nota nota = new Nota(resultSet.getString("idNota"), resultSet.getString("estudiantexasignatura_Asignatura_idAsignatura"), resultSet.getDouble("valor"));
                    notas.add(nota);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return notas;
    }

    @Override
    public ArrayList<Nota> consultarNotas(String idAlumno) throws RemoteException{
        ArrayList<Nota> notas = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Nota");
            while(resultSet.next()){
                if(resultSet.getString("estudiantexasignatura_Estudiante_idEstudiante").equals(idAlumno)) {
                    Nota nota = new Nota(resultSet.getString("idNota"), resultSet.getString("estudiantexasignatura_Asignatura_idAsignatura"), resultSet.getDouble("valor"));
                    notas.add(nota);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return notas;
    }

    @Override
    public ArrayList<String> asignaturasEstudiante(String idEstudiante) throws RemoteException{
        ArrayList<String> asignaturas = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from EstudianteXAsignatura ");
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
