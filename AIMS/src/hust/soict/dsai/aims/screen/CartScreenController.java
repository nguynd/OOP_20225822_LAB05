package hust.soict.dsai.aims.screen;


import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.LimitExceededException;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class CartScreenController {

    private Store store;
    private carts cart;


    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    public Label totalCost;

    @FXML
    private float totalCost(){
        return this.cart.totalCost();
    }

    @FXML
    public void PlaceOrder(javafx.event.ActionEvent event) {
        this.cart.placeOrder();
    }


    public CartScreenController(Store store,carts cart) {
        super();
        this.store = store;
        this.cart = cart;
    }

    @FXML
    void btnPlayPressed(javafx.event.ActionEvent event) {
        Media media=tblMedia.getSelectionModel().getSelectedItem();
        if(media instanceof Playable){
            Playable playable=(Playable)media;
            try {
                playable.play();
            } catch (PlayerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnRemovePressed(javafx.event.ActionEvent event) {
        Media media=tblMedia.getSelectionModel().getSelectedItem();
        try {
            cart.removeMedia(media);
        } catch (LimitExceededException e) {
            throw new RuntimeException(e);
        }
        totalCost.setText(String.valueOf(totalCost()));
    }

    @FXML
    void openStoreScreen(javafx.event.ActionEvent event) {
        StoreScreen storeScreen=new StoreScreen(store,cart);
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("Title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("Category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("Cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());
        totalCost.setText(String.valueOf(totalCost()));
        this.cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> {
            // Cập nhật lại total cost khi danh sách thay đổi
            Platform.runLater(() -> {
                totalCost.setText(String.format("%.2f $", totalCost()));
            });
        });


    }
}