package com.harshal.vitaljobsearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.restlet.engine.Engine;
import org.restlet.ext.jackson.JacksonConverter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.harshal.entities.JobsList;
import com.harshal.entities.QueryRestPassable;


public class MainActivity extends ActionBarActivity {

	EditText NationalityInput,JobsKindInput;
	ProgressBar pb;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		TextView title = (TextView) findViewById(R.id.activity_main_PageTitle);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/OLDENGL.TTF");
		title.setTypeface(tf);
		
        NationalityInput = (EditText) findViewById(R.id.NationalityInput);
        JobsKindInput = (EditText) findViewById(R.id.JobsKindInput);
     
        pb = (ProgressBar) findViewById(R.id.pb);
    
//        fillSpinners();	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main, menu);
       return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    
    	switch (item.getItemId()) {
		case R.id.action_settings:
	    	Intent i = new Intent(this,ChangeURL.class);
	    	startActivity(i);
	    	return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    public void ShowAll(View v)
    {
    	getResults(false);
    }
    
    //On Search button click
    public void SearchClicked(View v)
    {
    	getResults(true);
    }
    
    
    /*
    //Load values in the spinner
    public void fillSpinners(){
        
        ArrayList<String> nationslist = new ArrayList<String>();
        for(Nationality n : Nationality.values()){
        	nationslist.add(n.name());
        }
      //  NationalitySpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,nationslist));
   	
        ArrayList<String> jobKindslist = new ArrayList<String>();
        for(JobKinds n : JobKinds.values()){
        	jobKindslist.add(n.getName());
        }
       // JobKindsSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,jobKindslist));
       
        
        ArrayList<String> paymentslist = new ArrayList<String>();
        for(PaymentOptions n : PaymentOptions.values()){
        	paymentslist.add(n.name());
        }
      //  PaymentSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,paymentslist));
    }
    */
    
    public void getResults(Boolean check){
    	
    	final Boolean ch = check;
    	
    	new AsyncTask<Void, Void, Void>() {

    		JobsList set;
    		Backend back;
    		Boolean success = true;
    		Boolean c;
    		
    		
    		protected void onPreExecute() 
    		{
    	     	showPB();

    	     	back = Backend.getInstance();
    	     	
				back.What = JobsKindInput.getText().toString();
				back.Where = NationalityInput.getText().toString();
				
				
    	     	
/*    			CountryPreference = Nationality.values()[NationalitySpinner.getSelectedItemPosition()];
    			jobkind = JobKinds.values()[JobKindsSpinner.getSelectedItemPosition()];
    			payOption = PaymentOptions.values()[PaymentSpinner.getSelectedItemPosition()];
*/
    	     	c = ch;
    			
    		};
    		
			@Override
			protected Void doInBackground(Void... params) {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
				StrictMode.setThreadPolicy(policy);
				Engine.getInstance().getRegisteredConverters()
				.add(new JacksonConverter());
				
				
				try{
					if(!c){
						Log.e("here",back.getServer_Search_Url());
						set = back.getSearchConnector().AllJobs();
					}else{
						QueryRestPassable q = new QueryRestPassable();
						String where = back.Where;
						List<String> l = Arrays.asList(where.split(","));
						q.Where = new HashSet<String>();
						q.Where.addAll(l);
						System.out.println("SET:" + q.Where.toString());
						q.What = back.What;
						
						set = back.getSearchConnector().SearchJobs(q);
						if(set.Message == "NO_SUCH_LOCATION")
						{
							return null;
						}
					}
					back.SetAvailableJobs(set);
				}
				catch(Exception e)
				{
					success = false;
					e.printStackTrace();
					Log.e("ERROR:", e.toString());
				}
				
				return null;
			}
    		
			protected void onPostExecute(Void result) 
			{
				hidePB();
				if(success){
					IntentToResults();
				}
				else{
					
					if(set!= null && set.Message.equals("NO_SUCH_LOCATION"))
					{
						Toast.makeText(getApplicationContext(),"No Jobs for that Location!!",3000).show();
					}
					else{
						Toast.makeText(getApplicationContext(), "Unable to Contact the Server", 2000).show();
					}
				}
			};
		}.execute();

    }
    
    public void IntentToResults(){
    	int size = Backend.getInstance().getSetOfJobs().size();
    	
    	if(size==0)
    	{
    		Toast.makeText(getApplicationContext(),"No Jobs are present at server.",2000).show();
    		return;
    	}
    	Intent i = new Intent(this,Results.class);
    	startActivity(i);
    }
    
    //Show hide progress bar
    public void showPB(){
    	pb.setVisibility(pb.VISIBLE);	
    }
    
    public void hidePB(){
    	pb.setVisibility(pb.GONE);
    }
    

}
