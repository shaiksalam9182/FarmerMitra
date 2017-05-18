package com.example.jagadeesh.kisanseva;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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


public class Puc1 extends Fragment {

    ProgressDialog dialog1 = null;
    String httpurl = "http://10.4.16.74/videos/msgshow.php";
    String path="http://10.4.16.74/videos/uploads/";
    HttpPost httppost;
    StringBuffer buffer;
    ProgressDialog pDialog;
    HttpResponse response;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    TextView textPHP;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    protected boolean active = true;
    protected int splashTime = 8000;
    AlertDialog levelDialog;
    ProgressDialog pdLoading;
    String imagenames;
    ImageView iv;
    TextView tv;
    String img,imgsrc;
    ArrayList<String> MyArrList;
    Bitmap gotbitmap;
    ListView list;




    @Override
    public void onStart() {
        super.onStart();
        pdLoading = ProgressDialog.show(getActivity(), "Getting Crops\n Please Wait...", null,true,true);

      list = (ListView)getActivity().findViewById(R.id.listview1);



        list.setAdapter(new sadapte(getActivity()));


    }

    public static Puc1 newInstance() {
            Puc1 fragment = new Puc1();
            return fragment;
    }
    class sadapte extends BaseAdapter {

        public sadapte(FragmentActivity activity) {
        }

        @Override
        public int getCount() {
            viewlall();
            return MyArrList.size();
        }

        private void viewlall() {
            if (Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

            }


            String url = httpurl;

            try {

                JSONArray data = new JSONArray(getJSONUrl(url));

                MyArrList = new ArrayList<>();
                HashMap<String, String> map;

                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);

                    map = new HashMap<String, String>();

                    /*String s = "&amp;";
                    String html = c.getString("id");
                    String id;
                    if (html.indexOf(s) != -1) {
                        id = html.replaceAll(s, "&");
                    } else {
                        id = html;
                    }*/
                    //These are the column names of database so plz make any changes in db
                    //map.put("put_sno", c.getString("image"));

                    imagenames = c.getString("video");


                    MyArrList.add(imagenames);
                    pdLoading.dismiss();

                }


                // OnClick Item
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                        String videourl = MyArrList.get(postion);
                        Intent var = new Intent(getContext(),Crop_details.class);
                        var.putExtra("vidiurl",videourl);
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
            View v = inflater.inflate(R.layout.crops_column, null);

            iv = (ImageView) v.findViewById(R.id.cimage);
            tv = (TextView) v.findViewById(R.id.tv);

            String new_path = path + MyArrList.get(i);
            try {
                gotbitmap=retriecevideoframe(new_path);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            iv.setImageBitmap(gotbitmap);
            tv.setText(new_path);


            return v;

        }
    }

    private static Bitmap retriecevideoframe(String new_path) throws Throwable{
        Bitmap  bitmap =  null ;
        MediaMetadataRetriever mediaMetadataRetriever =  null ;
        try
        {
            mediaMetadataRetriever =  new   MediaMetadataRetriever ();
            if  ( Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(new_path,  new
                        HashMap < String ,  String >());
            else
                mediaMetadataRetriever.setDataSource(new_path);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        }
        catch  ( Exception  e)
        {
            e.printStackTrace();
            throw   new   Throwable (
                    "Exception in retriveVideoFrameFromVideo(String videoPath)"
                            + e.getMessage());
        }
        finally
        {
            if  (mediaMetadataRetriever !=  null )
            {
                mediaMetadataRetriever.release();
            }
        }
        return  bitmap;


    }


    public Puc1() {
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_crops, container, false);
            return rootView;
        }


}




