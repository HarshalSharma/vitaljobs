package com.harshal.vitaljobsearch;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.harshal.entities.JobRestPassable;
import com.harshal.entities.JobsList;

public class Results extends ActionBarActivity{

	TextView tv;
	ListView listview;
	JobsList jobsList;
	Backend backend;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutresults);
		
		backend = Backend.getInstance();
		
		jobsList = backend.getSetOfJobs();
		
		tv = (TextView) findViewById(R.id.resultsTitle);
		listview = (ListView) findViewById(R.id.Resultslistview);
		
		String Where = "";
		if(backend.Where.trim().length() > 0)
			Where = "at <b><i>" + backend.Where + "<p style=\" \"></b></i> ";
		String What = "";
		if(backend.What.trim().length() >0 )
		What =  "for <b><i>" + backend.What +  "</p></i></b> ";
		
		int size =  backend.getSetOfJobs().size();
		
		Spanned text = Html.fromHtml(size + " results found " + Where + What);
		
		tv.setText(text);
		
		myAdaptor adp = new myAdaptor(getApplicationContext(),jobsList);
		
		listview.setAdapter(adp);
	}
	
	
	class myAdaptor extends BaseAdapter{

		Context context;
		ArrayList<JobRestPassable> list;
		LayoutInflater inflator = null;
		
		public myAdaptor(Context context,ArrayList<JobRestPassable> list) {
			this.context = context;
			this.list = list;
			inflator = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}
		
		public class Holder{
			TextView tv1;
			TextView tv2;
			TextView tv3;
			
		}
		
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			
			Holder holder = new Holder();
			View rowView = inflator.inflate(R.layout.resultsitem,null);
			holder.tv1 = (TextView) rowView.findViewById(R.id.ResultslistitemTitle);
			holder.tv2 = (TextView) rowView.findViewById(R.id.ResultslistitemDescription);
			holder.tv3 = (TextView) rowView.findViewById(R.id.ResultslistitemLocations);
						
			final JobRestPassable job = list.get(position);
			holder.tv1.setText(job.JobTitle);
			holder.tv2.setText(job.JobDescription);
			holder.tv3.setText(job.Locations.toString());
			
			rowView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(context,JobView.class);
					i.putExtra("JOB",job);
					startActivity(i);
//					Toast.makeText(getApplicationContext(), list.get(position).JobTitle , 2000).show();
				}
			});
			return rowView;
		}
		
		
	}
	
}
