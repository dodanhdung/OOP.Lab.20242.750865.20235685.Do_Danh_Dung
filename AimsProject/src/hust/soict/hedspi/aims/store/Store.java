package hust.soict.hedspi.aims.store;

import java.util.ArrayList;
import hust.soict.hedspi.aims.media.Media;
public class Store {
	private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Added media: " + media.getTitle());
        } else {
            System.out.println("Media already exists: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("Removed media: " + media.getTitle());
        } else {
            System.out.println("Media not found: " + media.getTitle());
        }
    }

    public void printStore() {
        System.out.println("********** STORE **********");
        for (Media media : itemsInStore) {
            System.out.println(media.toString());
        }
        System.out.println("***************************");
    }
    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
             // Sử dụng equalsIgnoreCase để tìm kiếm không phân biệt hoa thường chính xác
             // Hoặc isMatch nếu muốn tìm chứa chuỗi
            if (media.getTitle() != null && media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
             // Nếu muốn tìm kiếm chứa chuỗi (không phân biệt hoa thường):
             // if (media.isMatch(title)) {
             //    return media;
             // }
        }
        return null; // Không tìm thấy
    }
}
