public enum Salon {
    LAB_LIS_01,
    LA_CUEVA,
    NULL;

    public String toString(){
        return String.format("%s", this.name().replace("_", " "));
    }

    public static Salon getSalon(int i){
        return Salon.values()[i];
    }
}
