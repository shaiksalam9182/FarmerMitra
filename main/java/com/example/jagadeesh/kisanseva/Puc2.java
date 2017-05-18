package com.example.jagadeesh.kisanseva;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;



public class Puc2 extends Fragment {


    String httpurl = "http://10.4.16.74/driver/driverlist.php";
    ArrayList<HashMap<String, String>> MyArrList;
    String path="http://10.4.16.74/driver/uploads/",newpath;
    String image,driver,type,lat,lon;

    ListView tra;
    TextView tvname,tvtype;
    ImageView dimage;
    Bitmap gei;


    @Override
    public void onStart() {
        super.onStart();

        tra = (ListView)getActivity().findViewById(R.id.trans);
        tra.setAdapter(new traadapter(getActivity()));
    }

    public static Puc2 newInstance() {
            Puc2 fragment = new Puc2();
            return fragment;
    }
    class traadapter extends BaseAdapter{

        public traadapter(FragmentActivity activity) {
        }

        @Override
        public int getCount() {
            viewdrivers();
            return MyArrList.size();
        }
        private void viewdrivers() {
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


                map.put("put_image", c.getString("image"));

                map.put("put_driver", c.getString("name"));
                map.put("put_type", c.getString("type"));
                map.put("put_lat", c.getString("lat"));
                map.put("put_long", c.getString("lon"));
                MyArrList.add(map);

            }




            final AlertDialog.Builder viewDetail = new AlertDialog.Builder(getContext());
            // OnClick Item
            tra.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView,
                                        int position, long mylng) {

                     image = MyArrList.get(position).get("put_image").toString();

                   driver = MyArrList.get(position).get("put_driver").toString();

                    type = MyArrList.get(position).get("put_type").toString();

                    lat = MyArrList.get(position).get("put_lat").toString();

                    lon = MyArrList.get(position).get("put_long").toString();



                    //String sAttachments = MyArrList.get(position).get("put_attachments").toString();


                    Intent var = new Intent(getContext(),drivermaps.class);
                    var.putExtra("not_title",image);
                    var.putExtra("not_det",driver);
                    var.putExtra("sdby", type);
                    var.putExtra("date", lat);
                    var.putExtra("time", lon);

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
            View v = inflater.inflate(R.layout.drivers_details, null);

            dimage = (ImageView)v.findViewById(R.id.dimage);
            tvname = (TextView)v.findViewById(R.id.tvname);
            tvtype = (TextView)v.findViewById(R.id.tvtype);

            newpath = path+MyArrList.get(i).get("put_image").toString();
           Bitmap kin =  getimag(newpath);
            dimage.setImageBitmap(kin);
            tvname.setText(MyArrList.get(i).get("put_driver").toString());
            tvtype.setText(MyArrList.get(i).get("put_type").toString());
            return v;
        }
    }

    private Bitmap getimag(String newpath) {
        URL url = null;


        try {

            url =  new URL(newpath);

            gei =  BitmapFactory.decodeStream(url.openConnection() .getInputStream());


        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return gei;
    }


    public Puc2() {
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_transport, container, false);
            return rootView;
        }



}




