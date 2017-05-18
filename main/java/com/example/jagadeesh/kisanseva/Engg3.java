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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Engg3 extends Fragment {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;
    String introduction,history,methods,cropDiversity,soilManagement,weedManagement,controllingOtherOrganisms,livestock,geneticModification,tools,composting,referenceSite;
    String httpurl="http://10.4.16.74/organic/organic.php";



    @Override
    public void onStart() {
        super.onStart();
        tv1 = (TextView)getActivity().findViewById(R.id.k);
        tv2 = (TextView)getActivity().findViewById(R.id.ki);
        tv3 = (TextView)getActivity().findViewById(R.id.kin);
        tv4 = (TextView)getActivity().findViewById(R.id.gang);
        tv5 = (TextView)getActivity().findViewById(R.id.gan);
        tv6 = (TextView)getActivity().findViewById(R.id.ga);
        tv7 = (TextView)getActivity().findViewById(R.id.g);
        tv8 = (TextView)getActivity().findViewById(R.id.helo);
        tv8 = (TextView)getActivity().findViewById(R.id.hel);
        tv10 = (TextView)getActivity().findViewById(R.id.he);
        tv11 = (TextView)getActivity().findViewById(R.id.h);


        organic();}

        public void organic(){

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }


            String url = httpurl;

            try {

                JSONArray data = new JSONArray(getJSONUrl(url));

                final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map;

                for(int i = 0; i < data.length(); i++){
                    JSONObject c = data.getJSONObject(i);

                    map = new HashMap<String, String>();

                    introduction=c.getString("introduction");
                    history=c.getString("history");
                    methods=c.getString(" methods");
                    cropDiversity=c.getString("cropDiversity");
                    soilManagement=c.getString("soilManagement");
                    weedManagement=c.getString("weedManagement");
                    controllingOtherOrganisms=c.getString("controllingOtherOrganisms");
                    livestock=c.getString("livestock");
                    geneticModification=c.getString("geneticModification");
                    tools=c.getString("tools");
                    composting=c.getString("composting");
                    referenceSite=c.getString("referenceSite");

                    tv1.setText("Introduction:\n"+introduction);
                    tv2.setText("History:\n"+history);
                    tv3.setText("Methods:\n"+methods);
                    tv4.setText("CropDiversity:\n"+cropDiversity);
                    tv5.setText("SoilManagement:\n"+soilManagement);
                    tv6.setText("ControllingOtherOrganisms:\n"+controllingOtherOrganisms);
                    tv7.setText("Livestock:\n"+livestock);
                    tv8.setText("GeneticModification:\n"+geneticModification);
                    tv9.setText("Tools:\n"+tools);
                    tv10.setText("Compositing:\n"+composting);
                    tv11.setText("Reference:\n"+referenceSite);








                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    public String getJSONUrl(String url) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }







    public static Engg3 newInstance() {
            Engg3 fragment = new Engg3();
            return fragment;
    }


    public Engg3() {
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_organic, container, false);
            return rootView;
        }



}




