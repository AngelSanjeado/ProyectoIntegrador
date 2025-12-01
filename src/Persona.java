public abstract class Persona{
    private String matricula;
    private String nombre;

    public Persona(){
        matricula = "";
        nombre = "";
    }

    public Persona(String matricula, String nombre){
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public abstract String toString();
}
