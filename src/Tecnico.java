import java.util.ArrayList;

public class Tecnico extends Persona{
    //Reutilizo la enumeracion TipoIncidencia para no hacer una enumeriacion Especialidad o Departamento que asignaría lo mismo que TipoIncidencia
    private TipoIncidencia especialidad;
    private NivelTecnico nivel;
    private ArrayList<Incidencia> incidencias;

    public Tecnico(){
        super();
        especialidad = TipoIncidencia.SIN_ASIGNAR;
        nivel = NivelTecnico.NULL;
    }

    public Tecnico(String matricula, String nombre, TipoIncidencia especialidad, NivelTecnico nivel){
        super(matricula, nombre);
        this.especialidad = especialidad;
        this.nivel = nivel;
        incidencias = new ArrayList<>();
    }

    public TipoIncidencia getEspecialidad(){
        return especialidad;
    }


    public void setEspecialidad(TipoIncidencia especialidad){
        this.especialidad = especialidad;
    }

    public NivelTecnico getNivel(){
        return nivel;
    }

    public void setNivel(NivelTecnico nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Incidencia> getIncidencias(){
        return incidencias;
    }

    public void asignarIncidencia(Incidencia incidencia){
        incidencias.add(incidencia);
    }

    public Incidencia buscarIncidencia(String id){
        Incidencia inc = null;
        for (Incidencia i: incidencias){
            if (i.getId().equals(id)){
                inc = i;
                System.out.println("ID encontrado");
            }
            else System.out.println("No se encontró el ID");
        }
        return inc;
    }

    public String toString(){
        return String.format("%s %s %s", super.getMatricula(), super.getNombre(), getEspecialidad());
    }

}
