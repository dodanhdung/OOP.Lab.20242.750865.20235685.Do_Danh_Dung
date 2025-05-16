package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.manager.StoreManagerScreen;
import hust.soict.hedspi.aims.store.Store;

public class AimsGUI {
    public static void main(String[] args) {
        Store store = new Store();
        addSampleData(store);

        javax.swing.SwingUtilities.invokeLater(() -> {
            new StoreManagerScreen(store);
        });
    }

    private static void addSampleData(Store store) {

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(101, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(102, "Star Wars", "Sci-Fi", "George Lucas", 121, 24.95f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        CompactDisc cd1 = new CompactDisc(201, "Greatest Hits", "Pop", "Music Videos", 0, 15.99f, "Queen");
        cd1.addTrack(new Track("Bohemian Rhapsody", 354));
        cd1.addTrack(new Track("Another One Bites the Dust", 214));
        store.addMedia(cd1);
        Book book1 = new Book(301, "The Lord of the Rings", "Fantasy", 25.50f);
        book1.addAuthor("J.R.R. Tolkien");
        store.addMedia(book1);
    }
}