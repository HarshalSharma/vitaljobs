package com.harshal.vitaljobsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ChangeURL extends ActionBarActivity{

	//r0 is localhost
	RadioButton r0,r1;
	RadioGroup rg;
	Backend backend;
	
	String localhost = "http://192.168.137.1:8888";
	String Appengine = "http://swjobsearchdemo.appspot.com";
	
	boolean isOnAppengine = true;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_url);
	
		r0 = (RadioButton) findViewById(R.id.radio0);
		r1 = (RadioButton) findViewById(R.id.radio1);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		
		backend = Backend.getInstance();
		if(backend.SERVER_URL.equals(Appengine))
		{
			r1.setChecked(true);
			isOnAppengine = true;
		}
		else if(backend.SERVER_URL.equals(localhost)){
			r0.setChecked(true);
			isOnAppengine = false;
		}
	
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				case R.id.radio0:
					isOnAppengine = false;
					break;
				case R.id.radio1:
					isOnAppengine = true;
					break;
				default:
					break;
				}
			}
		});
		
	}
	
	
	public void changeURL(View v)
	{
		if(isOnAppengine)
			backend.SERVER_URL = Appengine;
		else
			backend.SERVER_URL = localhost;
		this.finish();
	}
	
	
}
