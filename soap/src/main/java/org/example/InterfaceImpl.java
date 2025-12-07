package org.example;

import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.controladores.MedicoController;
import org.controladores.PacienteController;
import org.model.Medico;
import org.model.Paciente;

import java.util.ArrayList;

@WebService(
        serviceName = "Hospital",
        targetNamespace = "http://hospital.ejemplo.com/",
        endpointInterface = "org.example.Interface",
        portName = "HospitalPort"
)
public class InterfaceImpl implements Interface {
    private final PacienteController pacientes = new PacienteController();
    private final MedicoController medicos = new MedicoController();

   @Override
    public String registrarPaciente(@WebParam(name = "paciente") Paciente paciente) {
        if (pacientes.addPaciente(paciente)) {
            return "Paciente con CURP " + paciente.getCurp() + " registrado exitosamente.";
        }
        return "Error al registrar al paciente.";
    }

    @Override
    public Paciente consultarPacientePorCurp(@WebParam(name = "curp") String curp) {
        return pacientes.getPacienteByCurp(curp);
    }

    @Override
    public ArrayList<Paciente> obtenerTodosLosPacientes() {
        return pacientes.getAllPacientes();
    }

    @Override
    public String actualizarPaciente(@WebParam(name = "paciente") Paciente paciente) {
        if (pacientes.updatePaciente(paciente)) {
            return "Paciente con CURP " + paciente.getCurp() + " actualizado exitosamente.";
        }
        return "Error al actualizar al paciente.";
    }

    @Override
    public String eliminarPaciente(@WebParam(name = "curp") String curp) {
        if (pacientes.deletePaciente(curp)) {
            return "Paciente con CURP " + curp + " eliminado exitosamente.";
        }
        return "Error al eliminar al paciente.";
    }

    @Override
    public String agregarMedico(@WebParam(name = "medico") Medico medico) {
        if (medicos.addMedico(medico)) {
            return "Médico con cédula " + medico.getCedula() + " agregado exitosamente.";
        }
        return "Error al agregar al médico.";
    }

    @Override
    public Medico consultarMedicoPorCedula(@WebParam(name = "cedula") String cedula) {
        return medicos.getMedicoByCedula(cedula);
    }

    @Override
    public ArrayList<Medico> obtenerTodosLosMedicos() {
        return medicos.getAllMedicos();
    }

    @Override
    public String actualizarMedico(@WebParam(name = "medico") Medico medico) {
        if (medicos.updateMedico(medico)) {
            return "Médico con cédula " + medico.getCedula() + " actualizado exitosamente.";
        }
        return "Error al actualizar al médico.";
    }

    @Override
    public String eliminarMedico(@WebParam(name = "cedula") String cedula) {
        if (medicos.deleteMedico(cedula)) {
            return "Médico con cédula " + cedula + " eliminado exitosamente.";
        }
        return "Error al eliminar al médico.";
    }
}