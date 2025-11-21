package com.djavan.navio_crud.gui;

import com.djavan.navio_crud.dao.NavioDAO;
import com.djavan.navio_crud.model.Navio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class NavioGUI extends BaseGUI {
    private NavioDAO navioDAO;
    private DefaultTableModel tableModel;
    private JTable navioTable;

    private JTextField txtNome;
    private JTextField txtTonelagem;
    private JTextField txtDestino;
    private JTextField txtId;

    public NavioGUI() {
        super("CRUD Navio - Djavan");
        navioDAO = new NavioDAO();
        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtNome = new JTextField();
        txtTonelagem = new JTextField();
        txtDestino = new JTextField();

        txtTonelagem.setName("Tonelagem"); // Para validação

        formPanel.add(new JLabel("ID (Auto):"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("Tonelagem (Double):"));
        formPanel.add(txtTonelagem);
        formPanel.add(new JLabel("Destino:"));
        formPanel.add(txtDestino);

        // Painel de Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCreate = new JButton("Inserir");
        JButton btnUpdate = new JButton("Atualizar");
        JButton btnDelete = new JButton("Excluir");
        JButton btnClear = new JButton("Limpar Campos");
        
        btnCreate.addActionListener(e -> createNavio());
        btnUpdate.addActionListener(e -> updateNavio());
        btnDelete.addActionListener(e -> deleteNavio());
        btnClear.addActionListener(e -> clearFields());

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Tonelagem", "Destino"}, 0);
        navioTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(navioTable);

        navioTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && navioTable.getSelectedRow() != -1) {
                fillFieldsFromTable();
            }
        });

        // Organização do Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    @Override
    protected void loadData() {
        tableModel.setRowCount(0); // Limpa a tabela
        try {
            List<Navio> navios = navioDAO.readAll();
            for (Navio navio : navios) {
                tableModel.addRow(new Object[]{navio.getId(), navio.getNome(), navio.getTonelagem(), navio.getDestino()});
            }
            log("Dados de Navio carregados com sucesso.");
        } catch (SQLException e) {
            log("Erro ao carregar dados de Navio: " + e.getMessage());
            showMessage("Erro de Banco de Dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNavio() {
        if (!validateFields(txtNome, txtTonelagem, txtDestino) || !validateDouble(txtTonelagem)) return;

        try {
            String nome = txtNome.getText();
            Double tonelagem = Double.parseDouble(txtTonelagem.getText());
            String destino = txtDestino.getText();

            Navio navio = new Navio(nome, tonelagem, destino);
            navioDAO.create(navio);
            
            log("Navio inserido: " + navio.toString());
            showMessage("Registro inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (SQLException e) {
            log("Erro ao inserir Navio: " + e.getMessage());
            showMessage("Erro ao inserir registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateNavio() {
        if (txtId.getText().isEmpty()) {
            showMessage("Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateFields(txtNome, txtTonelagem, txtDestino) || !validateDouble(txtTonelagem)) return;

        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            Double tonelagem = Double.parseDouble(txtTonelagem.getText());
            String destino = txtDestino.getText();

            Navio navio = new Navio(id, nome, tonelagem, destino);
            navioDAO.update(navio);

            log("Navio atualizado: " + navio.toString());
            showMessage("Registro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (SQLException e) {
            log("Erro ao atualizar Navio: " + e.getMessage());
            showMessage("Erro ao atualizar registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteNavio() {
        if (txtId.getText().isEmpty()) {
            showMessage("Selecione um registro na tabela para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                navioDAO.delete(id);

                log("Navio excluído (ID: " + id + ")");
                showMessage("Registro excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } catch (SQLException e) {
                log("Erro ao excluir Navio: " + e.getMessage());
                showMessage("Erro ao excluir registro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void fillFieldsFromTable() {
        int selectedRow = navioTable.getSelectedRow();
        if (selectedRow >= 0) {
            txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtNome.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtTonelagem.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtDestino.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtNome.setText("");
        txtTonelagem.setText("");
        txtDestino.setText("");
        navioTable.clearSelection();
    }
}
