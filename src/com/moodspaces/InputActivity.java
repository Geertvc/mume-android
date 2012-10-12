package com.moodspaces;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class InputActivity extends Activity {
	
	private Spinner spinner;
	private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        addItemsOnSpinner();
        addListenerOnButton();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_input, menu);
        return true;
    }
	
	public void submitInput(){
		System.out.println("TODO save the input in the database here");
	}

    private void addItemsOnSpinner() {
    	spinner = (Spinner) findViewById(R.id.spinner);
    	List<String> list = new ArrayList<String>();
    	list.add("Mume");
    	list.add("Capsel");
    	list.add("Thesis");
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
    		android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(dataAdapter);
	}
	
	public void addListenerOnButton() {
		 
		spinner = (Spinner) findViewById(R.id.spinner);
		submitButton = (Button) findViewById(R.id.submitButton);
	 
		submitButton.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	 
		    Toast.makeText(InputActivity.this,
			"OnClickListener : " + 
	                "\nSpinner : "+ String.valueOf(spinner.getSelectedItem()),
				Toast.LENGTH_SHORT).show();
		  }
	 
		});
	  }
}
