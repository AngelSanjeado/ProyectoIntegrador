public enum Prioridad {
    ALTA,
    MEDIA,
    BAJA,
    SIN_ASIGNAR;

    public static Prioridad getPrioridad(int i){
        return Prioridad.values()[i];
    }
}