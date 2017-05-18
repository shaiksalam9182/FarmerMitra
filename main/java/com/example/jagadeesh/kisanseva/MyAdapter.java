package com.example.jagadeesh.kisanseva;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by salam on 13/12/16.
 */
public class MyAdapter extends BaseAdapter {


    ProgressDialog dialog1 = null;
    String httpurl = "http://192.168.22.14/images/imglist.php";

    HttpPost httppost;
    String path="http://192.168.22.14/images/";
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    protected boolean active = true;
    protected int splashTime = 8000;
    AlertDialog levelDialog;
    ProgressDialog pdLoading;

    ImageView iv;
    Bitmap get;
    String imagenames;
    TextView tv;
    Puc1 crops;
    ArrayList<String> MyArrList;
    Context context;




    public MyAdapter(Puc1 puc1) {
        this.crops = puc1;
    }

    @Override
    public int getCount() {
        viewlall();
        return MyArrList.size();
    }
    private void viewlall() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }


        String url = httpurl;

        try {

            JSONArray data = new JSONArray(getJSONUrl(url));

            MyArrList = new ArrayList<>();
            HashMap<String, String> map;

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();

                String s="&amp;";
                String html=c.getString("id");
                String id;
                if(html.indexOf(s) != -1)
                {
                    id=html.replaceAll(s,"&");
                }
                else
                {
                    id=html;
                }
                //These are the column names of database so plz make any changes in db
                map.put("put_sno", c.getString("image"));

                imagenames= c.getString("image");




                MyArrList.add(imagenames);

            }




            // OnClick Item

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
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate( R.layout.crops_column, null );

        iv = (ImageView)v.findViewById(R.id.cimage);
        tv = (TextView)v.findViewById(R.id.tv);

        String new_path = path+MyArrList.get(i);
        getimage(new_path);


        return v;

    }

    private void getimage(String new_path) {

        URL url = null;
        Bitmap image = null;

        try {

            url =  new URL(new_path);

            image =  BitmapFactory.decodeStream(url.openConnection() .getInputStream());
            iv.setImageBitmap(image);
            tv.setText(new_path);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }




    }
}
