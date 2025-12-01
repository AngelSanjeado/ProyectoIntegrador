public class Incidencia {
    private static int contadorHW = 0;
    private static int contadorSW = 0;
    private static int contadorRED = 0;

    private String id;
    private Usuario usuario;
    private String description;
    private Salon salon;
    private String equipoComputoID;
    private TipoIncidencia tipoIncidencia;
    private Prioridad prioridad;
    private Tecnico tecnico;
    private EstadoIncidencia estado;

    public Incidencia(){
        id = generarID(TipoIncidencia.SIN_ASIGNAR);
        usuario = null;
        description = "";
        salon = Salon.NULL;
        equipoComputoID = "";
        tipoIncidencia = TipoIncidencia.SIN_ASIGNAR;
        prioridad = Prioridad.SIN_ASIGNAR;
        tecnico = null;
        estado = EstadoIncidencia.SIN_ASIGNAR;
    }

    public Incidencia(Usuario usuario, String description, Salon salon, String equipoComputoID,TipoIncidencia tipoIncidencia){
        id = generarID(tipoIncidencia);
        this.usuario = usuario;
        this.description = description;
        this.salon = salon;
        this.equipoComputoID = equipoComputoID;
        this.tipoIncidencia = tipoIncidencia;
        this.prioridad = Prioridad.SIN_ASIGNAR;
        tecnico = null;
        estado = EstadoIncidencia.SIN_ASIGNAR;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUsuario(){
        return usuario.getMatricula();
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Salon getSalon(){
        return salon;
    }

    public void setSalon(Salon salon){
        this.salon = salon;
    }

    public String getEquipoComputo(){
        return equipoComputoID;
    }

    public void setEquipoComputo(String equipoComputoID){
        this.equipoComputoID = equipoComputoID;
    }

    public TipoIncidencia getTipoIncidencia(){
        return tipoIncidencia;
    }

    public void setTipoIncidencia(TipoIncidencia tipo){
        this.tipoIncidencia = tipo;
    }

    public Prioridad getPrioridad(){
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad){
        this.prioridad = prioridad;
    }

    public Tecnico getTecnico(){
        return tecnico;
    }

    public void asignarTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public EstadoIncidencia getEstado(){
        return estado;
    }

    public void asignarEstado(EstadoIncidencia estado){
        this.estado = estado;
    }

    public String generarID(TipoIncidencia tipo){
        StringBuilder generarID = new StringBuilder();

        switch (tipo){
            case HARDWARE:
                generarID.append("HW-").append(String.format("%03d", ++contadorHW));
                break;

            case SOFTWARE:
                generarID.append("SW-").append(String.format("%03d", ++contadorSW));
                break;

            case RED:
                generarID.append("RED-").append(String.format("%03d", ++contadorRED));
                break;

            default:
                generarID.append("ST-").append(String.format("%03d", 0)); //ST significa "Sin Tipo"
                break;
        }
        return generarID.toString();
    }

    public String toString(){
        return String.format("\nID:\n\t%s \nReportado por:\n\t%s \nDescripción:\n\t%s \nTipo de incidencia:\n\t%s \nComputadora numero:\n\t%s \nSalón:\n\t%s", getId(), getUsuario(), getDescription(), getTipoIncidencia(), getEquipoComputo(), getSalon());
    }
}


