package com.mercacortex.ecasielles_ad_xml.model;


import java.io.Serializable;
import java.util.Comparator;

public class Estacion implements Comparable, Serializable {
    int id, bicisDisponibles, anclajesDisponibles;
    String calle, estado, fechaUltimaModif;
    double longitud, latitud;

    public static final Comparator<Estacion> ID_COMPARATOR = new Comparator() {
        @Override
        public int compare(Object o, Object t1) {
            return ((Estacion)o).compareTo(t1);
        }
    };

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public int getBicisDisponibles() { return bicisDisponibles; }
    public void setBicisDisponibles(int bicisDisponibles) { this.bicisDisponibles = bicisDisponibles; }
    public int getAnclajesDisponibles() { return anclajesDisponibles; }
    public void setAnclajesDisponibles(int anclajesDisponibles) { this.anclajesDisponibles = anclajesDisponibles; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getFechaUltimaModif() { return fechaUltimaModif; }
    public void setFechaUltimaModif(String fechaUltimaModif) { this.fechaUltimaModif = fechaUltimaModif; }
    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    @Override
    public int compareTo(Object o) {
        return this.getId() - ((Estacion) o).getId();
    }

}
