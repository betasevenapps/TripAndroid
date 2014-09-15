package br.com.metaseven.trip.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.metaseven.trip.app.R;

public class MainFragment extends Fragment {

    public static final int POSITION = 0;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        init();
        return view;
    }

    private void init() {
        setHasOptionsMenu(true); // caso a action bar tenha que mudar
    }

}