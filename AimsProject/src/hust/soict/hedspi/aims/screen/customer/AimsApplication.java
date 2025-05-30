package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController; // Import đúng controller
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AimsApplication extends Application {
    private static Store store;
    private static Cart cart;

    // Khởi tạo static block để đảm bảo store và cart có dữ liệu trước khi start() được gọi
    static {
        store = new Store();
        cart = new Cart();

        // Khởi tạo dữ liệu mẫu cho Store
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Aladdin", "Animation", "John Musker", 90, 18.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(4, "Harry Potter", "Fantasy", "Chris Columbus", 152, 22.5f);
        // Thêm một DVD có độ dài lớn để kiểm tra PlayerException
        DigitalVideoDisc dvd5 = new DigitalVideoDisc(5, "Very Long Movie", "Drama", "Big Director", 600, 30.0f); // length > NB_AVAILABLE_DVDS (500)

        CompactDisc cd1 = new CompactDisc(6, "Thriller", "Pop", "Quincy Jones", 0, 15.0f, "Michael Jackson");
        cd1.addTrack(new Track("Wanna Be Startin' Somethin'", 363));
        cd1.addTrack(new Track("Baby Be Mine", 260));
        cd1.addTrack(new Track("Thriller", 357));
        // Thêm CD có tổng độ dài vượt quá 10000 để kiểm tra PlayerException
        CompactDisc cd2 = new CompactDisc(7, "Long Album", "Classical", "Various", 0, 20.0f, "Various Artists");
        for(int i=0; i<30; i++) { // Thêm nhiều track để vượt quá 10000s
            cd2.addTrack(new Track("Track " + (i+1), 400));
        }

        Book book1 = new Book(8, "The Lord of the Rings", "Fantasy", 25.0f);
        book1.addAuthor("J.R.R. Tolkien");

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(dvd4);
        store.addMedia(dvd5); // Thêm DVD lỗi vào Store
        store.addMedia(cd1);
        store.addMedia(cd2); // Thêm CD lỗi vào Store
        store.addMedia(book1);

        // Đặt Store cho Cart để có thể chuyển đổi giữa các màn hình
        cart.setStore(store);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Sửa đổi đường dẫn FXML để trỏ đến đúng vị trí tài nguyên
            final String STORE_SCREEN_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_SCREEN_FXML_FILE_PATH));

            // Tạo instance của controller và set nó cho FXMLLoader
            // Điều này cho phép chúng ta truyền dữ liệu vào controller trước khi load FXML
            ViewStoreController viewStoreController = new ViewStoreController(store, cart);
            fxmlLoader.setController(viewStoreController);

            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("AIMS - Store");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Application Error");
            alert.setHeaderText("Failed to load application");
            alert.setContentText("Could not load the main store screen: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}