package com.mercacortex.ecasielles_ad_xml.model;

import java.util.ArrayList;

public class Prediccion {
    String today;

    String tempMax;
    String tempMin;

    ArrayList<EstadoCielo> estadosCielo;

    public Prediccion() {
        this.estadosCielo = new ArrayList<>();
    }

    public String getDay() { return today; }
    public void setDay(String day) {
        String[] fecha = day.split("-");
        this.today = fecha[2] + "/" + fecha[1];
    }
    public String getTempMax() { return tempMax; }
    public void setTempMax(String tempMax) { this.tempMax = tempMax; }
    public String getTempMin() { return tempMin; }
    public void setTempMin(String tempMin) { this.tempMin = tempMin; }

    public ArrayList<EstadoCielo> getEstadosCielo() {
        return estadosCielo;
    }
    public void addEstadoCielo(String periodo, String descripcion, String codImagen) {
        estadosCielo.add(new EstadoCielo(periodo, descripcion, codImagen));
    }

    @Override
    public String toString() {
        return "Prediccion{" +
                "today='" + today + '\'' +
                ", tempMax='" + tempMax + '\'' +
                ", tempMin='" + tempMin + '\'' +
                ", estadosCielo=" + estadosCielo +
                '}';
    }

    public class EstadoCielo {
        String periodo;
        String descripcion;
        String codImagen;

        public EstadoCielo(String periodo, String descripcion, String codImagen) {
            this.periodo = periodo;
            this.descripcion = descripcion;
            this.codImagen = codImagen;
        }

        public String getPeriodo() { return periodo; }
        public void setPeriodo(String periodo) {  this.periodo = periodo; }
        public String getDescripcion() {  return descripcion; }
        public void setDescripcion(String descripcion) {  this.descripcion = descripcion; }
        public String getCodImagen() {  return codImagen; }
        public void setCodImagen(String codImagen) {  this.codImagen = codImagen; }

        @Override
        public String toString() {
            return "EstadoCielo{" +
                    "periodo='" + periodo + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    ", codImagen='" + codImagen + '\'' +
                    '}';
        }
    }
}
