package com.djavan.navio_crud.dao;

import com.djavan.navio_crud.model.Navio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NavioDAO implements DAO<Navio> {

    @Override
    public void create(Navio navio) throws SQLException {
        String sql = "INSERT INTO navio (nome, tonelagem, destino) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, navio.getNome());
            stmt.setDouble(2, navio.getTonelagem());
            stmt.setString(3, navio.getDestino());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    navio.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Navio read(int id) throws SQLException {
        String sql = "SELECT * FROM navio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Navio(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("tonelagem"),
                            rs.getString("destino")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Navio> readAll() throws SQLException {
        List<Navio> navios = new ArrayList<>();
        String sql = "SELECT * FROM navio";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                navios.add(new Navio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("tonelagem"),
                        rs.getString("destino")
                ));
            }
        }
        return navios;
    }

    @Override
    public void update(Navio navio) throws SQLException {
        String sql = "UPDATE navio SET nome = ?, tonelagem = ?, destino = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, navio.getNome());
            stmt.setDouble(2, navio.getTonelagem());
            stmt.setString(3, navio.getDestino());
            stmt.setInt(4, navio.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM navio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
