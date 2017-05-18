package com.example.jagadeesh.kisanseva;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by salam on 24/12/16.
 */

public class Schemes_details extends Activity{
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    String title,depart,spon,fundi,descr,bne,benety,eligi,url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schemes_details);
        tv1 = (TextView)findViewById(R.id.name);
        tv2 = (TextView)findViewById(R.id.depart);
        tv3 = (TextView)findViewById(R.id.sponser);
        tv4 = (TextView)findViewById(R.id.funding);
        tv5 = (TextView)findViewById(R.id.descr);
        tv6 = (TextView)findViewById(R.id.bene);
        tv7 = (TextView)findViewById(R.id.benetype);
        tv8 = (TextView)findViewById(R.id.eligi);
        tv9 = (TextView)findViewById(R.id.rul);

        title = getIntent().getStringExtra("name");
        depart = getIntent().getStringExtra("depart");
        spon = getIntent().getStringExtra("sponser");
        fundi = getIntent().getStringExtra("funding");
        descr = getIntent().getStringExtra("descr");
        bne = getIntent().getStringExtra("bene");
        benety = getIntent().getStringExtra("benetype");
        eligi = getIntent().getStringExtra("eligi");
        url = getIntent().getStringExtra("url");

        tv1.setText(title);
        tv2.setText("Department:\n"+depart);
        tv3.setText("Sponsered:\n"+spon);
        tv4.setText("Fundings:\n"+fundi);
        tv5.setText("Description:\n"+descr);
        tv6.setText("Benefits:\n"+bne);
        tv7.setText("Benefit Type:\n"+benety);
        tv8.setText("Eligigibility:\n"+eligi);
        tv9.setText("Reference Url:\n"+url);



    }
}
