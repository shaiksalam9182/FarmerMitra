package com.example.jagadeesh.kisanseva;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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


public class Engg2 extends Fragment {



    String httpurl = "http://10.4.16.74/govern/govern.php";
    ArrayList<HashMap<String, String>> MyArrList;

    String image,driver,type,lat,lon,k,j,l,m,n;

    ListView tra;
    TextView tvname,tvtype;
    ImageView dimage;
    Bitmap gei;



    @Override
    public void onStart() {
        super.onStart();


        tra = (ListView)getActivity().findViewById(R.id.listgov);
        tra.setAdapter(new Govern(getActivity()));


    }
    class Govern extends BaseAdapter{

        public Govern(FragmentActivity activity) {
        }

        @Override
        public int getCount() {
            viewgovern();
            return MyArrList.size();
        }
        private void viewgovern() {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

            }


            String url = httpurl;
            try {
                JSONArray data = new JSONArray(getJSONUrl(url));

                MyArrList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map;

                for(int i = 0; i < data.length(); i++){
                    JSONObject c = data.getJSONObject(i);

                    map = new HashMap<String, String>();


                    map.put("name", c.getString("Name"));

                    map.put("depart", c.getString("Ministry_Department"));
                    map.put("sponser", c.getString("Sponsered"));
                    map.put("funding", c.getString("Funding Pattern"));
                    map.put("descr", c.getString("Description"));
                    map.put("bene",c.getString("Beneficiaries"));
                    map.put("benetype", c.getString("Benefit Type"));
                    map.put("eligi",c.getString("Eligibility criteria"));
                    map.put("url", c.getString("Reference URL"));


                    MyArrList.add(map);

                }




                final AlertDialog.Builder viewDetail = new AlertDialog.Builder(getContext());
                // OnClick Item
                tra.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView,
                                            int position, long mylng) {

                        image = MyArrList.get(position).get("name").toString();

                        driver = MyArrList.get(position).get("depart").toString();

                        type = MyArrList.get(position).get("sponser").toString();

                        lat = MyArrList.get(position).get("funding").toString();

                        lon = MyArrList.get(position).get("descr").toString();

                        k = MyArrList.get(position).get("bene").toString();

                        l = MyArrList.get(position).get("benetype").toString();

                        m = MyArrList.get(position).get("eligi").toString();

                        n = MyArrList.get(position).get("url").toString();




                        //String sAttachments = MyArrList.get(position).get("put_attachments").toString();


                        Intent var = new Intent(getContext(),Schemes_details.class);
                        var.putExtra("name",image);
                        var.putExtra("depart",driver);
                        var.putExtra("sponser", type);
                        var.putExtra("funding", lat);
                        var.putExtra("descr", lon);
                        var.putExtra("bene", k);
                        var.putExtra("benetype", l);
                        var.putExtra("eligi", m);
                        var.putExtra("url", n);

                        // var.putExtra("files", sAttachments);
                        startActivity(var);
                    }

                });
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

        private String getJSONUrl(String url) {
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

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.govern_list, null);
            TextView tvname = (TextView)v.findViewById(R.id.gtitle);
            TextView tvdept = (TextView)v.findViewById(R.id.gdept);

            tvname.setText(MyArrList.get(i).get("name").toString());
            tvdept.setText(MyArrList.get(i).get("depart").toString());

            return v;
        }
    }

    public static Engg2 newInstance() {
            Engg2 fragment = new Engg2();
            return fragment;
    }


    public Engg2() {
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_government, container, false);
            return rootView;
        }


}




