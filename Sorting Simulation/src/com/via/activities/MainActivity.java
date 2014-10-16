package com.via.activities;

import uet.via.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.via.fragments.SimulationFragment;

@SuppressLint({ "NewApi", "ResourceAsColor" })
public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageView exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
		initViews();
		getFragmentManager().beginTransaction().add(R.id.activity_main_contain, SimulationFragment.getInstance()).commit();
	
	}

	private void initViews() {
		exit = (ImageView) findViewById(R.id.activity_main_exit);
		exit.setOnClickListener(this);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.activity_main_exit: 
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
				alertDialogBuilder.setTitle("Sorting Simulation");
				alertDialogBuilder
					.setMessage("Are you sure ? All data will be lost.")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							System.exit(0);
							MainActivity.this.finish();
						}
					  })
					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
	 
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
			break;
	
		default:
			break;
		}
		
	}
}
