package br.com.metaseven.trip.app.uihelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import br.com.metaseven.trip.app.R;

/**
 * Created by vagnnermartins on 15/09/14.
 */
public class MainUiHelper {

    public ImageView facebook;
    public ImageView background;

    public MainUiHelper(View view){
        init(view);
    }

    private void init(View view) {
        facebook = (ImageView) view.findViewById(R.id.main_facebook);
        background = (ImageView) view.findViewById(R.id.main_background);
    }
}
