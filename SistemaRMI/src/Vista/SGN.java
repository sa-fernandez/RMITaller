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
            if (controladorSistema.autenticarEstudiante(id, contrasena)) {
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
                        System.out.println();
                        System.out.print("Digite el numero de la asignatura a consultar nota: ");
                        int num = scI.nextInt();
                        System.out.println("Asignatura " + asignaturas.get(num - 1) + " escogida");
                        System.out.print("(P -> Consultar todas las notas de la asignatura|| E -> Consultar nota en especifico de la asignatura) > ");
                        opc = scS.nextLine();
                        System.out.println();
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
                    System.out.println();
                    boolean terminar = true;
                    do {
                        System.out.print("(S -> Seguir || C -> Cerrar sesion) > ");
                        String opt = scS.nextLine();
                        if (opt.toUpperCase().equals("C")) {
                            est = false;
                        }else if(opt.toUpperCase().equals("S")){
                            terminar = false;
                        }else{
                            System.out.println("Opción incorrecta");
                        }
                        System.out.println();
                    }while(terminar);
                }while(est);
            } else if (controladorSistema.autenticarProfesor(id, contrasena)) {
                boolean pro = true;
                System.out.println("Bienvenido de nuevo Profesor " + id);
                System.out.println();
                do {
                    boolean verfAsig = true;
                    ArrayList<String> asignaturas;
                    int nAsignatura;
                    do {
                        System.out.println("Lista de asignaturas");
                        asignaturas = controladorSistema.asignaturasProfesor(id);
                        for (int i = 0; i < asignaturas.size(); i++) {
                            System.out.println(i + 1 + ". " + asignaturas.get(i));
                        }
                        System.out.println();
                        System.out.print("Digite el numero de la asignatura: ");
                        nAsignatura = scI.nextInt();
                        if(nAsignatura <= asignaturas.size() && nAsignatura > 0){
                            verfAsig = false;
                        }else{
                            System.out.println("Asignatura invalida, intente de nuevo");
                        }
                    }while(verfAsig);
                    System.out.println("Asignatura " + asignaturas.get(nAsignatura - 1) + " escogida");
                    boolean verfEst = true;
                    String idEst;
                    do {
                        System.out.print("Ingrese el id del estudiante: ");
                        idEst = scS.nextLine();
                        if(!controladorSistema.verificarEstudiante(idEst)){
                            System.out.println("Estudiante no existente, intente de nuevo");
                        }else{
                            verfEst = false;
                        }
                    }while(verfEst);
                    System.out.println();
                    ArrayList<Nota> notas = controladorSistema.consultarNotaXAsignatura(asignaturas.get(nAsignatura - 1), idEst);
                    for(Nota n: notas){
                        System.out.println("Asignatura: " + n.getIdAsignatura() + " - " + n.getIdNota() + ": " + n.getValor());
                    }
                    System.out.println();
                    System.out.print("(I -> Insertar Nota || M -> Modificar Nota || B -> Borrar Nota) > ");
                    String opc = scS.nextLine();
                    System.out.println();
                    if(opc.toUpperCase().equals("I")){
                        System.out.print("Ingrese el tipo de nota del estudiante: ");
                        String idNotaI = scS.nextLine();
                        System.out.print("Ingrese la nota del estudiante: ");
                        double notaI = scD.nextDouble();
                        controladorSistema.introducirNota(asignaturas.get(nAsignatura - 1), idNotaI, idEst, notaI);
                        System.out.println("Nota correctamente introducida");
                    }else if(opc.toUpperCase().equals("M")){
                        System.out.print("Ingrese el tipo de nota del estudiante: ");
                        String idNotaM = scS.nextLine();
                        System.out.print("Ingrese la nueva nota del estudiante: ");
                        double notaM = scD.nextDouble();
                        controladorSistema.modificarNota(asignaturas.get(nAsignatura - 1), idEst, idNotaM, notaM);
                        System.out.println("Nota correctamente modificada");
                    }else if(opc.toUpperCase().equals("B")){
                        System.out.print("Ingrese el tipo de nota del estudiante: ");
                        String idNotaD = scS.nextLine();
                        controladorSistema.borrarNota(asignaturas.get(nAsignatura - 1), idNotaD, idEst);
                        System.out.println("Nota correctamente eliminada");
                    }else{
                        System.out.println("Opcion invalida");
                    }
                    System.out.println();
                    boolean terminar = true;
                    do {
                        System.out.print("(S -> Seguir || C -> Cerrar sesion) > ");
                        String opt = scS.nextLine();
                        if (opt.toUpperCase().equals("C")) {
                            pro = false;
                        }else if(opt.toUpperCase().equals("S")){
                            terminar = false;
                        }else{
                            System.out.println("Opción incorrecta");
                        }
                        System.out.println();
                    }while(terminar);
                }while(pro);
            } else {
                System.out.println("INGRESO INVALIDO, INTENTE NUEVAMENTE");
            }
            boolean terminar = true;
            do {
                System.out.print("(S -> Seguir || C -> Cerrar sesion) > ");
                String opt = scS.nextLine();
                if (opt.toUpperCase().equals("C")) {
                    seguir = false;
                }else if(opt.toUpperCase().equals("S")){
                    terminar = false;
                }else{
                    System.out.println("Opción incorrecta");
                }
                System.out.println();
            }while(terminar);
        }while(seguir);

    }

}
