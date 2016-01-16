package com.harshal.vitaljobsearch;

import com.harshal.entities.JobRestPassable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class JobView extends ActionBarActivity{

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.job_view);
	
		JobRestPassable job  = null;
		job = (JobRestPassable) getIntent().getExtras().getSerializable("JOB");
		
		TextView title = (TextView) findViewById(R.id.jobViewTitle);
		Spanned text = Html.fromHtml("<u>" + job.JobTitle + "</u>");
		title.setText(job.JobTitle);
		
		TextView description = (TextView) findViewById(R.id.jobViewDescription);
		description.setText(job.JobDescription);
		
		TextView locations = (TextView) findViewById(R.id.jobViewLocations);
		text = Html.fromHtml("<b>Locations:</b>" + job.Locations.toString());
		locations.setText(text);
		
		TextView kind = (TextView) findViewById(R.id.jobViewjobKind);
		text = Html.fromHtml("<b>Category:</b>" + job.jobKind.getName());
		kind.setText(text);
		
		TextView paytype = (TextView) findViewById(R.id.jobViewpayOptions);
		text = Html.fromHtml("<b>Pay Type:</b>" + job.payOptions.name());
		paytype.setText(text);
	}
	
}
