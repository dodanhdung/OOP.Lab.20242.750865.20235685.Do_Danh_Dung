package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;
import java.util.Arrays;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField idField; 
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField authorsField; 

    public AddBookToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen);
        setTitle("Add Book to Store"); 
        setVisible(true);
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID:"), gbc);
        idField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(idField, gbc);

        // Title
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(titleField, gbc);

        // Category
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Category:"), gbc);
        categoryField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(categoryField, gbc);

        // Cost
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Cost:"), gbc);
        costField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(costField, gbc);

        // Authors
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Authors (comma-separated):"), gbc);
        authorsField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(authorsField, gbc);


        JButton addBtn = new JButton("Add Book");
        addBtn.addActionListener(e -> {
            try {
                if (idField.getText().trim().isEmpty() || titleField.getText().trim().isEmpty() ||
                    !validateInt(idField.getText()) || !validateFloat(costField.getText())) {
                    JOptionPane.showMessageDialog(this, "ID, Title, and Cost are required. ID must be integer, Cost must be number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String category = categoryField.getText();
                float cost = Float.parseFloat(costField.getText());
                String authorsInput = authorsField.getText();
                if (store.findById(id) != null) {
                     JOptionPane.showMessageDialog(this, "Error: Media with ID " + id + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }


                Book book = new Book(id, title, category, cost);
                if (authorsInput != null && !authorsInput.trim().isEmpty()) {
                    Arrays.stream(authorsInput.split(","))
                          .map(String::trim)
                          .filter(author -> !author.isEmpty())
                          .forEach(book::addAuthor);
                }

                store.addMedia(book);
                JOptionPane.showMessageDialog(this, "Book added successfully!");
                this.dispose(); 
                parentScreen.refreshStoreView(); 
                parentScreen.setVisible(true); 

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for ID or Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addBtn, gbc);

        return panel;
    }
}