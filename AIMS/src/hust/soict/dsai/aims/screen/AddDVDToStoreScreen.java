package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.media.DVD;
import hust.soict.dsai.aims.store.Store;


public class AddDVDToStoreScreen extends AddItemToStoreScreen{
    public AddDVDToStoreScreen(Store store, carts cart, DVD disc) {
        super(store,cart,disc);
    }
}