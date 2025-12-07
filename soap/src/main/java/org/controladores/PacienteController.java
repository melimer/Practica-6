package org.controladores;

import org.model.Paciente;
import org.service.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class PacienteController {

    private Paciente mapResultSetToPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente(
                rs.getString("curp"),
                rs.getString("nombre"),
                rs.getString("edad"),
                rs.getString("dir"),
                rs.getString("tel")
        );
        return paciente;
    }

    public boolean addPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (curp, nombre, edad, dir, tel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, paciente.getCurp());
            pstmt.setString(2, paciente.getNombre());
            pstmt.setString(3, paciente.getEdad());
            pstmt.setString(4, paciente.getDir());
            pstmt.setString(5, paciente.getTel());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Paciente getPacienteByCurp(String curp) {
        String sql = "SELECT * FROM paciente WHERE curp = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curp);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPaciente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Paciente> getAllPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pacientes.add(mapResultSetToPaciente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public boolean updatePaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET nombre = ?, edad = ?, dir = ?, tel = ? WHERE curp = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getEdad());
            pstmt.setString(3, paciente.getDir());
            pstmt.setString(4, paciente.getTel());
            pstmt.setString(5, paciente.getCurp());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deletePaciente(String curp) {
        String sql = "DELETE FROM paciente WHERE curp = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curp);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}