package hust.soict.hedspi.aims;
import javafx.scene.control.Alert;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class Aims {
    // ... (Phần code console giữ nguyên nếu bạn vẫn muốn dùng) ...
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
    public static void main(String[] args) { // Bỏ throws LimitExceededException nếu bạn muốn xử lý cục bộ
        // Nếu muốn chạy GUI, hãy gọi Application.launch(AimsApplication.class, args);
        // Nếu muốn chạy console, giữ nguyên logic hiện tại.
        // Ví dụ: Chạy GUI
        Application.launch(AimsApplication.class, args);


        // Hoặc chạy console (bỏ/comment dòng trên nếu chạy console)
        /*
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        Cart cart = new Cart();
        addSampleData(store); // Phương thức này cần được định nghĩa hoặc lấy từ AimsApplication

        int option;
        do {
            showMainMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            try { // Bọc các hành động có thể ném Exception
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
            } catch (LimitExceededException e) {
                System.err.println("Error: " + e.getMessage());
            }

        } while (option != 0);
        scanner.close();
        */
    }

    // Phương thức này có thể dùng chung cho cả console và GUI nếu cần
    public static void addSampleDataToStore(Store store) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        store.addMedia(dvd1);

        CompactDisc cd1 = new CompactDisc(2, "Greatest Hits", "Pop", "abcabc", 0, 69.69f, "Queen"); // Length CD sẽ được tính từ track
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

        CompactDisc cd2 = new CompactDisc(5, "Thriller", "Pop", "ihiawdniawd",0 , 20.30f, "Michael Jackson");
        cd2.addTrack(new Track("Thriller", 357));
        cd2.addTrack(new Track("Billie Jean", 294));
        store.addMedia(cd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(6, "Aladdin", "Animation", "John Musker", 90, 18.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(7, "The Dark Knight", "Action", "Christopher Nolan", 152, 29.99f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc(8, "Inception", "Science Fiction", "Christopher Nolan", 148, 22.50f);
        store.addMedia(dvd3);
        store.addMedia(dvd4);
        store.addMedia(dvd5);
    }


    // Logic cho View Store console
    public static void viewStore (Scanner scanner, Store store, Cart cart) throws LimitExceededException {
        store.printStore();
        int storeOption;
        do {
            storeMenu();
            storeOption = scanner.nextInt();
            scanner.nextLine();

            switch (storeOption) {
                case 1:
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
                case 2:
                    System.out.print("Enter the title: ");
                    String titleToAdd = scanner.nextLine();
                    Media mediaToAdd = store.findMediaByTitle(titleToAdd);
                    if (mediaToAdd != null) {
                        cart.addMedia(mediaToAdd) ; // Có thể ném LimitExceededException
                    } else {
                        System.out.println("Not found in store");
                    }
                    break;
                case 3:
                    System.out.print("Enter the title: ");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = store.findMediaByTitle(titleToPlay);
                    playMediaConsole(mediaToPlay);
                    break;
                case 4:
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

    public static void seeMediaDetails(Scanner scanner, Media media, Cart cart) throws LimitExceededException {
        int detailOption;
        do {
            mediaDetailsMenu();
            detailOption = scanner.nextInt();
            scanner.nextLine();
            switch (detailOption) {
                case 1:
                    cart.addMedia(media); // Có thể ném LimitExceededException
                    System.out.println(media.getTitle() + " added to cart.");
                    detailOption = 0;
                    break;
                case 2:
                    playMediaConsole(media);
                    break;
                case 0:
                    System.out.println("Returning to store menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (detailOption != 0);
    }

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
                case 1:
                    System.out.print("Enter author(s) (comma-separated): ");
                    String authorsInput = scanner.nextLine();
                    Book newBook = new Book(id, title, category, cost);
                    String[] authors = authorsInput.split(",");
                    for(String author : authors) {
                        newBook.addAuthor(author.trim());
                    }
                    newMedia = newBook;
                    break;
                case 2:
                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter length (minutes): ");
                    int length = scanner.nextInt();
                    scanner.nextLine();
                    newMedia = new DigitalVideoDisc(id, title, category, director, length, cost);
                    break;
                case 3:
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter CD director (if any, else leave blank): ");
                    String cdDirector = scanner.nextLine();
                    // CD length is sum of tracks, so initial length can be 0 for the CD object itself
                    CompactDisc newCD = new CompactDisc(id, title, category, cdDirector, 0, cost, artist);
                    String addMoreTracks;
                    do {
                        System.out.print("Enter track title: ");
                        String trackTitle = scanner.nextLine();
                        System.out.print("Enter track length (seconds): ");
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
                System.out.println(newMedia.getTitle() + " added to store.");
            }

        } else if (updateOption == 2) {
            System.out.print("Enter title of media to remove: ");
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

    public static void seeCurrentCart(Scanner scanner, Cart cart) {
        cart.printCart(); // In ra nội dung giỏ hàng hiện tại
        int cartOption;
        do {
            cartMenu(); // Hiển thị menu cho giỏ hàng
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
                        // cart.searchById(idFilter); // Phương thức này chỉ in, không trả về list
                        System.out.println("Filtered by ID (Console output):");
                        boolean found = false;
                        for(Media m : cart.getItemsOrdered()){
                            if(m.getId() == idFilter) {
                                System.out.println(m);
                                found = true;
                            }
                        }
                        if(!found) System.out.println("No media found with ID: " + idFilter);

                    } else if (filterType == 2) {
                        System.out.print("Enter Title to filter: ");
                        String titleFilter = scanner.nextLine();
                        // cart.searchByTitle(titleFilter); // Phương thức này chỉ in
                        System.out.println("Filtered by Title (Console output):");
                        boolean found = false;
                        for(Media m : cart.getItemsOrdered()){
                            if(m.isMatch(titleFilter)) { // Giả sử isMatch là case-insensitive contains
                                System.out.println(m);
                                found = true;
                            }
                        }
                        if(!found) System.out.println("No media found with title containing: " + titleFilter);
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
                        cart.printCart();
                    } else if (sortType == 2) {
                        cart.sortByCostTitle();
                        cart.printCart();
                    } else {
                        System.out.println("Invalid sort type.");
                    }
                    break;
                case 3: // Remove media
                    System.out.print("Enter the title of media to remove: ");
                    String titleToRemove = scanner.nextLine();
                    Media mediaToRemove = null;
                    // Tìm media trong itemsOrdered của cart
                    for (Media m : cart.getItemsOrdered()) {
                        if (m.getTitle() != null && m.getTitle().equalsIgnoreCase(titleToRemove)) {
                            mediaToRemove = m;
                            break;
                        }
                    }
                    if (mediaToRemove != null) {
                        cart.removeMedia(mediaToRemove);
                        System.out.println(titleToRemove + " removed from cart.");
                    } else {
                        System.out.println("Media not found in cart.");
                    }
                    break;
                case 4: // Play a media
                    System.out.print("Enter the title of media to play from cart: ");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = null;
                    for (Media m : cart.getItemsOrdered()) {
                        if (m.getTitle() != null && m.getTitle().equalsIgnoreCase(titleToPlay)) {
                            mediaToPlay = m;
                            break;
                        }
                    }
                    playMediaConsole(mediaToPlay);
                    break;
                case 5: // Place order
                    if (cart.getItemsOrdered().isEmpty()){
                        System.out.println("Cart is empty. Cannot place order.");
                    } else {
                        System.out.println("Order placed successfully! Total cost: " + String.format("%.2f", cart.totalCost()) + " $");
                        cart.getItemsOrdered().clear(); // Xóa giỏ hàng sau khi đặt
                        System.out.println("Cart has been cleared.");
                        cartOption = 0; // Tự động quay lại main menu
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

    public static void playMediaConsole(Media media) {
        if (media != null) {
            if (media instanceof Playable) {
                try {
                    ((Playable) media).play();
                } catch (PlayerException e) {
                    System.err.println("Playback Error: " + e.getMessage());
                    // Không dùng JOptionPane cho console version nếu không muốn trộn GUI
                    // System.err.println("Exception Details: " + e.toString());
                    // e.printStackTrace();
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred during playback: " + e.getMessage());
                }
            } else {
                System.out.println("Cannot play this type of media: " + (media.getTitle() != null ? media.getTitle() : "N/A"));
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    // Lớp lồng AimsApplication cho GUI
    public static class AimsApplication extends Application {
        private static Store store;
        private static Cart cart;

        static {
            store = new Store();
            cart = new Cart();
            addSampleDataToStore(store); // Sử dụng phương thức đã tách ra
            cart.setStore(store);
        }

        @Override
        public void start(Stage primaryStage) {
            try {
                // Đảm bảo đường dẫn này chính xác với cấu trúc dự án của bạn
                final String STORE_SCREEN_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_SCREEN_FXML_FILE_PATH));

                ViewStoreController viewStoreController = new ViewStoreController(store, cart);
                fxmlLoader.setController(viewStoreController);

                Parent root = fxmlLoader.load();
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle("AIMS Customer");
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to load Store.fxml: " + e.getMessage());
                // Hiển thị lỗi cho người dùng nếu cần
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Application Startup Error");
                alert.setHeaderText("Failed to load the main application screen.");
                alert.setContentText("Error details: " + e.getMessage() + "\n" + getStackTraceAsString(e));
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("An unexpected error occurred during startup: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Unexpected Application Error");
                alert.setHeaderText("An unexpected error occurred.");
                alert.setContentText("Error details: " + e.getMessage() + "\n" + getStackTraceAsString(e));
                alert.showAndWait();
            }
        }

        // main của AimsApplication chỉ để launch, không cần dữ liệu mẫu lại ở đây
        // public static void main(String[] args) {
        // launch(args);
        // }

        private static String getStackTraceAsString(Throwable throwable) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}