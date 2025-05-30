package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.exception.PlayerException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ItemController {

    private Media media;
    private Cart cart;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblTitle;

    public ItemController(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        if (media != null) {
            lblTitle.setText(media.getTitle());
            lblCost.setText(String.format("%.2f $", media.getCost()));

            if (!(media instanceof Playable)) {
                btnPlay.setVisible(false);
                // Căn chỉnh btnAddToCart nếu btnPlay bị ẩn (HBox là parent)
                if (btnAddToCart.getParent() instanceof HBox) {
                    HBox parentHBox = (HBox) btnAddToCart.getParent();
                    if (parentHBox.getChildren().contains(btnPlay)) { // Kiểm tra btnPlay có thực sự là con không
                        HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60)); // Giá trị margin có thể cần điều chỉnh
                    }
                }
            } else {
                btnPlay.setVisible(true);
                if (btnAddToCart.getParent() instanceof HBox) {
                    HBox.setMargin(btnAddToCart, Insets.EMPTY); // Reset margin
                }
            }
        } else {
            lblTitle.setText("N/A");
            lblCost.setText("N/A");
            if(btnPlay != null) btnPlay.setVisible(false);
            if(btnAddToCart != null) btnAddToCart.setDisable(true);
        }
    }


    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        if (media == null || cart == null) {
            showErrorDialog("Error", "Cannot add item", "Media or Cart is not initialized.");
            return;
        }
        try {
            cart.addMedia(media);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Add to Cart");
            alert.setHeaderText(null);
            alert.setContentText("\"" + media.getTitle() + "\" has been added to the cart.\nNumber of items in cart: " + cart.getItemsOrdered().size());
            alert.showAndWait();
        } catch (LimitExceededException e) {
            showErrorDialog("Add to Cart Error", "Cannot Add Media to Cart", e.getMessage() + "\n" + getStackTraceAsString(e));
        } catch (Exception e) {
            showErrorDialog("Unexpected Error", "An unexpected error occurred.", e.getMessage() + "\n" + getStackTraceAsString(e));
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media == null) {
            showErrorDialog("Error", "Cannot play item", "Media is not initialized.");
            return;
        }
        if (media instanceof Playable) {
            try {
                // Gọi play() của media, nó sẽ tự in ra console hoặc chuẩn bị data
                ((Playable) media).play();

                // Hiển thị thông báo thành công trên JavaFX Alert
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Playing Media");
                successAlert.setHeaderText("Now Playing: " + media.getTitle());

                String content = "Type: " + media.getClass().getSimpleName() + "\n";
                if (media instanceof DigitalVideoDisc) {
                    content += "Length: " + ((DigitalVideoDisc) media).getLength() + " minutes";
                } else if (media instanceof CompactDisc) {
                    CompactDisc cd = (CompactDisc) media;
                    content += "Artist: " + cd.getArtist() + "\n";
                    content += "Total Length: " + cd.getLength() + " seconds\n";
                    content += "Tracks:\n";
                    if (cd.getTracks().isEmpty()){
                        content += "- No tracks available.";
                    } else {
                        for(Track track : cd.getTracks()){
                            content += "- " + track.getTitle() + " (" + track.getLength() + "s)\n";
                        }
                    }
                }
                successAlert.setContentText(content);
                successAlert.showAndWait();

            } catch (PlayerException e) {
                showErrorDialog("Playback Error", "Cannot Play Media: " + media.getTitle(), e.getMessage() + "\n" + getStackTraceAsString(e));
            } catch (Exception e) {
                showErrorDialog("Unexpected Error", "An unexpected error occurred during playback.", e.getMessage() + "\n" + getStackTraceAsString(e));
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Not Playable");
            alert.setHeaderText(null);
            alert.setContentText("The selected media \"" + media.getTitle() + "\" is not playable.");
            alert.showAndWait();
        }
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true); // Cho phép thay đổi kích thước để xem hết stack trace
        alert.getDialogPane().setPrefSize(480, 320); // Đặt kích thước ưu tiên
        alert.showAndWait();
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}