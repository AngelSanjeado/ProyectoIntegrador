public enum TipoIncidencia {
    HARDWARE,
    SOFTWARE,
    RED,
    SIN_ASIGNAR;


    public static TipoIncidencia getIncidencia(int i){
        return TipoIncidencia.values()[i];
    }
}
