/*Name:AsapuDurgaRao
 * Id:N110415
 * Class:E2-CSE01-SS09*/ 
package com.example.jagadeesh.kisanseva;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.app.Activity;
import android.content.Intent;

public class Splash extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);


		Thread logoTimer=new Thread(){
			public void run(){
				try{


					sleep(3000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

				finally{

					startActivity(new Intent(Splash.this, MainActivity.class));
					finish();
				}
			}

		};
		logoTimer.start();
	}







	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
}

