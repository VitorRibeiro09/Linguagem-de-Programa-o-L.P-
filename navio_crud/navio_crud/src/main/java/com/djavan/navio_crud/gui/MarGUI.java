package com.djavan.navio_crud.gui;

import com.djavan.navio_crud.dao.MarDAO;
import com.djavan.navio_crud.model.Mar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MarGUI extends BaseGUI {
    private MarDAO marDAO;
    private DefaultTableModel tableModel;
    private JTable marTable;

    private JTextField txtProfundidade;
    private JTextField txtCor;
    private JTextField txtEstado;
    private JTextField txtId;

    public MarGUI() {
        super("CRUD Mar - Djavan");
        marDAO = new MarDAO();
        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtProfundidade = new JTextField();
        txtCor = new JTextField();
        txtEstado = new JTextField();

        txtProfundidade.setName("Profundidade"); // Para validação

        formPanel.add(new JLabel("ID (Auto):"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Profundidade (Double):"));
        formPanel.add(txtProfundidade);
        formPanel.add(new JLabel("Cor:"));
        formPanel.add(txtCor);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(txtEstado);

        // Painel de Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCreate = new JButton("Inserir");
        JButton btnUpdate = new JButton("Atualizar");
        JButton btnDelete = new JButton("Excluir");
        JButton btnClear = new JButton("Limpar Campos");
        
        btnCreate.addActionListener(e -> createMar());
        btnUpdate.addActionListener(e -> updateMar());
        btnDelete.addActionListener(e -> deleteMar());
        btnClear.addActionListener(e -> clearFields());

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Profundidade", "Cor", "Estado"}, 0);
        marTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(marTable);

        marTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && marTable.getSelectedRow() != -1) {
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
            List<Mar> mares = marDAO.readAll();
            for (Mar mar : mares) {
                tableModel.addRow(new Object[]{mar.getId(), mar.getProfundidade(), mar.getCor(), mar.getEstado()});
            }
            log("Dados de Mar carregados com sucesso.");
        } catch (SQLException e) {
            log("Erro ao carregar dados de Mar: " + e.getMessage());
            showMessage("Erro de Banco de Dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createMar() {
        if (!validateFields(txtProfundidade, txtCor, txtEstado) || !validateDouble(txtProfundidade)) return;

        try {
            Double profundidade = Double.parseDouble(txtProfundidade.getText());
            String cor = txtCor.getText();
            String estado = txtEstado.getText();

            Mar mar = new Mar(profundidade, cor, estado);
            marDAO.create(mar);
            
            log("Mar inserido: " + mar.toString());
            showMessage("Registro inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (SQLException e) {
            log("Erro ao inserir Mar: " + e.getMessage());
            showMessage("Erro ao inserir registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMar() {
        if (txtId.getText().isEmpty()) {
            showMessage("Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateFields(txtProfundidade, txtCor, txtEstado) || !validateDouble(txtProfundidade)) return;

        try {
            int id = Integer.parseInt(txtId.getText());
            Double profundidade = Double.parseDouble(txtProfundidade.getText());
            String cor = txtCor.getText();
            String estado = txtEstado.getText();

            Mar mar = new Mar(id, profundidade, cor, estado);
            marDAO.update(mar);

            log("Mar atualizado: " + mar.toString());
            showMessage("Registro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (SQLException e) {
            log("Erro ao atualizar Mar: " + e.getMessage());
            showMessage("Erro ao atualizar registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMar() {
        if (txtId.getText().isEmpty()) {
            showMessage("Selecione um registro na tabela para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                marDAO.delete(id);

                log("Mar excluído (ID: " + id + ")");
                showMessage("Registro excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } catch (SQLException e) {
                log("Erro ao excluir Mar: " + e.getMessage());
                showMessage("Erro ao excluir registro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void fillFieldsFromTable() {
        int selectedRow = marTable.getSelectedRow();
        if (selectedRow >= 0) {
            txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtProfundidade.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtCor.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtEstado.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtProfundidade.setText("");
        txtCor.setText("");
        txtEstado.setText("");
        marTable.clearSelection();
    }
}
