package com.harshal.vitaljobsearch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class Introduction extends Activity{

	
	TextView tv,tv1,tv2,title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		title = (TextView) findViewById(R.id.PageTitle);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/OLDENGL.TTF");
		title.setTypeface(tf);
		
		tv = (TextView)findViewById(R.id.link);
		tv.setText(Backend.getInstance().SERVER_URL);
		
		Spanned text1 = Html.fromHtml("<p align=\"justify\">This Application is used to Search the Jobs Added on the Scaleable Web App, hosted at Google app engine.</p>");
		Spanned text2 = Html.fromHtml("<p align=\"justify\">You can <u>ADD</u> new Jobs at the below link, provide AccessKey Password as <b>&apos;complete&apos;</b> to authorize your access.</p>");

		tv1 = (TextView)findViewById(R.id.text1);
		tv1.setText(text1);
		tv2 = (TextView)findViewById(R.id.text2);
		tv2.setText(text2);
		
		
	}
	
	
	public void Enter(View v)
	{
		Intent i = new Intent(this,MainActivity.class);
		startActivity(i);
	}
	
	
	public void VisitURL(View v){

		Linkify.addLinks((TextView)v, Linkify.WEB_URLS);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		tv.setText(Backend.getInstance().SERVER_URL);
	}
	
}
