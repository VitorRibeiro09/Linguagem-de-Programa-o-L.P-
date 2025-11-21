package com.djavan.navio_crud.dao;

import com.djavan.navio_crud.model.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO implements DAO<Pessoa> {

    @Override
    public void create(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, emocao, localizacao) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmocao());
            stmt.setString(3, pessoa.getLocalizacao());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pessoa.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Pessoa read(int id) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pessoa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("emocao"),
                            rs.getString("localizacao")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Pessoa> readAll() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pessoas.add(new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("emocao"),
                        rs.getString("localizacao")
                ));
            }
        }
        return pessoas;
    }

    @Override
    public void update(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, emocao = ?, localizacao = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmocao());
            stmt.setString(3, pessoa.getLocalizacao());
            stmt.setInt(4, pessoa.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
