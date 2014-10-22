package com.example.abclandia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.abclandia.audio.Audio;
import com.example.abclandia.graphics.CardView;
import com.example.abclandia.graphics.EOneMatchedRenderer;
import com.example.abclandia.graphics.JustLetterRenderer;
import com.example.abclandia.graphics.Renderer;
import com.frba.abclandia.R;
import com.frba.abclandia.adapters.CardViewAdapter;
import com.frba.abclandia.db.DataBaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.SQLException;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity implements View.OnTouchListener,
		DragController.DragListener {

	public static final int TOTAL_JOINS = 5;
	public static final String PACKAGE_NAME = "com.example.abclandia";
	public static final String INTENT_LEVEL_KEY = "level";
	public static final String INTENT_SECUENCE_KEY = "secuence";
	public static final String INTENT_CLASS_LAUNCHER_KEY = "class_launcher";
	public static final String INTENT_EXERCISE_NUMBER = "exercise";

	protected int countHits = 0;

	protected DragController mDragController;
	protected DragLayer mDragLayer;

	protected Renderer mDroppedRenderer;
	protected List<Card> data;

	private WindowManager.LayoutParams mWindowParams;
	private WindowManager mWindowManager;
	protected Audio mAudio;
	protected DataBaseHelper myDbHelper;

	protected int mCurrrentLevel = 0;
	protected int mCurrentSecuence = 0;
	protected int mGameNumber = 0;
	protected int secuence = 0;
	
	// Definimos las variables para saber que Maestro, Alumno y Categoria estan involucrados. 
	private int unMaestro = 0;
	private int unAlumno = 0;
	private int unaCategoria = 0;



	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullScreen();
		setSizes();
		

		
		iniciarDB();

	}

	protected void setFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mWindowManager = (WindowManager) getSystemService("window");
	}

	protected void setSizes() {
		Configuration config = getResources().getConfiguration();
		if (config.smallestScreenWidthDp >= 720) {
			Renderer.TEXT_LETTER_SIZE = 30;
			Renderer.TEXT_WORD_SIZE = 25;
		} else if (config.smallestScreenWidthDp >= 600) {
			Renderer.TEXT_LETTER_SIZE = 20;
			Renderer.TEXT_WORD_SIZE = 18;
		}

	}

	protected void loadDataCard() {
		data = new ArrayList<Card>();
		char[] secuences = GameDataStructure.getSecuence(mGameNumber,
				mCurrrentLevel, secuence);
		for (int i = 0; i < secuences.length; i++) {
			String letter = String.valueOf(secuences[i]).toUpperCase();
			Card card = myDbHelper.getPalabraFromLetraAndCategoria(letter, unaCategoria);
			card.setLetterType(2);
			data.add(card);
		}
	}
	
		private void iniciarDB() {
			// Inicializar servicios
			myDbHelper = new DataBaseHelper(this);
			try {
				myDbHelper.createDatabase();
			} catch (IOException ioe) {
				throw new Error("No se pudo crear la base de datos");
				
			}
			
			try {
				myDbHelper.openDatabase();
			}catch (SQLException sqle){
				Log.d("POOCHIE", "No se pudo abrir la BD");
				throw sqle;
			}
			
		}
		
	protected void getExtraData(){
		
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {

		boolean handledHere = false;
		CardView cardView = (CardView) v;
		final int action = event.getAction();

		// In the situation where a long click is not needed to initiate a drag,
		// simply start on the down event.
		if (action == MotionEvent.ACTION_DOWN) {

			if (!cardView.isEmptyCard() && cardView.allowDrag()) {
				if (cardView.getRenderer().getClass() == JustLetterRenderer.class){
				mAudio.playSoundLetter(cardView.getCardId().toLowerCase());
				} else 
					mAudio.playSoundWord(cardView.getCardId());

			}

			handledHere = startDrag(v);
		}

		return handledHere;
	}

	public boolean startDrag(View v) {
		DragSource dragSource = (DragSource) v;

		// We are starting a drag. Let the DragController handle it.
		mDragController.startDrag(v, dragSource, dragSource,
				DragController.DRAG_ACTION_MOVE);

		return true;
	}

	public Renderer getMatchedRenderer() {
		return mDroppedRenderer;
	}

	@Override
	public void onDragStart(DragSource source, Object info, int dragAction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDragEnd(boolean success) {

	}
	
	

}
