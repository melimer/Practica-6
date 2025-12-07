package org.example;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import org.model.*;
import java.util.ArrayList;

@WebService(targetNamespace = "http://hospital.ejemplo.com/")
@SOAPBinding(style = Style.DOCUMENT) // Estilo de comunicación (RPC o DOCUMENT)
public interface Interface {

    @WebMethod(operationName = "registrarPaciente")
    public String registrarPaciente(@WebParam(name = "paciente") Paciente paciente);

    @WebMethod(operationName = "consultarPacientePorCurp")
    public Paciente consultarPacientePorCurp(@WebParam(name = "curp") String curp);

    @WebMethod(operationName = "obtenerTodosLosPacientes")
    public ArrayList<Paciente> obtenerTodosLosPacientes();

    @WebMethod(operationName = "actualizarPaciente")
    public String actualizarPaciente(@WebParam(name = "paciente") Paciente paciente);

    @WebMethod(operationName = "eliminarPaciente")
    public String eliminarPaciente(@WebParam(name = "curp") String curp);

    @WebMethod(operationName = "agregarMedico")
    public String agregarMedico(@WebParam(name = "medico") Medico medico);

    @WebMethod(operationName = "consultarMedicoPorCedula")
    public Medico consultarMedicoPorCedula(@WebParam(name = "cedula") String cedula);

    @WebMethod(operationName = "obtenerTodosLosMedicos")
    public ArrayList<Medico> obtenerTodosLosMedicos();

    @WebMethod(operationName = "actualizarMedico")
    public String actualizarMedico(@WebParam(name = "medico") Medico medico);

    @WebMethod(operationName = "eliminarMedico")
    public String eliminarMedico(@WebParam(name = "cedula") String cedula);
}