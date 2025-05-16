package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
public class Book extends Media {
   
    private ArrayList<String> authors = new ArrayList<>();
    public Book() {
        super();
    }

    public Book(int id, String title, String category, float cost) {
    	super(id, title, category, cost);
    	this.authors = new ArrayList<>();
    }
    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        }
    }
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
