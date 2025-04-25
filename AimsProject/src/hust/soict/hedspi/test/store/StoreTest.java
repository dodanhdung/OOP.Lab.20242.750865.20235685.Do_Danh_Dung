package hust.soict.hedspi.test.store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Matrix", "Action", "Wachowski", 136, 21.5f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Inception", "Sci-fi", "Nolan", 148, 22.0f);

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.printStore();

        store.removeMedia(dvd1);
        store.printStore();
    }
}
