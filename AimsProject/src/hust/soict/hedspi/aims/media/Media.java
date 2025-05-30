package hust.soict.hedspi.aims.media;
import java.util.Comparator;

public abstract class Media implements Comparable<Media> { // Thêm implements Comparable<Media>

    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media() {
        super();
    }
    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    public Media(String title) {
        this.title = title;
    }
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    // Getters và Setters (giữ nguyên)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost < 0) {
            System.err.println("Warning: Cost cannot be negative. Setting cost to 0.");
            this.cost = 0;
        } else {
            this.cost = cost;
        }
    }

    public boolean isMatch(String title) {
        if (this.title == null || title == null) {
            return false;
        }
        return this.title.toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Media)) {
            return false;
        }
        Media other = (Media) obj;
        boolean titlesEqual = (this.title == null && other.title == null) || (this.title != null && this.title.equals(other.title));
        return titlesEqual && Float.compare(this.cost, other.cost) == 0;
    }
    @Override
    public int compareTo(Media other) {
        if (other == null) {
            return 1;
        }

        int titleCompare;
        if (this.title == null && other.title == null) {
            titleCompare = 0;
        } else if (this.title == null) {
            titleCompare = -1; // null titles come before non-null titles
        } else if (other.title == null) {
            titleCompare = 1;
        } else {
            titleCompare = this.title.compareToIgnoreCase(other.title);
        }

        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(other.cost, this.cost); // other.cost vs this.cost for descending
    }

    private static class MediaComparatorByTitleCost implements Comparator<Media> {
        @Override
        public int compare(Media media1, Media media2) {
            if (media1 == null && media2 == null) return 0;
            if (media1 == null) return -1;
            if (media2 == null) return 1;

            int titleComparison;
            if (media1.getTitle() == null && media2.getTitle() == null) titleComparison = 0;
            else if (media1.getTitle() == null) titleComparison = -1;
            else if (media2.getTitle() == null) titleComparison = 1;
            else titleComparison = media1.getTitle().compareToIgnoreCase(media2.getTitle());

            if (titleComparison != 0) {
                return titleComparison;
            }
            return Float.compare(media2.getCost(), media1.getCost());
        }
    }

    private static class MediaComparatorByCostTitle implements Comparator<Media> {
        @Override
        public int compare(Media media1, Media media2) {
            if (media1 == null && media2 == null) return 0;
            if (media1 == null) return -1; // Hoặc 1 tùy theo logic nulls first/last
            if (media2 == null) return 1;

            int costComparison = Float.compare(media2.getCost(), media1.getCost()); // Giảm dần theo cost
            if (costComparison != 0) {
                return costComparison;
            }

            if (media1.getTitle() == null && media2.getTitle() == null) return 0;
            if (media1.getTitle() == null) return -1;
            if (media2.getTitle() == null) return 1;
            return media1.getTitle().compareToIgnoreCase(media2.getTitle());
        }
    }
}