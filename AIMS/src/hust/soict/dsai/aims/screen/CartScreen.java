package hust.soict.dsai.aims.screen;



import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CD;
import hust.soict.dsai.aims.media.DVD;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javafx.application.Application.launch;


public class CartScreen extends JFrame {

    private Store store;
    private carts cart;


    public CartScreen(Store store,carts cart) {
        super();

        this.store = store;
        this.cart = cart;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setVisible(true);
        this.setPreferredSize(new Dimension(1050, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                    CartScreenController controller = new CartScreenController(store,cart);
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });
    }

    public static void main(String[] args) {
        Store store = new Store();
        carts cart=new carts();
        CartScreen cartScreen = new CartScreen(store,cart);
    }

}