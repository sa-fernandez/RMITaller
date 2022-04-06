package Vista;

import Controlador.ControladorSistema;
import Modelo.Nota;

import java.util.ArrayList;
import java.util.Scanner;

public class SGN {

    static ControladorSistema controladorSistema;

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("=========SISTEMA DE GESTION DE NOTAS=========");
        System.out.println("=============================================");
        controladorSistema = new ControladorSistema(args[0]);
        Scanner scS = new Scanner(System.in);
        Scanner scI = new Scanner(System.in);
        Scanner scD = new Scanner(System.in);
        String id;
        String contrasena;
        boolean seguir = true;
        do {
            System.out.println("LOGIN");
            System.out.print("Ingrese ID: ");
            id = scS.nextLine();
            System.out.print("Ingrese contrasena: ");
            contrasena = scS.nextLine();
            System.out.println();
            if (controladorSistema.verificarEstudiante(id, contrasena)) {
                boolean est = true;
                System.out.println("Bienvenido de nuevo Estudiante " + id);
                System.out.println();
                do {
                    System.out.print("(T -> Consultar todas las notas || A -> Consultar notas por asignatura) > ");
                    String opc = scS.nextLine();
                    if(opc.toUpperCase().equals("T")) {
                        System.out.println("Sus notas de todas las asignaturas son las siguientes");
                        ArrayList<Nota> notas = controladorSistema.consultarNotas(id);
                        for(Nota n: notas){
                            System.out.println("Asignatura: " + n.getIdAsignatura() + " - " + n.getIdNota() + ": " + n.getValor());
                        }
                    }else if(opc.toUpperCase().equals("A")) {
                        System.out.println("Lista de asignaturas");
                        ArrayList<String> asignaturas = controladorSistema.asignaturasEstudiante(id);
                        for (int i = 0; i < asignaturas.size(); i++) {
                            System.out.println(i + 1 + ". " + asignaturas.get(i));
                        }
                        System.out.print("Digite el numero de la asignatura a consultar nota: ");
                        int num = scI.nextInt();
                        System.out.println("Asignatura " + asignaturas.get(num - 1) + " escogida");
                        System.out.print("(P -> Consultar todas las notas de la asignatura|| E -> Consultar nota en especifico de la asignatura) > ");
                        opc = scS.nextLine();
                        if(opc.toUpperCase().equals("P")) {
                            System.out.println("Sus notas son las siguientes");
                            ArrayList<Nota> notas = controladorSistema.consultarNotaXAsignatura(asignaturas.get(num - 1), id);
                            for(Nota n: notas){
                                System.out.println(n.getIdNota() + ": " + n.getValor());
                            }
                        } else if(opc.toUpperCase().equals("E")){
                            System.out.print("Ingrese el tipo de nota del estudiante: ");
                            String idNota = scS.nextLine();
                            System.out.println("Su nota es: " + controladorSistema.consultarNota(asignaturas.get(num - 1), id, idNota).getValor());
                        } else{
                            System.out.println("Opcion invalida, intentelo nuevamente");
                        }
                    }else{
                        System.out.println("Opcion invalida, intentelo nuevamente");
                    }
                    System.out.print("(S -> Seguir || C -> Cerrar sesion) > ");
                    opc = scS.nextLine();
                    if (opc.toUpperCase().equals("C")) {
                        est = false;
                    }
                }while(est);
            } else if (controladorSistema.verificarProfesor(id, contrasena)) {
                boolean pro = true;
                System.out.println("Bienvenido de nuevo Profesor " + id);
                System.out.println();
                do {
                    System.out.println("Lista de asignaturas");
                    ArrayList<String> asignaturas = controladorSistema.asignaturasProfesor(id);
                    for (int i = 0; i < asignaturas.size(); i++) {
                        System.out.println(i + 1 + ". " + asignaturas.get(i));
                    }
                    System.out.print("Digite el numero de la asignatura: ");
                    int nAsignatura = scI.nextInt();
                    System.out.println("Asignatura " + asignaturas.get(nAsignatura - 1) + " escogida");
                    System.out.print("Ingrese el id del estudiante: ");
                    String idEst = scS.nextLine();
                    System.out.print("(I -> Insertar Nota || M -> Modificar Nota || B -> Borrar Nota) > ");
                    String opc = scS.nextLine();
                    if(opc.toUpperCase().equals("I")){
                        System.out.print("Ingrese el tipo de nota del estudiante: ");
                        String idNotaI = scS.nextLine();
                        System.out.print("Ingrese la nota del estudiante: ");
                        double notaI = scD.nextDouble();
                        controladorSistema.introducirNota(asignaturas.get(nAsignatura), idNotaI,idEst, notaI);
                    }else if(opc.toUpperCase().equals("M")){
                        System.out.print("Ingrese el tipo de nota del estudiante: ");
                        String idNotaM = scS.nextLine();
                        System.out.print("Ingrese la nueva nota del estudiante: ");
                        double notaM = scD.nextDouble();
                        controladorSistema.modificarNota(asignaturas.get(nAsignatura), idEst, idNotaM, notaM);
                    }else if(opc.toUpperCase().equals("B")){
                        System.out.print("Ingrese el tipo de nota del estudiante: ");
                        String idNotaD = scS.nextLine();
                        controladorSistema.borrarNota(asignaturas.get(nAsignatura), idNotaD,idEst);
                        System.out.println("Nota eliminada correctamente");
                    }else{
                        System.out.println("Opcion invalida");
                    }
                    System.out.println("(S -> Seguir || C -> Cerrar sesion) > ");
                    String opt = scS.nextLine();
                    if(opt.toUpperCase().equals("C")){
                        pro = false;
                    }
                }while(pro);
            } else {
                System.out.println("INGRESO INVALIDO, INTENTE NUEVAMENTE");
            }
            System.out.print("Desea cerrar la aplicacion?: ");
            System.out.print("(S -> Si || N -> No) > ");
            String opc = scS.nextLine();
            if(opc.toUpperCase().equals("S")){
                seguir = false;
            }
        }while(seguir);

    }

}
