package Testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileManager extends JFrame implements ActionListener {
    private JTextField fileName1Field, fileName2Field;
    private JTextArea textArea;
    private JButton readButton, writeButton, deleteButton, renameButton, clearButton;

    public FileManager() {
        // Set up the frame
        setTitle("File Operations");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // File Operations Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("File Operations"), gbc);

        // File name 1 Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JLabel("File name 1 :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fileName1Field = new JTextField(20);
        add(fileName1Field, gbc);

        // File name 2 Label and TextField
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("File name 2 :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        fileName2Field = new JTextField(20);
        add(fileName2Field, gbc);

        // Buttons Panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));

        readButton = new JButton("READ");
        readButton.addActionListener(this);
        buttonPanel.add(readButton);

        writeButton = new JButton("WRITE");
        writeButton.addActionListener(this);
        buttonPanel.add(writeButton);

        deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        renameButton = new JButton("RENAME");
        renameButton.addActionListener(this);
        buttonPanel.add(renameButton);

        clearButton = new JButton("CLEAR");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        add(buttonPanel, gbc);

        // Text Area
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, gbc);
    }

    public void actionPerformed(ActionEvent e) {
        String fileName1 = fileName1Field.getText().trim();
        String fileName2 = fileName2Field.getText().trim();
        File file1 = new File(fileName1);


        if (e.getSource() == readButton) {
            // Read file
            if (file1.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
                    textArea.setText("");
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "File not found.");
            }
        } else if (e.getSource() == writeButton) {
            // Write to file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file1))) {
                bw.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File written successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error writing file: " + ex.getMessage());
            }
        } else if (e.getSource() == deleteButton) {
            // Delete file
            if (file1.exists() && file1.delete()) {
                JOptionPane.showMessageDialog(this, "File deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting file.");
            }
        } else if (e.getSource() == renameButton) {
            // Rename file
            File file2 = new File(fileName2);
            if (file1.exists() && !file2.exists() && file1.renameTo(file2)) {
                JOptionPane.showMessageDialog(this, "File renamed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error renaming file.");
            }
        } else if (e.getSource() == clearButton) {
            // Clear text fields and text area
            fileName1Field.setText("");
            fileName2Field.setText("");
            textArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FileManager().setVisible(true);
        });
    }
}