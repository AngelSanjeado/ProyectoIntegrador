public class Usuario extends Persona{
    private String grupo;

    public Usuario(){
        super();
        grupo = "";
    }

    public Usuario(String matricula, String nombre, String grupo){
        super(matricula, nombre);
        this.grupo = grupo;
    }

    public String getGrupo(){
        return grupo;
    }

    public void setGrupo(String grupo){
        this.grupo = grupo;
    }

    public String toString(){
        return String.format("%s %s %s", super.getMatricula(), super.getNombre(), getGrupo());
    }
}
