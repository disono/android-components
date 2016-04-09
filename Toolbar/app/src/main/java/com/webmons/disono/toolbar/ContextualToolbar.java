package com.webmons.disono.toolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContextualToolbar extends Fragment {
    View inflaterView;
    private Toolbar toolbar;

    public ContextualToolbar() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        // change title
        if (actionBar != null) {
            actionBar.setSubtitle("");
            actionBar.setTitle("Contextual Toolbar");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflaterView = inflater.inflate(R.layout.fragment_contextual_toolbar, container, false);

        toolbar = (Toolbar) inflaterView.findViewById(R.id.tool_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Inflate the layout for this fragment
        return inflaterView;
    }
}
