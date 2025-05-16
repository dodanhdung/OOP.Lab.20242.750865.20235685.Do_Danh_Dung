package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField idField;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField directorField;
    private JTextField lengthField;
    private JTextField costField;

    public AddDigitalVideoDiscToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen);
        setTitle("Add Digital Video Disc to Store");
        setVisible(true);
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0; 

        // ID
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("ID:"), gbc);
        idField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(idField, gbc);

        // Title
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(titleField, gbc);

        // Category
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Category:"), gbc);
        categoryField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(categoryField, gbc);

        // Director
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Director:"), gbc);
        directorField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(directorField, gbc);

        // Length
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Length (minutes):"), gbc);
        lengthField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(lengthField, gbc);

        // Cost
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Cost:"), gbc);
        costField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(costField, gbc);


        JButton addBtn = new JButton("Add DVD");
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addBtn, gbc);

        addBtn.addActionListener(e -> {
            try {
                if (idField.getText().trim().isEmpty() || titleField.getText().trim().isEmpty() ||
                    directorField.getText().trim().isEmpty() || !validateInt(idField.getText()) ||
                    !validateInt(lengthField.getText()) || !validateFloat(costField.getText())) {
                    JOptionPane.showMessageDialog(this, "ID, Title, Director, Length and Cost are required. ID & Length must be integers, Cost must be number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String category = categoryField.getText();
                String director = directorField.getText();
                int length = Integer.parseInt(lengthField.getText());
                float cost = Float.parseFloat(costField.getText());

                if (length <= 0) {
                    JOptionPane.showMessageDialog(this, "Length must be a positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (store.findById(id) != null) {
                     JOptionPane.showMessageDialog(this, "Error: Media with ID " + id + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, director, length, cost);
                store.addMedia(dvd);
                JOptionPane.showMessageDialog(this, "DVD added successfully!");
                this.dispose();
                parentScreen.refreshStoreView();
                parentScreen.setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for ID, Length or Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding DVD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        return panel;
    }
}