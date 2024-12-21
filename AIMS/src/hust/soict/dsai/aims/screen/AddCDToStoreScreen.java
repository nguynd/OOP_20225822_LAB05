package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.carts;
import hust.soict.dsai.aims.media.CD;
import hust.soict.dsai.aims.store.Store;

public class AddCDToStoreScreen extends AddItemToStoreScreen{
    public AddCDToStoreScreen(Store store, carts cart, CD cd) {
        super(store,cart,cd);
    }
}