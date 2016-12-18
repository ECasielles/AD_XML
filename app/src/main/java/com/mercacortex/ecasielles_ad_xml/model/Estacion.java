package com.mercacortex.ecasielles_ad_xml.model;


public class Estacion {
    int id, bicisDisponibles, anclajesDisponibles;
    String uri, calle, estado,  icon;
    Coordenadas coordenadas;


    class Coordenadas {
        double longitud, latitud;
        String type;
    }
}
