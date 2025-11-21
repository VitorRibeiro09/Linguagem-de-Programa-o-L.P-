package com.djavan.navio_crud.gui;

import javax.swing.*;
import java.awt.*;

public abstract class BaseGUI extends JFrame {
    protected JTextArea logArea;
    protected JPanel mainPanel;

    public BaseGUI(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centraliza a janela

        mainPanel = new JPanel(new BorderLayout());
        
        // Área de log/mensagens
        logArea = new JTextArea(5, 20);
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        mainPanel.add(logScrollPane, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    protected void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    protected void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    protected boolean validateFields(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                showMessage("Todos os campos são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    protected boolean validateDouble(JTextField field) {
        try {
            Double.parseDouble(field.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            showMessage("O campo '" + field.getName() + "' deve ser um número válido.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    protected abstract void loadData();
}
