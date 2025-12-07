package org.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Paciente {
    private String curp;
    private String nombre;
    private String edad;
    private String dir;
    private String tel;

    public Paciente() {}

    public Paciente(String curp, String nombre, String edad, String dir, String tel) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.dir = dir;
        this.tel = tel;
    }

    // Getters y Setters
    public String getCurp() { return curp; }
    public void setCurp(String curp) { this.curp = curp; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEdad() { return edad; }
    public void setEdad(String edad) { this.edad = edad; }
    public String getDir() { return dir; }
    public void setDir(String dir) { this.dir = dir; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
}