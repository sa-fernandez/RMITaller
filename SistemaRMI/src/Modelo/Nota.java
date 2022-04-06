package Modelo;

import java.io.Serializable;

public class Nota implements Serializable {

    private String idNota;
    private String idAsignatura;
    private double valor;

    public Nota() {
    }

    public Nota(String idNota, String idAsignatura, double valor) {
        this.idNota = idNota;
        this.idAsignatura = idAsignatura;
        this.valor = valor;
    }

    public String getIdNota() {
        return idNota;
    }

    public void setIdNota(String idNota) {
        this.idNota = idNota;
    }

    public String getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(String idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
