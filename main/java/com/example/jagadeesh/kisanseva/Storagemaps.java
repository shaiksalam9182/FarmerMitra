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
 * Created by salam on 24/12/16.
 */

public class Storagemaps extends Activity{

    TextView tvname,tvdescr,tvlan,tvlat;
    ImageView ipro;
    Button bsto;
    Bitmap s;
    String storagename,storagedesc,storagelan,stolat,stoimage;
    String ur = "http://10.4.16.74/storages/uploads/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storagemaps);
        tvname = (TextView)findViewById(R.id.storagename);
        tvdescr = (TextView)findViewById(R.id.storageddesc);
        tvlan = (TextView)findViewById(R.id.stolongitude);
        tvlat = (TextView)findViewById(R.id.storagelaitude);

        ipro = (ImageView)findViewById(R.id.pro);

        bsto = (Button)findViewById(R.id.bstoragemaps);

        storagename = getIntent().getStringExtra("not_det");
        storagedesc = getIntent().getStringExtra("sdby");
        storagelan = getIntent().getStringExtra("date");
        stolat = getIntent().getStringExtra("time");
        stoimage = getIntent().getStringExtra("not_title");

        String complterurl = ur+stoimage;
        Bitmap ging = getbitmap(complterurl);
        ipro.setImageBitmap(ging);
        tvname.setText(storagename);
        tvdescr.setText(storagedesc);
        tvlan.setText(stolat);
        tvlat.setText(storagelan);

        final double king = Double.parseDouble(stolat);
        final double kin = Double.parseDouble(storagelan);

        bsto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bask = new Bundle();
                bask.putDouble("latti",king);
                bask.putDouble("lundi",kin);
                bask.putString("name",storagename);
                Intent m = new Intent(Storagemaps.this,showstoragemaps.class);
                m.putExtras(bask);
                startActivity(m);


            }
        });
    }

    private Bitmap getbitmap(String complterurl) {
        URL url = null;


        try {

            url =  new URL(complterurl);

            s =  BitmapFactory.decodeStream(url.openConnection() .getInputStream());


        } catch (MalformedURLException e) {
            Log.e("King","Urlexeception");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("King","Io exception");
            e.printStackTrace();
        }

        return s;

    }
}
