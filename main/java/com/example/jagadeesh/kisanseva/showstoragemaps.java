package com.example.jagadeesh.kisanseva;

import android.app.Activity;
import android.os.Bundle;



/**
 * Created by salam on 24/12/16.
 */


public class showstoragemaps extends Activity /*implements OnMapReadyCallback*/{

    double kan,kang;
    String pund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showstoragelocation);

        /*Bundle gotbasket = getIntent().getExtras();
        kan= gotbasket.getDouble("latti");
        kang = gotbasket.getDouble("lundi");
        pund = gotbasket.getString("name");

        MapFragment Mm = (MapFragment) getFragmentManager().
                findFragmentById(R.id.pppp);
        Mm.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(kan,kang))
                .title("salam"));*/


    }
}
