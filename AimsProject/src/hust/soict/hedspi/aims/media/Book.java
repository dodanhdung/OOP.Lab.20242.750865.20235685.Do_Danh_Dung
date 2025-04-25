package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
public class Book extends Media {
   
    private ArrayList<String> authors = new ArrayList<>();

    // Constructor
    public Book() {
        super();
    }

    public Book(int id, String title, String category, float cost) {
    	super(id, title, category, cost);
    	this.authors = new ArrayList<>();
    }

    // Getters and Setters (chỉ cho các thuộc tính cần thiết)
    
    // addAuthor – tránh trùng lặp
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }

    // removeAuthor – chỉ xoá nếu tồn tại
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        }
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - " + authors + ": " + getCost() + "$";
    }

}
