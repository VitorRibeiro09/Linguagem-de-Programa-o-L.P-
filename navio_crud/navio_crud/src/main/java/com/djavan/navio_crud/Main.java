package com.djavan.navio_crud;

import com.djavan.navio_crud.gui.MainGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Garante que a GUI seja executada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}
