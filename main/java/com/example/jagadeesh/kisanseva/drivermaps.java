package com.example.jagadeesh.kisanseva;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by salam on 23/12/16.
 */

public class drivermaps extends Activity {

    String driver_image,driver_name,driver_type,Latitude,Longitude;
    ImageView imgpro;
    TextView tvname;
    TextView tvtype;
    TextView tvlat;
    TextView tvlon;
    String url="http://10.4.16.74/driver/uploads/";
    String complter;
    Bitmap k;
    Button bmaps;
    double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drivermaps);
        driver_image = getIntent().getStringExtra("not_title");
        driver_name = getIntent().getStringExtra("not_det");
        driver_type = getIntent().getStringExtra("sdby");
        Latitude = getIntent().getStringExtra("date");
        Longitude = getIntent().getStringExtra("time");

        imgpro = (ImageView)findViewById(R.id.profile);
        tvname = (TextView)findViewById(R.id.drivername);
        tvtype = (TextView)findViewById(R.id.drivertype);
        tvlat = (TextView)findViewById(R.id.longitude);
        tvlon = (TextView)findViewById(R.id.latitude);
        bmaps = (Button)findViewById(R.id.bmaps);

        complter = url+driver_image;

        Bitmap pro = getbitmap(complter);

        imgpro.setImageBitmap(pro);
        tvname.setText(driver_name);
        tvtype.setText(driver_type);
        tvlat.setText(Latitude);
        tvlon.setText(Longitude);

        lat = Double.parseDouble(Latitude);
        lon = Double.parseDouble(Longitude);

        bmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bask = new Bundle();
                bask.putDouble("latti",lat);
                bask.putDouble("lundi",lon);
                bask.putString("name",driver_name);
                Intent m = new Intent(drivermaps.this,showdriversmaps.class);
                m.putExtras(bask);
                startActivity(m);

            }
        });

    }

    private Bitmap getbitmap(String complter) {
        URL url = null;


        try {

            url =  new URL(complter);

            k =  BitmapFactory.decodeStream(url.openConnection() .getInputStream());


        } catch (MalformedURLException e) {
            Log.e("King","Urlexeception");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("King","Io exception");
            e.printStackTrace();
        }

        return k;

    }
}
