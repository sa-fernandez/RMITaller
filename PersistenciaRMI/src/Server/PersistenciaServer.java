package Server;

import Persistencia.Persistencia;

public class PersistenciaServer {
    public static void main(String[] args) {
        try {
            Persistencia persistencia = new Persistencia("PersistenciaRMI");
        } catch(Exception e) {
            System.err.println("System exception" + e);
        }
    }
}
