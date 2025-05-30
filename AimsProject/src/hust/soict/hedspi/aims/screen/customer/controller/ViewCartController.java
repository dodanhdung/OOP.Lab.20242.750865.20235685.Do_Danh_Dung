package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.exception.PlayerException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

// Các import quan trọng cho JavaFX mà bạn cần kiểm tra:
import javafx.stage.Stage; // << Quan trọng cho lỗi của bạn
import javafx.scene.Scene; // << Quan trọng cho lỗi của bạn
import javafx.scene.Node;  // << Quan trọng cho lỗi của bạn (lấy stage từ event)
import javafx.event.ActionEvent; // << Quan trọng cho các phương thức xử lý sự kiện
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
// --- Hết phần import quan trọng cho lỗi cụ thể ---

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class ViewCartController {

    private Store store;
    private Cart cart;

    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, Integer> colMediaId;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediaCategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;
    @FXML
    private Label lblTotalCost;
    @FXML
    private Button btnPlaceOrder;
    @FXML
    private TextField tfFilter;
    @FXML
    private ToggleButton btnFilterById;
    @FXML
    private ToggleButton btnFilterByTitle;
    @FXML
    private ToggleGroup filterToggleGroup;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;

    public ViewCartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        if (cart != null && cart.getItemsOrdered() != null) {
            tblMedia.setItems(cart.getItemsOrdered());
            updateTotalCost();
            btnPlaceOrder.setDisable(cart.getItemsOrdered().isEmpty());

            cart.getItemsOrdered().addListener(new ListChangeListener<Media>() {
                @Override
                public void onChanged(Change<? extends Media> change) {
                    updateTotalCost();
                    btnPlaceOrder.setDisable(cart.getItemsOrdered().isEmpty());
                }
            });
        }

        btnPlay.setDisable(true);
        btnRemove.setDisable(true);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                if (newValue != null) {
                    btnPlay.setDisable(!(newValue instanceof Playable));
                    btnRemove.setDisable(false);
                } else {
                    btnPlay.setDisable(true);
                    btnRemove.setDisable(true);
                }
            }
        });

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> filterMedia(newValue));

        if (filterToggleGroup != null) {
            filterToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                filterMedia(tfFilter.getText());
            });
        }
        filterMedia(tfFilter.getText());
    }

    private void updateTotalCost() {
        if (cart != null) {
            lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
        } else {
            lblTotalCost.setText("0.00 $");
        }
    }

    private void filterMedia(String filterText) {
        if (cart == null || cart.getItemsOrdered() == null) return;

        if (filterText == null || filterText.isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
        } else {
            ObservableList<Media> filteredList = FXCollections.observableArrayList();
            String lowerCaseFilterText = filterText.toLowerCase();
            boolean filterByIdSelected = btnFilterById != null && btnFilterById.isSelected();

            for (Media media : cart.getItemsOrdered()) {
                if (filterByIdSelected) {
                    try {
                        int id = Integer.parseInt(filterText.trim());
                        if (media.getId() == id) {
                            filteredList.add(media);
                        }
                    } catch (NumberFormatException e) {
                        // Bỏ qua
                    }
                } else {
                    if (media.getTitle() != null && media.getTitle().toLowerCase().contains(lowerCaseFilterText)) {
                        filteredList.add(media);
                    }
                }
            }
            tblMedia.setItems(filteredList);
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart == null || cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Order Warning");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty. Please add items before placing an order.");
            alert.showAndWait();
        } else {
            System.out.println("Order placed. Total cost: " + cart.totalCost() + "$");
            cart.getItemsOrdered().clear();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been placed successfully!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_SCREEN_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_SCREEN_FXML_FILE_PATH));

            ViewStoreController viewStoreController = new ViewStoreController(this.store, this.cart);
            fxmlLoader.setController(viewStoreController);

            Parent root = fxmlLoader.load();

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("AIMS - Store");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Navigation Error", "Could not load the store screen: " + e.getMessage(), getStackTraceAsString(e));
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null && selectedMedia instanceof Playable) {
            try {
                ((Playable) selectedMedia).play();
            } catch (PlayerException e) {
                showErrorDialog("Playback Error", "Cannot Play Media: " + e.getMessage(), getStackTraceAsString(e));
            } catch (Exception e) {
                showErrorDialog("Unexpected Error", "An unexpected error occurred during playback: " + e.getMessage(), getStackTraceAsString(e));
            }
        } else if (selectedMedia != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Not Playable");
            alert.setHeaderText(null);
            alert.setContentText("The selected media '" + selectedMedia.getTitle() + "' is not playable.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null && cart != null) {
            String title = selectedMedia.getTitle();
            cart.removeMedia(selectedMedia);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Remove from Cart");
            alert.setHeaderText(null);
            alert.setContentText(title + " has been removed from the cart.");
            alert.showAndWait();
        }
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}