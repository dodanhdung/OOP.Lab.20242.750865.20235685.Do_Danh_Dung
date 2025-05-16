package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;
import java.util.ArrayList;
import java.util.List;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField idField;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField artistField;
    private JTextField directorField; 
    private List<Track> tracksToAdd;

    public AddCompactDiscToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen);
        tracksToAdd = new ArrayList<>();
        setTitle("Add Compact Disc to Store");
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

        // Cost
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Cost:"), gbc);
        costField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(costField, gbc);

        // Artist
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Artist:"), gbc);
        artistField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(artistField, gbc);

        // Director 
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Director (Optional):"), gbc);
        directorField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = y++; panel.add(directorField, gbc);

        // Button to Add Tracks
        JButton btnAddTrack = new JButton("Add Track(s)");
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAddTrack, gbc);
        y++;

        btnAddTrack.addActionListener(e -> addTrackDialog());


        JButton addBtn = new JButton("Add CD");
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addBtn, gbc);

        addBtn.addActionListener(e -> {
            try {
                if (idField.getText().trim().isEmpty() || titleField.getText().trim().isEmpty() ||
                    artistField.getText().trim().isEmpty() || !validateInt(idField.getText()) ||
                    !validateFloat(costField.getText())) {
                    JOptionPane.showMessageDialog(this, "ID, Title, Artist, and Cost are required. ID must be integer, Cost must be number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String category = categoryField.getText();
                float cost = Float.parseFloat(costField.getText());
                String artist = artistField.getText();
                String director = directorField.getText().trim(); // Optional

                if (store.findById(id) != null) {
                     JOptionPane.showMessageDialog(this, "Error: Media with ID " + id + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                CompactDisc cd = new CompactDisc(id, title, category, director.isEmpty() ? null : director, 0, cost, artist);

                for (Track track : tracksToAdd) {
                    cd.addTrack(track);
                }

                store.addMedia(cd);
                JOptionPane.showMessageDialog(this, "Compact Disc added successfully!");
                this.dispose();
                parentScreen.refreshStoreView();
                parentScreen.setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for ID or Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding CD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); 
            }
        });

        return panel;
    }

    private void addTrackDialog() {
        String trackTitle = JOptionPane.showInputDialog(this, "Enter track title:", "Add Track", JOptionPane.PLAIN_MESSAGE);
        if (trackTitle != null && !trackTitle.trim().isEmpty()) {
            String trackLengthStr = JOptionPane.showInputDialog(this, "Enter track length (seconds):", "Add Track", JOptionPane.PLAIN_MESSAGE);
            if (trackLengthStr != null) {
                try {
                    int trackLength = Integer.parseInt(trackLengthStr);
                    if (trackLength <= 0) {
                         JOptionPane.showMessageDialog(this, "Track length must be a positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                         return;
                    }
                    tracksToAdd.add(new Track(trackTitle, trackLength));
                    JOptionPane.showMessageDialog(this, "Track '" + trackTitle + "' ready to be added with CD.", "Track Added", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid track length. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}