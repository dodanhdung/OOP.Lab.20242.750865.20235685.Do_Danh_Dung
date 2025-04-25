package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private String director;
    private int length;

    // Getter
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // Constructors
    public DigitalVideoDisc(String title) {
       getTitle();
    }

    public DigitalVideoDisc(String title, String category, float cost) {
    	getTitle();
    	getCategory();
    	getCost();
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
    	getTitle();
    	getCategory();
    	getDirector();
    	getCost();
    }
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
    	getTitle();
    	getCategory();
    	getDirector();
    	getLength();
    	getCost();
    }

    public DigitalVideoDisc(int id, String title, String category, String director, int length, float cost) {
        super(id, title, category, director, length, cost);
    }


    // ✅ Mới: isMatch dùng để tìm kiếm theo tiêu đề
    public boolean isMatch(String title) {
        return getTitle() != null && getTitle().toLowerCase().contains(title.toLowerCase());
    }

    // ✅ Mới: toString để in thông tin DVD
    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + director + " - " + length + ": " + getCost() + " $";
    }
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}
