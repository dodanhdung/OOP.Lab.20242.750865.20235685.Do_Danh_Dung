package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.Aims; 
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List; 

public class StoreManagerScreen extends JFrame {
    private Store store;
    private JPanel centerPanel; 

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshStoreView();
                JOptionPane.showMessageDialog(null, "Store view refreshed!");
            }
        });
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookToStoreScreen(store, StoreManagerScreen.this); 
            }
        });
        smUpdateStore.add(addBook);

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompactDiscToStoreScreen(store, StoreManagerScreen.this); 
            }
        });
        smUpdateStore.add(addCD);

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new AddDigitalVideoDiscToStoreScreen(store, StoreManagerScreen.this);
            }
        });
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        centerPanel = new JPanel(); 
        centerPanel.setLayout(new GridLayout(3, 3, 2, 2)); 

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        if (mediaInStore != null) {
            for (Media media : mediaInStore) {
                MediaStore cell = new MediaStore(media, this);
                centerPanel.add(cell);
            }
        }
        return centerPanel;
    }
    public void refreshStoreView() {
        if (centerPanel == null) return;
        centerPanel.removeAll();
        ArrayList<Media> mediaInStore = store.getItemsInStore();
        if (mediaInStore != null) {
            for (Media media : mediaInStore) {
                MediaStore cell = new MediaStore(media, this);
                centerPanel.add(cell);
            }
        }
        centerPanel.revalidate();
        centerPanel.repaint();
    }


    public StoreManagerScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}