package com.example.jagadeesh.kisanseva;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Aboutus extends Fragment {

    public static Aboutus newInstance() {
        Aboutus fragment = new Aboutus();
        return fragment;
    }

    public Aboutus() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_aboutus, container, false);
        return rootView;
    }
}
