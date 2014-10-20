package com.frba.abclandia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.abclandia.GameActivity;
import com.example.abclandia.GameDataStructure;

public class NivelesActivity extends Activity implements OnClickListener{
	
	private Class<?> exerciseClass;
	
	private Button btnNivel1, btnNivel2, btnNivel3;
	
	protected void onCreate(Bundle paramBundle){
	super.onCreate(paramBundle);
		
	  Bundle extras = getIntent().getExtras();
      if (extras !=null) {
      	int exerciseNumber = extras.getInt(GameActivity.INTENT_EXERCISE_NUMBER);
      	exerciseClass = GameDataStructure.getExerciseClass(exerciseNumber);
        
      }
      
      
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.niveles_activity);
		btnNivel1 = (Button) findViewById(R.id.btnNivel1);
		btnNivel2 = (Button) findViewById(R.id.btnNivel2);
		btnNivel3 = (Button) findViewById(R.id.btnNivel3);
		
		btnNivel1.setOnClickListener(this);
		btnNivel2.setOnClickListener(this);
		btnNivel3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int levelNumber = 1;
		if (btnNivel1 == v){
			levelNumber = 1;
		}
		
		if (btnNivel2 == v){
			levelNumber = 2;
		}
		if (btnNivel3 == v){
			levelNumber = 3;
		}
		
		Intent intent = new Intent(this, exerciseClass);
		intent.putExtra(GameActivity.INTENT_LEVEL_KEY, levelNumber);
		intent.putExtra(GameActivity.INTENT_SECUENCE_KEY, 1);
		startActivity(intent);
		
		
		
	}
	

}
