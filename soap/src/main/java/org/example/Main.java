package org.example;

import jakarta.xml.ws.Endpoint;
public class Main {
    public static void main(String[] args) {
        String url = "http://localhost:8080/hospital";
        Endpoint.publish(url, new InterfaceImpl());
        System.out.println("Servicio SOAP publicado en: " + url + "?wsdl");
        System.out.println("Presiona Ctrl+C para detener el servicio.");
    }
}

