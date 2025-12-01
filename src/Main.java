import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        GestionIncidencia gestion = new GestionIncidencia();
        iniciarDatos(gestion);

        System.out.println("\n===== Bienvenido al sistema de Ticketing =====");
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
                System.out.println("\nMenú principal \n\t1. Reportar incidencia \n\t2. Gestionar incidencias (técnico) \n\t0. Salir del programa");
                System.out.print("Ingrese una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese su matrícula:");
                    String matriculaUsuario = sc.nextLine();
                    Usuario usuario = buscarPersona(gestion.getUsuarios(), matriculaUsuario);
                    String continuar;

                    do {
                        System.out.print("Describa la incidencia: ");
                        String description = sc.nextLine();

                        int i = 0;
                        System.out.println("Tipo incidencia:");
                        for (TipoIncidencia tp : TipoIncidencia.values()) {
                            if (tp != TipoIncidencia.SIN_ASIGNAR) {
                                System.out.printf("\t%d. %s\n", ++i, tp.name());
                            }
                        }
                        System.out.print("¿Qué tipo de incidencia es?: ");
                        int incidencia = sc.nextInt();
                        TipoIncidencia tipo = TipoIncidencia.getIncidencia(incidencia - 1);

                        System.out.print("¿Cuál es el ID de la computadora (número de la etiqueta): ");
                        sc.nextLine();
                        String id = sc.nextLine();

                        i = 0;

                        System.out.println("Salones:");
                        for (Salon s : Salon.values()) {
                            if (s != Salon.NULL)
                                System.out.printf("\t%d. %s\n", ++i, s.toString());
                        }
                        System.out.print("Seleccione el salón donde ocurrió: ");
                        int sln = sc.nextInt();
                        Salon salon = Salon.getSalon(sln - 1);

                        gestion.registrarIncidencia(new Incidencia(usuario, description, salon, id, tipo));
                        System.out.println("Reporte registrado :)");

                        System.out.print("\n¿Desea reportar otra incidencia? (Si/No): ");
                        sc.nextLine();
                        continuar = sc.nextLine();

                    }while (continuar.equalsIgnoreCase("Si"));

                    break;

                case 2:
                    System.out.println("\n===== Inicia sesión =====");

                    Tecnico tecnico;
                    do {
                        System.out.print("Inicie sesión con su matricula: ");
                        String matriculaTecnico = sc.nextLine();
                        tecnico = buscarPersona(gestion.getTecnicos(), matriculaTecnico);

                        if (tecnico == null) {
                            System.out.println("No se encontró la matricula. Intente de nuevo...");
                        }

                    } while (tecnico == null);

                    if (tecnico.getNivel() != NivelTecnico.GERENTE) {
                        System.out.println("\n===== Panel de control general =====");
                        tecnicoGeneral(tecnico, sc);
                    } else {
                        System.out.println("\n===== Menú de Técnico Gerente =====");
                        System.out.printf("Bienvenido Gerente %s\n", tecnico.getNombre());
                        tecnicoGerente(gestion, sc);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
            }

        }while (opcion != 0);
    }

    public static void iniciarDatos(GestionIncidencia gestion){
        Persona[] tecnicos = new Tecnico[] {
                new Tecnico("TG100", "Antonio", TipoIncidencia.HARDWARE, NivelTecnico.GERENTE),

                new Tecnico("TH201", "Teresa", TipoIncidencia.HARDWARE, NivelTecnico.SENIOR),
                new Tecnico("TH202", "Omar", TipoIncidencia.HARDWARE, NivelTecnico.SEMI_SENIOR),
                new Tecnico("TH203", "Katia", TipoIncidencia.HARDWARE, NivelTecnico.JUNIOR),

                new Tecnico("TS301", "Rafa", TipoIncidencia.SOFTWARE, NivelTecnico.SENIOR),
                new Tecnico("TS302", "Yahel", TipoIncidencia.SOFTWARE, NivelTecnico.SEMI_SENIOR),
                new Tecnico("TS303", "Altair", TipoIncidencia.SOFTWARE, NivelTecnico.JUNIOR),

                new Tecnico("TR401", "Irwin", TipoIncidencia.RED, NivelTecnico.SENIOR),
                new Tecnico("TR402", "Emilio", TipoIncidencia.RED, NivelTecnico.SEMI_SENIOR),
                new Tecnico("TR403", "Luis", TipoIncidencia.RED, NivelTecnico.JUNIOR)
        };

        for (Tecnico t: (Tecnico[]) tecnicos){
            gestion.registrarTecnico(t);
        }

        Persona[] usuarios = new Usuario[]{
                new Usuario("S24016726", "Daniel", "LIS-301"),
                new Usuario("S24016721", "Katia", "LIS-301"),
                new Usuario("S24016731", "Omar", "LIS-301"),
                new Usuario("S24016704", "Altair", "LIS-301"),
                new Usuario("S24016698", "Vaughan", "LIS-301"),
        };

        for (Usuario u: (Usuario[]) usuarios){
            gestion.registrarUsuarios(u);
        }
    }

    public static <T extends Persona> T buscarPersona(ArrayList<T> personas, String matriculaUsuario){
        for (T p: personas){
            if(p.getMatricula().equals(matriculaUsuario)){
                return p;
            }
        }
        return null;
    }

    public static void tecnicoGerente(GestionIncidencia gestion,Scanner scanner){
        int opcion = 0;
        do {
            System.out.println("\t1. Asignar prioridad a las incidencias y repartir las tareas a cada técnico");
            System.out.println("\t0. Cerrar sesión");
            System.out.print("Elija una acción: ");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    gestion.asignarPrioridad(scanner);
                    gestion.asignarIncidencias();
                    System.out.println();
                    System.out.println("Las tareas han sido asignadas :)");
                    break;

                case 0:
                    System.out.println("Cerrando sesión...");
            }
        }while(opcion != 0);
    }

    public static void tecnicoGeneral(Tecnico tecnico, Scanner scanner){
        int opcion = 0;

        System.out.printf("Bienvenido %s", tecnico.getNombre());

        do {
            System.out.println("\n\t1. Ver mis incidencias asignadas");
            System.out.println("\t2. Actualizar el estado de la incidencia");
            System.out.println("\t0. Cerrar sesión");
            System.out.print("Elija una acción: ");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("\n==========================================================================================");
                    System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-15s |\n",
                            "ID", "Matrícula", "Tipo", "Prioridad", "Estado");
                    System.out.println("==========================================================================================");

                    if (tecnico.getIncidencias().isEmpty()) {
                        System.out.println("|                              NO TIENES TAREAS ASIGNADAS                                |");
                    } else {
                        for (Incidencia i : tecnico.getIncidencias()) {
                            System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-15s |\n",
                                    i.getId(),
                                    i.getUsuario(),
                                    i.getTipoIncidencia(),
                                    (i.getPrioridad() != null ? i.getPrioridad() : "N/A"),
                                    (i.getEstado() != null ? i.getEstado() : "N/A")
                            );

                            System.out.printf("| Desc: %-81s |\n", i.getDescription());
                            System.out.println("------------------------------------------------------------------------------------------");
                        }
                    }
                    System.out.println("==========================================================================================\n");
                    break;

                case 2:

                    for (Incidencia i: tecnico.getIncidencias()){
                        if(i.getEstado() != EstadoIncidencia.TERMINADA){
                            System.out.printf("ID: %s\tEstado: %s\n", i.getId(), i.getEstado());
                        }
                    }

                    System.out.print("Ingrese el ID de la incidencia a actualizar: ");
                    scanner.nextLine();
                    String id = scanner.nextLine();
                    Incidencia inc = tecnico.buscarIncidencia(id);

                    if (inc != null) {
                        System.out.printf("Estado actual: %s\n", inc.getEstado());
                        System.out.println("Nuevo estado (1. En proceso\t2. Terminada)");
                        System.out.print("Introduzca el número del nuevo estado: ");
                        int estado = scanner.nextInt();
                        inc.asignarEstado(EstadoIncidencia.values()[estado + 1]);
                        System.out.print("Estado actualizado :)");
                    }

                    break;

                case 0:
                    System.out.println("Cerrando sesión...");
            }
        }while(opcion != 0);
    }
}