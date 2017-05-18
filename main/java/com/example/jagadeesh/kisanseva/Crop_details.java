package com.example.jagadeesh.kisanseva;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by salam on 22/12/16.
 */

public class Crop_details extends Activity {

    String path="http://10.2.12.74/videos/uploads/";
    String url,urlpath;
    VideoView vv;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_details);
        vv = (VideoView)findViewById(R.id.videoView);
        url = getIntent().getStringExtra("vidiurl");
        urlpath = path+url;

        pDialog = new ProgressDialog(Crop_details.this);
        // Set progressbar title

        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(getApplicationContext());

            // Get the URL from String VideoURL
            Uri video = Uri.parse(urlpath);
            vv.setMediaController(mediacontroller);
            mediacontroller.setAnchorView(vv);
            vv.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        vv.requestFocus();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                vv.start();
            }
        });

    }

    }

