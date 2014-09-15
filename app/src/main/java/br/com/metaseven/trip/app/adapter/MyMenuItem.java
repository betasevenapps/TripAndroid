package br.com.metaseven.trip.app.adapter;

import java.util.ArrayList;

import br.com.metaseven.trip.app.R;
import br.com.metaseven.trip.app.fragment.MainFragment;

public class MyMenuItem {

    private int idStringResource;
    private int idDrawableResource;

    public MyMenuItem() {
    }

    private MyMenuItem(int idStringResource, int idDrawableResource) {
        this.idStringResource = idStringResource;
        this.idDrawableResource = idDrawableResource;
    }

    public ArrayList<MyMenuItem> getItemsMenu(){
        ArrayList<MyMenuItem> list = new ArrayList<MyMenuItem>();
        list.add(new MyMenuItem(R.string.item_exemplo, android.R.drawable.ic_menu_myplaces));
        list.add(new MyMenuItem(R.string.item_exemplo, android.R.drawable.ic_menu_edit));
        list.add(new MyMenuItem(R.string.item_exemplo, android.R.drawable.ic_menu_add));

        return list;
    }

    public int getIdStringResource() {
        return idStringResource;
    }

    public int getIdDrawableResource() {
        return idDrawableResource;
    }
}
