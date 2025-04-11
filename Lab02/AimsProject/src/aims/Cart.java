package aims;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	  private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED]; 
	  private int qtyOrdered = 0;
	// Thêm DVD vào giỏ
	    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
	        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
	            System.out.println("The cart is full.");
	        } else {
	            itemsOrdered[qtyOrdered] = disc;
	            qtyOrdered++;
	            System.out.println("Added.");
	        }
	    }
	    // Xóa DVD khỏi giỏ
	    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
	        boolean found = false;
	        for (int i = 0; i < qtyOrdered; i++) {
	            if (itemsOrdered[i].equals(disc)) {
	                for (int j = i; j < qtyOrdered - 1; j++) {
	                    itemsOrdered[j] = itemsOrdered[j + 1];
	                }
	                itemsOrdered[qtyOrdered - 1] = null;
	                qtyOrdered--;
	                System.out.println("Removed.");
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            System.out.println("The disc \"" + disc.getTitle() + "\" was not found in the cart.");
	        }
	    }

	    // Tính tổng tiền của giỏ hàng
	    public float totalCost() {
	        float total = 0;
	        for (int i = 0; i < qtyOrdered; i++) {
	            total += itemsOrdered[i].getCost();
	        }
	        return total;
	    }


}
