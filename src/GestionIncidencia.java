import java.util.ArrayList;
import java.util.Scanner;

public class GestionIncidencia{
    ArrayList<Tecnico> tecnicos;
    ArrayList<Incidencia> incidencias;
    ArrayList<Usuario> usuarios;

    public GestionIncidencia(){
        tecnicos = new ArrayList<>();
        incidencias = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void registrarTecnico(Tecnico tecnico){
        tecnicos.add(tecnico);
    }

    public ArrayList<Tecnico> getTecnicos(){
        return tecnicos;
    }

    public void registrarUsuarios(Usuario usuario){
        usuarios.add(usuario);
    }

    public ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }

    public void registrarIncidencia(Incidencia incidencia){
        incidencias.add(incidencia);
    }

    public ArrayList<Incidencia> getIncidencias(){
        return incidencias;
    }

    public void asignarPrioridad(Scanner scanner){
        if (incidencias.isEmpty()){
            System.out.println("\nNo hay incidencias reportadas :)");
            return;
        }

        Prioridad prioridad;
        for (Incidencia i: incidencias){
            if (i.getPrioridad() == Prioridad.SIN_ASIGNAR) {
                System.out.println("\n==========================================================================================");
                System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-15s |\n", "ID", "Matrícula", "Tipo", "Prioridad", "Estado");
                System.out.println("==========================================================================================");

                if (incidencias.isEmpty()) {
                    System.out.println("|                              NO TIENES TAREAS ASIGNADAS                                |");
                } else {
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

                System.out.println("==========================================================================================\n");
                System.out.println("Niveles de prioridad:\n1. Alta\t 2. Media\t 3. Baja");
                System.out.print("¿Que prioridad le asigna?: ");
                int pd = scanner.nextInt();
                prioridad = Prioridad.getPrioridad(pd - 1);
                i.setPrioridad(prioridad);
            }
        }
    }

    public void asignarIncidencias(){
        for (Incidencia i: incidencias){

            if (i.getTecnico() != null) continue;

            for (Tecnico t: tecnicos){

                if (t.getEspecialidad() != i.getTipoIncidencia()){
                    continue;
                }

                if ((t.getNivel() == NivelTecnico.JUNIOR) && (i.getPrioridad() == Prioridad.BAJA)){
                    t.asignarIncidencia(i);
                    i.asignarTecnico(t);
                    i.asignarEstado(EstadoIncidencia.ASIGNADA);
                    break;
                }

                if ((t.getNivel() == NivelTecnico.SEMI_SENIOR) && (i.getPrioridad() == Prioridad.MEDIA)){
                    t.asignarIncidencia(i);
                    i.asignarTecnico(t);
                    i.asignarEstado(EstadoIncidencia.ASIGNADA);
                    break;
                }

                if ((t.getNivel() == NivelTecnico.SENIOR) && (i.getPrioridad() == Prioridad.ALTA)){
                    t.asignarIncidencia(i);
                    i.asignarTecnico(t);
                    i.asignarEstado(EstadoIncidencia.ASIGNADA);
                    break;
                }
            }
        }
    }

}
