package com.example.jagadeesh.kisanseva;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;




public class Engg4 extends Fragment {


    TextView tvs;



    @Override
    public void onStart() {
        super.onStart();
        //notices();
        tvs = (TextView)getActivity().findViewById(R.id.tvs);
        tvs.setText("Hello Engg4");

    }

    public static Engg4 newInstance() {
            Engg4 fragment = new Engg4();
            return fragment;
    }

    public Engg4() {
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
            return rootView;
        }


}




