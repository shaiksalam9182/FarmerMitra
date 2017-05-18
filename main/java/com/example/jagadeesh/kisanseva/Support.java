package com.example.jagadeesh.kisanseva;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Support extends Fragment {

    public static Support newInstance() {
        Support fragment = new Support();
        return fragment;
    }

    public Support() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_support, container, false);
        return rootView;
    }
}
