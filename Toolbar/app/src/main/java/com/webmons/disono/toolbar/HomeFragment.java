package com.webmons.disono.toolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View inflaterView;

    TextView textView_home;
    Menu optionsMenu;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflaterView = inflater.inflate(R.layout.fragment_home, container, false);

        String unixTime = "Unix Time: " + System.currentTimeMillis() / 1000L;
        textView_home = (TextView) inflaterView.findViewById(R.id.textView_home);
        textView_home.setText(unixTime);

        // Inflate the layout for this fragment
        return inflaterView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_home, menu);
        optionsMenu = menu;

        // add new item on menu
        menu.add(0, 1, 0, "Option 1");
    }

}
