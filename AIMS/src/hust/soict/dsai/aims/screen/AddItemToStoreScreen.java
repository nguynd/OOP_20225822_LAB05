package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.DVD;
import hust.soict.dsai.aims.media.CD;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    private Store store;
    private carts cart;
    private Media media;

    public AddItemToStoreScreen(Store store, carts cart, Media media) {
        this.store = store;
        this.cart = cart;
        this.media = media;

        // frame
        this.setSize(1500, 1500);
        this.setTitle("Add Item To Store");
        this.setResizable(true);

        // container
        Container pane = new Container();
        pane.setLayout(new BorderLayout());

        // label1
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel pntitle = new JPanel();
        JLabel lbtitle = new JLabel("Title: ");
        JTextField tftitle = new JTextField(20);
        pntitle.add(lbtitle);
        pntitle.add(tftitle);

        JPanel pncategory = new JPanel();
        JLabel lbcategory = new JLabel("Category: ");
        JTextField tfcategory = new JTextField(20);
        pncategory.add(lbcategory);
        pncategory.add(tfcategory);

        JPanel pncost = new JPanel();
        JLabel lbcost = new JLabel("Cost: ");
        JTextField tfcost = new JTextField(20);
        pncost.add(lbcost);
        pncost.add(tfcost);

        lbtitle.setPreferredSize(lbcategory.getPreferredSize());
        lbcost.setPreferredSize(lbcategory.getPreferredSize());

        JPanel btnbutton = new JPanel();
        JButton button = new JButton("Add Item");

        // Additional fields for specific media types
        JPanel pnauthor = new JPanel();
        JLabel lbauthor = new JLabel("Author: ");
        JTextField tfauthor = new JTextField(20);
        pnauthor.add(lbauthor);
        pnauthor.add(tfauthor);

        JPanel pndirector = new JPanel();
        JLabel lbdirector = new JLabel("Director: ");
        JTextField tfdirector = new JTextField(20);
        pndirector.add(lbdirector);
        pndirector.add(tfdirector);

        JPanel pnlength = new JPanel();
        JLabel lblength = new JLabel("Length: ");
        JTextField tflength = new JTextField(20);
        pnlength.add(lblength);
        pnlength.add(tflength);

        // Add action listener to button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                media.setTitle(tftitle.getText());
                media.setCategory(tfcategory.getText());
                try {
                    media.setCost(Float.parseFloat(tfcost.getText()));

                    if (media instanceof Book) {
                        ((Book) media).addAuthor(tfauthor.getText());
                    } else if (media instanceof DVD) {
                        ((DVD) media).setDirector(tfdirector.getText());
                        ((DVD) media).setLength(Integer.parseInt(tflength.getText()));
                    } else if (media instanceof CD) {

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                store.addMedia(media);
                MediaStore cell = new MediaStore(store.getItemsInStore().get(store.getItemsInStore().size() - 1), cart);
                StoreScreen storeScreen = new StoreScreen(store, cart);
                storeScreen.center.add(cell);
                dispose();
            }
        });

        btnbutton.add(button);

        panel.add(pntitle);
        panel.add(pncategory);
        panel.add(pncost);

        if (media instanceof Book) {
            panel.add(pnauthor);
        } else if (media instanceof DVD || media instanceof CD) {
            panel.add(pndirector);
            panel.add(pnlength);
        }

        pane.add(panel, BorderLayout.CENTER);
        pane.add(btnbutton, BorderLayout.SOUTH);
        this.add(pane);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
