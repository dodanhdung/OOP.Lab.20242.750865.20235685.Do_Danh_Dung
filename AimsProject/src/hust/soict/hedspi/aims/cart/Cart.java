package hust.soict.hedspi.aims.cart;
import java.util.Collections;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.store.Store; // Import Store
import javafx.collections.FXCollections; // Thêm import này
import javafx.collections.ObservableList; // Thêm import này

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    // Thay ArrayList bằng ObservableList
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    private Store store; // Thêm thuộc tính Store

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public Store getStore() { // Thêm getter cho Store
        return store;
    }

    public void setStore(Store store) { // Thêm setter cho Store
        this.store = store;
    }

    public void addMedia(Media media) throws LimitExceededException {
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new LimitExceededException("The cart is full. Maximum items allowed is " + MAX_NUMBERS_ORDERED);
        }
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("Media added to cart: " + media.getTitle());
        } else {
            System.out.println("Media already exists in cart: " + media.getTitle());
        }
    }
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Media removed from cart: " + media.getTitle());
        } else {
            System.out.println("Media not found in cart: " + media.getTitle());
        }
    }
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }
    public void printCart() {
        System.out.println("********** CART **********");
        for (Media media : itemsOrdered) {
            System.out.println(media.toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("**************************");
    }

    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media.getTitle());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No media found with ID: " + id);
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found: " + media.getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
    }
    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart sorted by title then cost.");
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart sorted by cost then title.");
    }
}