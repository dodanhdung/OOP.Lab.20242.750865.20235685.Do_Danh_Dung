package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*; 
import hust.soict.hedspi.aims.store.Store;

import java.util.List; 
import java.util.Scanner;

public class Aims {
    public static void showMainMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void cartMenu() {
        System.out.println("\nOptions: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }
    //main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        Cart cart = new Cart();

        //thêm mẫu
        addSampleData(store);


        int option;
        do {
            showMainMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: // View Store
                    viewStore(scanner, store, cart);
                    break;
                case 2: // Update store
                    updateStore(scanner, store);
                    break;
                case 3: // See current cart
                    seeCurrentCart(scanner, cart);
                    break;
                case 0:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid option. Pls choose again.");
            }

        } while (option != 0);

        scanner.close();
    }

    //thêm dữ liệu mẫu
    public static void addSampleData(Store store) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addMedia(dvd1);

        CompactDisc cd1 = new CompactDisc(2, "Greatest Hits", "Pop", "abcabc", 20, 69.69f, "Queen");
        Track track1 = new Track("Bohemian Rhapsody", 354);
        Track track2 = new Track("Another One Bites the Dust", 214);
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        store.addMedia(cd1);


        Book book1 = new Book(3, "The Lord of the Rings", "Fantasy", 25.50f);
        book1.addAuthor("J.R.R. Tolkien");
        store.addMedia(book1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(4, "Star Wars", "Science Fiction", "George Lucas", 121, 24.95f);
        store.addMedia(dvd2);

         CompactDisc cd2 = new CompactDisc(5, "Thriller", "Pop", "ihiawdniawd", 69, 203029301.3f, "Michael Jackson");
         cd2.addTrack(new Track("Thriller", 357));
         cd2.addTrack(new Track("Billie Jean", 294));
         store.addMedia(cd2);
    }


    // Logic cho View Store 
    public static void viewStore(Scanner scanner, Store store, Cart cart) {
        store.printStore();
        int storeOption;
        do {
            storeMenu();
            storeOption = scanner.nextInt();
            scanner.nextLine(); 

            switch (storeOption) {
                case 1: // media details
                    System.out.print("Enter the title: ");
                    String titleToView = scanner.nextLine();
                    Media mediaToView = store.findMediaByTitle(titleToView);
                    if (mediaToView != null) {
                        System.out.println("Details: " + mediaToView.toString());
                        seeMediaDetails(scanner, mediaToView, cart);
                    } else {
                        System.out.println("Not found in store");
                    }
                    break;
                case 2: // Add media to cart
                    System.out.print("Enter the title: ");
                    String titleToAdd = scanner.nextLine();
                    Media mediaToAdd = store.findMediaByTitle(titleToAdd);
                    if (mediaToAdd != null) {
                        cart.addMedia(mediaToAdd);
                    } else {
                        System.out.println("Not found in store");
                    }
                    break;
                case 3: // Play media
                     System.out.print("Enter the title: ");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = store.findMediaByTitle(titleToPlay);
                    playMedia(mediaToPlay); 
                    break;
                case 4: // See current cart
                     seeCurrentCart(scanner, cart); 
                    break;
                case 0: 
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Pls choose again.");
            }
        } while (storeOption != 0);
    }

