package com.djavan.navio_crud.gui;

import com.djavan.navio_crud.dao.DatabaseConnection;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        super("CRUD Djavan - Navio, Mar e Pessoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Inicializa o banco de dados e cria as tabelas
        DatabaseConnection.createTables();

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Selecione a Entidade para Gerenciar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        
        JButton btnNavio = new JButton("Gerenciar Navio");
        JButton btnMar = new JButton("Gerenciar Mar");
        JButton btnPessoa = new JButton("Gerenciar Pessoa");

        btnNavio.addActionListener(e -> new NavioGUI().setVisible(true));
        btnMar.addActionListener(e -> new MarGUI().setVisible(true));
        btnPessoa.addActionListener(e -> new PessoaGUI().setVisible(true));

        panel.add(titleLabel);
        panel.add(btnNavio);
        panel.add(btnMar);
        panel.add(btnPessoa);

        add(panel);
    }
}
