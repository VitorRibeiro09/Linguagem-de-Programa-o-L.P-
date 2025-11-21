package com.djavan.navio_crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_NAME = "navio.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_NAME;


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTables() {
        String sqlNavio = "CREATE TABLE IF NOT EXISTS navio ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT NOT NULL,"
                + "tonelagem REAL NOT NULL,"
                + "destino TEXT NOT NULL"
                + ");";

        String sqlMar = "CREATE TABLE IF NOT EXISTS mar ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "profundidade REAL NOT NULL,"
                + "cor TEXT NOT NULL,"
                + "estado TEXT NOT NULL"
                + ");";

        String sqlPessoa = "CREATE TABLE IF NOT EXISTS pessoa ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT NOT NULL,"
                + "emocao TEXT NOT NULL,"
                + "localizacao TEXT NOT NULL"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Cria as tabelas
            stmt.execute(sqlNavio);
            stmt.execute(sqlMar);
            stmt.execute(sqlPessoa);
            System.out.println("Tabelas criadas ou já existentes.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
    static {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver SQLite carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver SQLite NÃO foi encontrado!");
        }
    }

}
