package com.djavan.navio_crud.dao;

import com.djavan.navio_crud.model.Mar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarDAO implements DAO<Mar> {

    @Override
    public void create(Mar mar) throws SQLException {
        String sql = "INSERT INTO mar (profundidade, cor, estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, mar.getProfundidade());
            stmt.setString(2, mar.getCor());
            stmt.setString(3, mar.getEstado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    mar.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Mar read(int id) throws SQLException {
        String sql = "SELECT * FROM mar WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mar(
                            rs.getInt("id"),
                            rs.getDouble("profundidade"),
                            rs.getString("cor"),
                            rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Mar> readAll() throws SQLException {
        List<Mar> mares = new ArrayList<>();
        String sql = "SELECT * FROM mar";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                mares.add(new Mar(
                        rs.getInt("id"),
                        rs.getDouble("profundidade"),
                        rs.getString("cor"),
                        rs.getString("estado")
                ));
            }
        }
        return mares;
    }

    @Override
    public void update(Mar mar) throws SQLException {
        String sql = "UPDATE mar SET profundidade = ?, cor = ?, estado = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, mar.getProfundidade());
            stmt.setString(2, mar.getCor());
            stmt.setString(3, mar.getEstado());
            stmt.setInt(4, mar.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM mar WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
