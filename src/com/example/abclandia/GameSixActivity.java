package com.example.abclandia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.example.abclandia.audio.Audio;
import com.example.abclandia.graphics.CompleteCardRenderer;
import com.example.abclandia.graphics.EOneMatchedRenderer;
import com.example.abclandia.graphics.JustImageRenderer;
import com.example.abclandia.graphics.JustLetterRenderer;
import com.example.abclandia.graphics.Renderer;
import com.frba.abclandia.R;
import com.frba.abclandia.adapters.CardViewAdapter;
import com.frba.abclandia.db.DataBaseHelper;

public class GameSixActivity extends GameActivity {
	
public static final int TOTAL_JOINS = 6;
	
	
	private DragLayer mDragLayer;
	private GridView mGridViewLeft,mGridViewCenter, mGridViewRight ;
	private List<Card> data;
	private Renderer mFirstMatchRenderer;
	private static String CLASS_NAME = "com.example.abclandia.GameOne";
	private static int GAME_NUMBER = 6;

	

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = getIntent().getExtras();
        if (extras !=null) {
        	mCurrrentLevel = extras.getInt(GameActivity.INTENT_LEVEL_KEY);
          
        }
       
        setFullScreen();
        setSizes();
        
        
        mDragController = new DragController (this);
        loadDataCard();
        
        
       
        setContentView(R.layout.game_six);
        mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
        mDragLayer.setDragController (mDragController);
        mDragController.setDragListener (mDragLayer);
        mDroppedRenderer = new EOneMatchedRenderer(this);
        
        
       mAudio = new Audio(this);
       mAudio.loadWordSounds(data);
        
      
        
        
       
       
  
     
        

  
        
        loadDataCard();
        
       
        
        mGridViewLeft = (GridView) findViewById(R.id.gridViewLeft);
        mGridViewRight = (GridView) findViewById(R.id.gridViewRight);
        mGridViewCenter = (GridView) findViewById(R.id.gridViewCenter);
       
        
        mGridViewRight.setAdapter(new CardViewAdapter(data, this, new JustLetterRenderer(this),R.layout.grid_row));
        mGridViewLeft.setAdapter(new CardViewAdapter(data, this, new JustImageRenderer(this),R.layout.grid_row));
        mGridViewCenter.setAdapter(new CardViewAdapter(data, this, new JustImageRenderer(this),R.layout.grid_row));
        
        mDragLayer = (DragLayer) findViewById(R.id.drag_layer);
        mDragLayer.setDragController (mDragController);
        
        mDragLayer.setGridViewLeft(mGridViewLeft);
        mDragLayer.setGridViewCenter(mGridViewCenter);
        mDragLayer.setGridViewRight(mGridViewRight);

        mDragController.setDragListener (mDragLayer);
        mDroppedRenderer = new EOneMatchedRenderer(this);
        mFirstMatchRenderer = new JustLetterRenderer(this);
        
       mAudio = new Audio(this);
       mAudio.loadWordSounds(data);
        
        
       
        
      }





   

	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
	    return super.onTouch(v, event);
	}

	public boolean startDrag (View v)
	{
		super.startDrag(v);
	    

	    return true;
	}
	
	public Renderer getMatchedRenderer(){
		return mDroppedRenderer;
	}
	

	public Renderer getFirstMatchedRenderer(){
		return mFirstMatchRenderer;
		
	}
	@Override
	public void onDragEnd(boolean success) {
		if (success) {
		countHits++;
		if (countHits == TOTAL_JOINS) {
			Intent intent = new Intent(this, WinActivity.class);
			intent.putExtra(GameActivity.INTENT_LEVEL_KEY, mCurrrentLevel);
			intent.putExtra(GameActivity.INTENT_SECUENCE_KEY, secuence);
			intent.putExtra(GameActivity.INTENT_CLASS_LAUNCHER_KEY, CLASS_NAME);
			startActivity(intent);
			finish();
			   
			
		}
		}
		
	}
	
//	private void iniciarDB() {
//		// Inicializar servicios
//		myDbHelper = new DataBaseHelper(this);
//		try {
//			myDbHelper.createDatabase();
//		} catch (IOException ioe) {
//			throw new Error("No se pudo crear la base de datos");
//			
//		}
//		
//		try {
//			myDbHelper.openDatabase();
//		}catch (SQLException sqle){
//			Log.d("POOCHIE", "No se pudo abrir la BD");
//			throw sqle;
//		}
//		
//	}
	

	

}
