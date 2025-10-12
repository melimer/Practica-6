package org.controladores;

import org.service.DatabaseConnection;
import org.model.Medico;
import java.sql.*;
import java.util.ArrayList;

public class MedicoController {

    public boolean addMedico(Medico medico) {
        String sql = "INSERT INTO medico (cedula, nombre, dir, idEsp, tel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, medico.getCedula());
            pstmt.setString(2, medico.getNombre());
            pstmt.setString(3, medico.getDir());
            pstmt.setString(4, medico.getIdEsp());
            pstmt.setString(5, medico.getTel());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Medico getMedicoByCedula(String cedula) {
        String sql = "SELECT * FROM medico WHERE cedula = ?";
        Medico medico = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                medico = new Medico(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("dir"),
                        rs.getString("idEsp"),
                        rs.getString("tel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medico;
    }

    public ArrayList<Medico> getAllMedicos() {
        ArrayList<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medico";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                medicos.add(new Medico(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("dir"),
                        rs.getString("idEsp"),
                        rs.getString("tel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    public boolean updateMedico(Medico medico) {
        String sql = "UPDATE medico SET nombre = ?, dir = ?, idEsp = ?, tel = ? WHERE cedula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, medico.getNombre());
            pstmt.setString(2, medico.getDir());
            pstmt.setString(3, medico.getIdEsp());
            pstmt.setString(4, medico.getTel());
            pstmt.setString(5, medico.getCedula());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMedico(String cedula) {
        String sql = "DELETE FROM medico WHERE cedula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cedula);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}