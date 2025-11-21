package com.djavan.navio_crud.gui;

import com.djavan.navio_crud.dao.PessoaDAO;
import com.djavan.navio_crud.model.Pessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PessoaGUI extends BaseGUI {
    private PessoaDAO pessoaDAO;
    private DefaultTableModel tableModel;
    private JTable pessoaTable;

    private JTextField txtNome;
    private JTextField txtEmocao;
    private JTextField txtLocalizacao;
    private JTextField txtId;

    public PessoaGUI() {
        super("CRUD Pessoa - Djavan");
        pessoaDAO = new PessoaDAO();
        initializeComponents();
        loadData();
    }

    private void initializeComponents() {
        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtNome = new JTextField();
        txtEmocao = new JTextField();
        txtLocalizacao = new JTextField();

        formPanel.add(new JLabel("ID (Auto):"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(txtNome);
        formPanel.add(new JLabel("Emoção:"));
        formPanel.add(txtEmocao);
        formPanel.add(new JLabel("Localização:"));
        formPanel.add(txtLocalizacao);

        // Painel de Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCreate = new JButton("Inserir");
        JButton btnUpdate = new JButton("Atualizar");
        JButton btnDelete = new JButton("Excluir");
        JButton btnClear = new JButton("Limpar Campos");
        
        btnCreate.addActionListener(e -> createPessoa());
        btnUpdate.addActionListener(e -> updatePessoa());
        btnDelete.addActionListener(e -> deletePessoa());
        btnClear.addActionListener(e -> clearFields());

        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Tabela
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Emoção", "Localização"}, 0);
        pessoaTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(pessoaTable);

        pessoaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && pessoaTable.getSelectedRow() != -1) {
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
            List<Pessoa> pessoas = pessoaDAO.readAll();
            for (Pessoa pessoa : pessoas) {
                tableModel.addRow(new Object[]{pessoa.getId(), pessoa.getNome(), pessoa.getEmocao(), pessoa.getLocalizacao()});
            }
            log("Dados de Pessoa carregados com sucesso.");
        } catch (SQLException e) {
            log("Erro ao carregar dados de Pessoa: " + e.getMessage());
            showMessage("Erro de Banco de Dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createPessoa() {
        if (!validateFields(txtNome, txtEmocao, txtLocalizacao)) return;

        try {
            String nome = txtNome.getText();
            String emocao = txtEmocao.getText();
            String localizacao = txtLocalizacao.getText();

            Pessoa pessoa = new Pessoa(nome, emocao, localizacao);
            pessoaDAO.create(pessoa);
            
            log("Pessoa inserida: " + pessoa.toString());
            showMessage("Registro inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (SQLException e) {
            log("Erro ao inserir Pessoa: " + e.getMessage());
            showMessage("Erro ao inserir registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePessoa() {
        if (txtId.getText().isEmpty()) {
            showMessage("Selecione um registro na tabela para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validateFields(txtNome, txtEmocao, txtLocalizacao)) return;

        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String emocao = txtEmocao.getText();
            String localizacao = txtLocalizacao.getText();

            Pessoa pessoa = new Pessoa(id, nome, emocao, localizacao);
            pessoaDAO.update(pessoa);

            log("Pessoa atualizada: " + pessoa.toString());
            showMessage("Registro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadData();
        } catch (SQLException e) {
            log("Erro ao atualizar Pessoa: " + e.getMessage());
            showMessage("Erro ao atualizar registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePessoa() {
        if (txtId.getText().isEmpty()) {
            showMessage("Selecione um registro na tabela para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este registro?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                pessoaDAO.delete(id);

                log("Pessoa excluída (ID: " + id + ")");
                showMessage("Registro excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                loadData();
            } catch (SQLException e) {
                log("Erro ao excluir Pessoa: " + e.getMessage());
                showMessage("Erro ao excluir registro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void fillFieldsFromTable() {
        int selectedRow = pessoaTable.getSelectedRow();
        if (selectedRow >= 0) {
            txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtNome.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtEmocao.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtLocalizacao.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtNome.setText("");
        txtEmocao.setText("");
        txtLocalizacao.setText("");
        pessoaTable.clearSelection();
    }
}
