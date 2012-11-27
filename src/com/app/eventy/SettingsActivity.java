package com.app.eventy;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class SettingsActivity extends Activity  implements OnSeekBarChangeListener {
	private static final String PREFS_NAME = "MyPrefsFile2";
	private SharedPreferences settings;
	private Editor editor;
	private SeekBar mSeekBar;
	private EditText mProgressText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		
        mSeekBar = (SeekBar)findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);
        mProgressText = (EditText)findViewById(R.id.number);
        mProgressText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            	
        }

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try{
					int progress=Integer.parseInt(s.toString());
					if(progress<0)
						progress=0;
					else if(progress>100)
						progress=100;
					mSeekBar.setProgress(progress);
				} catch(NumberFormatException e){
					e.printStackTrace();
					
				}
				
				
			}
      
});
        getSettings();   
    }

	public void onProgressChanged(SeekBar arg0, int progress, boolean fromTouch) {
		if(fromTouch)
			mProgressText.setText(String.valueOf(progress));
		
	}

	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
	public void getSettings() {
		int a=settings.getInt("promien", 0);
		mProgressText.setText(String.valueOf(settings.getInt("promien", 0)));
		getSingleSetting(R.id.imprezyOkolicznoscioweCheckBox,"imprezyOkolicznosciowe");
		getSingleSetting(R.id.imprezyCheckBox,"imprezy");
		getSingleSetting(R.id.kinoCheckBox,"kino");
		getSingleSetting(R.id.koncertyCheckBox,"koncerty");
		getSingleSetting(R.id.pokazyCheckBox,"pokazy");
		getSingleSetting(R.id.spektakleCheckBox,"spektakle");
		getSingleSetting(R.id.wydarzeniaeCheckBox,"wydarzenia");
		getSingleSetting(R.id.targiCheckBox,"targi");
		getSingleSetting(R.id.wystawyCheckBox,"wystawy");
		
	}
	
	public void getSingleSetting(int resId,String str){
		 CheckBox checkBox = (CheckBox) findViewById(resId);
		 boolean b=settings.getBoolean(str, false);
		 checkBox.setChecked(settings.getBoolean(str, false));
		
	}
	
	
	public void saveSettings(View view) {
		try{
			int progress=Integer.parseInt(mProgressText.getEditableText().toString());
			editor.putInt("promien", progress);
		} catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		saveSingleSetting(R.id.imprezyOkolicznoscioweCheckBox,"imprezyOkolicznosciowe");
		saveSingleSetting(R.id.imprezyCheckBox,"imprezy");
		saveSingleSetting(R.id.kinoCheckBox,"kino");
		saveSingleSetting(R.id.koncertyCheckBox,"koncerty");
		saveSingleSetting(R.id.pokazyCheckBox,"pokazy");
		saveSingleSetting(R.id.spektakleCheckBox,"spektakle");
		saveSingleSetting(R.id.wydarzeniaeCheckBox,"wydarzenia");
		saveSingleSetting(R.id.targiCheckBox,"targi");
		saveSingleSetting(R.id.wystawyCheckBox,"wystawy");
		editor.commit();
		this.finish();
		
	}
	public void saveSingleSetting(int resId,String str){
		 CheckBox checkBox = (CheckBox) findViewById(resId);
	        if (checkBox.isChecked())
	        	editor.putBoolean(str, true);
	         else
	        	 editor.putBoolean(str, false);
		
	}
    
}
