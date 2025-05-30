package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {

    private Store store;
    private Cart cart;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 0;

        System.out.println("ViewStoreController: Initializing items. Store available: " + (store != null) + ", Cart available: " + (cart != null));
        if (store != null && store.getItemsInStore() != null) { // Thêm kiểm tra store.getItemsInStore() != null
            System.out.println("ViewStoreController: Number of items in store: " + store.getItemsInStore().size());
        } else if (store == null) {
            System.out.println("ViewStoreController: Store object is null.");
        } else {
            System.out.println("ViewStoreController: Store.getItemsInStore() is null.");
        }


        if (store != null && store.getItemsInStore() != null && gridPane != null) {
            gridPane.getChildren().clear();
            for (int i = 0; i < store.getItemsInStore().size(); i++) {
                Media media = store.getItemsInStore().get(i);
                if (media == null) {
                    System.out.println("ViewStoreController: Media object at index " + i + " is null. Skipping.");
                    continue;
                }
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
                    if (getClass().getResource(ITEM_FXML_FILE_PATH) == null) { // DEBUG PRINTLN: Kiểm tra đường dẫn FXML
                        System.err.println("ViewStoreController: ERROR - Cannot find Item.fxml at path: " + ITEM_FXML_FILE_PATH);
                        continue;
                    }

                    ItemController itemController = new ItemController(media, cart);
                    System.out.println("ViewStoreController: Loading Item.fxml for: " + media.getTitle() + " with ItemController instance: " + itemController.hashCode());
                    System.out.println("ViewStoreController: Media object for this item: " + media.toString()); // Đảm bảo media.toString() không null
                    System.out.println("ViewStoreController: Cart object for this item: " + (cart != null ? cart.hashCode() : "null cart object"));

                    fxmlLoader.setController(itemController);
                    AnchorPane anchorPane = fxmlLoader.load();
                    System.out.println("ViewStoreController: Item.fxml loaded successfully for: " + media.getTitle());


                    gridPane.add(anchorPane, column, row);
                    GridPane.setMargin(anchorPane, new Insets(10));

                    column++;
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("ViewStoreController: IOException - Failed to load Item.fxml for media: " + media.getTitle() + " - " + e.getMessage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.err.println("ViewStoreController: NullPointerException while loading item: " + media.getTitle() + " - " + e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("ViewStoreController: Unexpected exception while loading item: " + media.getTitle() + " - " + e.getMessage());
                }
            }
        } else {
            if(gridPane != null) gridPane.getChildren().clear();
            System.err.println("ViewStoreController: Store, itemsInStore, or GridPane is null. Cannot populate view.");
        }
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            final String CART_SCREEN_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_SCREEN_FXML_FILE_PATH));
            if (getClass().getResource(CART_SCREEN_FXML_FILE_PATH) == null) { // DEBUG PRINTLN: Kiểm tra đường dẫn FXML
                System.err.println("ViewStoreController: ERROR - Cannot find Cart.fxml at path: " + CART_SCREEN_FXML_FILE_PATH);
                return;
            }

            ViewCartController viewCartController = new ViewCartController(this.store, this.cart);
            fxmlLoader.setController(viewCartController);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("AIMS - Cart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("Could not load the cart screen: " + e.getMessage() + "\n" + getStackTraceAsString(e));
            alert.showAndWait();
        }
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}