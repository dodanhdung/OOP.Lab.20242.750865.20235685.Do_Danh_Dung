package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected StoreManagerScreen parentScreen; 
    public AddItemToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        this.store = store;
        this.parentScreen = parentScreen; 
        this.setLayout(new BorderLayout());
        this.setSize(600, 400); 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.setLocationRelativeTo(parentScreen); 

        this.setJMenuBar(createMenuBar());
        this.add(createInputPanel(), BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            this.dispose(); 
            parentScreen.setVisible(true); 
            parentScreen.refreshStoreView(); 
        });

        JMenu updateStore = new JMenu("Update Store");

        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> {
            this.dispose();
            new AddBookToStoreScreen(store, parentScreen);
        });

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> {
            this.dispose();
            new AddCompactDiscToStoreScreen(store, parentScreen);
        });

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> {
            this.dispose();
            new AddDigitalVideoDiscToStoreScreen(store, parentScreen);
        });

        updateStore.add(addBook);
        updateStore.add(addCD);
        updateStore.add(addDVD);

        menu.add(viewStoreItem);
        menu.add(updateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        return menuBar;
    }

    protected abstract JPanel createInputPanel();
    protected boolean validateFloat(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    protected boolean validateInt(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}