package com.mercacortex.ecasielles_ad_xml.model;


import java.util.Comparator;

public class Estacion implements Comparable{
    int id, bicisDisponibles, anclajesDisponibles;
    String uri, calle, estado,  icon;
    Coordenadas coordenadas;

    public static final Comparator ID_COMPARATOR = new Comparator() {
        @Override
        public int compare(Object o, Object t1) {
            ((Estacion)o).compareTo(t1);
            return 0;
        }
    };

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getBicisDisponibles() { return bicisDisponibles; }
    public void setBicisDisponibles(int bicisDisponibles) { this.bicisDisponibles = bicisDisponibles; }
    public int getAnclajesDisponibles() { return anclajesDisponibles; }
    public void setAnclajesDisponibles(int anclajesDisponibles) { this.anclajesDisponibles = anclajesDisponibles; }
    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public Coordenadas getCoordenadas() { return coordenadas; }
    public void setCoordenadas(Coordenadas coordenadas) { this.coordenadas = coordenadas; }

    @Override
    public int compareTo(Object o) {
        if(this.getId()>((Estacion) o).getId())
            return 1;
        else if ((this.getId()>((Estacion) o).getId()))
            return -1;
        return 0;
    }


    class Coordenadas {
        double longitud, latitud;
        String type;

        public Coordenadas(double longitud, double latitud, String type) {
            this.longitud = longitud;
            this.latitud = latitud;
            this.type = type;
        }
    }
}