    // Logic cho Media Details Menu 
    public static void seeMediaDetails(Scanner scanner, Media media, Cart cart) {
        int detailOption;
        do {
            mediaDetailsMenu();
            detailOption = scanner.nextInt();
            scanner.nextLine(); 
            switch (detailOption) {
                case 1: // Add to cart
                    cart.addMedia(media);
                    detailOption = 0; 
                    break;
                case 2: // Play
                    playMedia(media); 
                    break;
                case 0: // Back
                    System.out.println("Returning to store menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (detailOption != 0);
    }


    // Logic cho Update Store 
    public static void updateStore(Scanner scanner, Store store) {
        System.out.println("\nUpdate Store Options:");
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.println("0. Back");
        System.out.print("Choose option: ");

        int updateOption = scanner.nextInt();
        scanner.nextLine();

        if (updateOption == 1) {
            System.out.println("Select media type to add:");
            System.out.println("1. Book");
            System.out.println("2. Digital Video Disc (DVD)");
            System.out.println("3. Compact Disc (CD)");
            System.out.print("Choose type: ");
            int type = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            System.out.print("Enter cost: ");
            float cost = scanner.nextFloat();
            scanner.nextLine(); 

            Media newMedia = null;
            switch (type) {
                case 1: // Book
                     System.out.print("Enter author(s): ");
                     String authorsInput = scanner.nextLine();
                     Book newBook = new Book(id, title, category, cost);
                     String[] authors = authorsInput.split(",");
                     for(String author : authors) {
                         newBook.addAuthor(author.trim());
                     }
                     newMedia = newBook;
                    break;
                case 2: // DVD
                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter length (m): ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); 
                    newMedia = new DigitalVideoDisc(id, title, category, director, length, cost);
                    break;
                 case 3: // CD
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter director: ");
                    String cdDirector = scanner.nextLine();
                    System.out.print("Enter length: ");
                    int cdLength = scanner.nextInt();
                    scanner.nextLine();
                    CompactDisc newCD = new CompactDisc(id, title, category, cdDirector, cdLength, cost, artist);
                    // Add tracks
                    String addMoreTracks;
                    do {
                        System.out.print("Enter track title: ");
                        String trackTitle = scanner.nextLine();
                        System.out.print("Enter track length (s): ");
                        int trackLength = scanner.nextInt();
                        scanner.nextLine(); 
                        newCD.addTrack(new Track(trackTitle, trackLength));
                        System.out.print("Add another track? (yes/no): ");
                        addMoreTracks = scanner.nextLine();
                    } while (addMoreTracks.equalsIgnoreCase("yes"));
                     newMedia = newCD;
                    break;
                default:
                    System.out.println("Invalid media type.");
                    break;
            }

            if (newMedia != null) {
                store.addMedia(newMedia);
            }

        } else if (updateOption == 2) {
            // Remove Media
            System.out.print("Enter title of media: ");
            String titleToRemove = scanner.nextLine();
            Media mediaToRemove = store.findMediaByTitle(titleToRemove);
            if (mediaToRemove != null) {
                store.removeMedia(mediaToRemove);
            } else {
                System.out.println("Media with title '" + titleToRemove + "' not found!");
            }
        } else if (updateOption == 0) {
             System.out.println("Returning to main menu...");
        }
        else {
            System.out.println("Invalid update option.");
        }
    }


    // Logic cho See Current Cart
    public static void seeCurrentCart(Scanner scanner, Cart cart) {
        cart.printCart();
        int cartOption;
        do {
            cartMenu();
            cartOption = scanner.nextInt();
            scanner.nextLine(); 

            switch (cartOption) {
                case 1: // Filter media
                    System.out.println("Filter options:");
                    System.out.println("1. Filter by ID");
                    System.out.println("2. Filter by Title");
                    System.out.print("Choose filter type: ");
                    int filterType = scanner.nextInt();
                    scanner.nextLine();
                    if (filterType == 1) {
                        System.out.print("Enter ID to filter: ");
                        int idFilter = scanner.nextInt();
                        scanner.nextLine();
                        cart.searchById(idFilter); 
                    } else if (filterType == 2) {
                        System.out.print("Enter Title to filter: ");
                        String titleFilter = scanner.nextLine();
                        cart.searchByTitle(titleFilter);
                    } else {
                        System.out.println("Invalid filter type.");
                    }
                    break;
                case 2: // Sort media
                     System.out.println("Sort options:");
                     System.out.println("1. Sort by Title then Cost");
                     System.out.println("2. Sort by Cost then Title");
                     System.out.print("Choose sort type: ");
                     int sortType = scanner.nextInt();
                     scanner.nextLine();
                     if (sortType == 1) {
                         cart.sortByTitleCost(); 
                     } else if (sortType == 2) {
                         cart.sortByCostTitle(); 
                     } else {
                         System.out.println("Invalid sort type.");
                     }
                    break;
                case 3: // Remove media
                     System.out.print("Enter the title: ");
                     String titleToRemove = scanner.nextLine();
                     Media mediaToRemove = null;
                     List<Media> items = cart.getItemsOrdered(); 
                     for (Media m : items) {
                         if (m.getTitle() != null && m.getTitle().equalsIgnoreCase(titleToRemove)) {
                             mediaToRemove = m;
                             break;
                         }
                     }

                     if (mediaToRemove != null) {
                         cart.removeMedia(mediaToRemove);
                     } else {
                         System.out.println("Not found in cart.");
                     }
                    break;
                case 4: // Play a media
                    System.out.print("Enter the title : ");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = null;
                     List<Media> cartItems = cart.getItemsOrdered(); // Lấy danh sách items từ cart
                     for (Media m : cartItems) {
                         if (m.getTitle() != null && m.getTitle().equalsIgnoreCase(titleToPlay)) {
                             mediaToPlay = m;
                             break;
                         }
                     }
                    playMedia(mediaToPlay);
                    break;
                case 5: // Place order
                    if (cart.getItemsOrdered().isEmpty()){
                        System.out.println("Cart is empty. Cannot place order.");
                    } else {
                        System.out.println("Order placed successfully! Total cost: " + String.format("%.2f", cart.totalCost()) + " $");
                        System.out.println("Cart has been cleared."); 
                        cartOption = 0; 
                    }
                    break;
                case 0: // Back
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (cartOption != 0);
    }

     // Hàm chung để play media
    public static void playMedia(Media media) {
         if (media != null) {
             if (media instanceof Playable) {
                 ((Playable) media).play();
             } else {
                 System.out.println("Cannot play this type of media: " );
             }
         } else {
              System.out.println("Media not found.");
         }
    }
}