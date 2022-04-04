package Vista;

import Controlador.ControladorSistema;

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
            id = scI.nextLine();
            System.out.print("Ingrese contrasena: ");
            contrasena = scI.nextLine();
            System.out.println();
            if (controladorSistema.verificarEstudiante(id, contrasena)) {
                boolean est = true;
                System.out.println("Bienvenido de nuevo Estudiante " + id);
                System.out.println();
                do {
                    System.out.println("Lista de asignaturas");
                    ArrayList<String> asignaturas = controladorSistema.asignaturasEstudiante(id);
                    for (int i = 0; i < asignaturas.size(); i++) {
                        System.out.println(i + 1 + ". " + asignaturas.get(i));
                    }
                    System.out.print("Digite el numero de la asignatura a consultar nota: ");
                    int num = scI.nextInt();
                    System.out.println("Asignatura " + asignaturas.get(num - 1) + " escogida");
                    System.out.print("Su nota es: " + controladorSistema.consultarNota(asignaturas.get(num - 1), id));
                    System.out.print("(S -> Seguir || C -> Cerrar sesion) > ");
                    String opc = scS.nextLine();
                    if(opc.toUpperCase().equals("C")){
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
                        System.out.print("Ingrese la nota del estudiante: ");
                        double notaI = scD.nextDouble();
                        controladorSistema.introducirNota(asignaturas.get(nAsignatura), idEst, notaI);
                    }else if(opc.toUpperCase().equals("M")){
                        System.out.print("Ingrese la nueva nota del estudiante: ");
                        double notaM = scD.nextDouble();
                        controladorSistema.modificarNota(asignaturas.get(nAsignatura), idEst, notaM);
                    }else if(opc.toUpperCase().equals("B")){
                        controladorSistema.borrarNota(asignaturas.get(nAsignatura), idEst);
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
