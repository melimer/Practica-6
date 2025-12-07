package org.model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Medico {
    private String cedula;
    private String nombre;
    private String dir;
    private String idEsp;
    private String tel;

    public Medico() {}

    public Medico(String cedula,String nombre, String dir, String idEsp,String tel ) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.dir = dir;
        this.idEsp = idEsp;
        this.tel = tel;
    }

    // Getters y Setters
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDir() { return dir; }
    public void setDir(String dir) { this.dir = dir; }
    public String getIdEsp() { return idEsp; }
    public void setIdEsp(String idEsp) { this.idEsp = idEsp; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
}