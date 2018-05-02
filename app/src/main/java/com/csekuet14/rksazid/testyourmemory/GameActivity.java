package com.csekuet14.rksazid.testyourmemory;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private MediaPlayer pianoA,pianoA1,pianoB,pianoC,pianoD,pianoB1,pianoC1,pianoD1,failedSound,pianoA2,pianoB2,pianoC2,pianoD2;
    private Button b1,b2,b3,b4,playAgainButton;
    private int pAcount=0,pBcount=0,pCcount=0,pDcount=0;
    private String randomSeqOfSound = "";
    private int level1Range = 40,noOfButtonTap=0,stSeq=0;
    private LongOperation longOp;
    private TextView lvlTextView,infoTextView,playAgainTextView;
    private int speedAmount = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        speedAmount = 400;

        buttonsInitialization();
        pianoInitialization();
        textViewInitialization();
        randomStringSequenceGenerator();

        longOp = new LongOperation();
        longOp.execute();
    }

    private void textViewInitialization() {
        lvlTextView = (TextView) findViewById(R.id.no_of_turn_textView);
        infoTextView = (TextView) findViewById(R.id.listen_yourTurn_textView);
        playAgainTextView = (TextView) findViewById(R.id.play_again_textView);
        playAgainTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        longOp.cancel(true);
        //if (longOp != null && longOp.getStatus() != AsyncTask.Status.FINISHED)
        //longOp.cancel(true);
    }

    private void randomStringSequenceGenerator() {
        Random rand = new Random();
        for(int i = 0;i<level1Range;i++){
            randomSeqOfSound+=Integer.toString(rand.nextInt(4));
        }
        Log.d("TAG","SEQ : "+randomSeqOfSound);
    }

    private void pianoInitialization() {
        pianoA = MediaPlayer.create(GameActivity.this, R.raw.piano_a);
        pianoB = MediaPlayer.create(GameActivity.this, R.raw.piano_b);
        pianoC = MediaPlayer.create(GameActivity.this, R.raw.piano_c);
        pianoD = MediaPlayer.create(GameActivity.this, R.raw.piano_d);

        pianoA1 = MediaPlayer.create(GameActivity.this, R.raw.piano_a);
        pianoB1 = MediaPlayer.create(GameActivity.this, R.raw.piano_b);
        pianoC1 = MediaPlayer.create(GameActivity.this, R.raw.piano_c);
        pianoD1 = MediaPlayer.create(GameActivity.this, R.raw.piano_d);

        pianoA2 = MediaPlayer.create(GameActivity.this, R.raw.piano_a);
        pianoB2 = MediaPlayer.create(GameActivity.this, R.raw.piano_b);
        pianoC2 = MediaPlayer.create(GameActivity.this, R.raw.piano_c);
        pianoD2 = MediaPlayer.create(GameActivity.this, R.raw.piano_d);

        failedSound = MediaPlayer.create(GameActivity.this, R.raw.fail_sound);
    }

    private void buttonsInitialization() {
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        playAgainButton = (Button) findViewById(R.id.play_again_button);
        playAgainButton.setVisibility(View.INVISIBLE);


        b1.setAlpha((float)0.3);
        b2.setAlpha((float)0.3);
        b3.setAlpha((float)0.3);
        b4.setAlpha((float)0.3);


        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b1.setAlpha((float)1.0);
                        playA();
                        break;
                    case MotionEvent.ACTION_UP:
                        b1.setAlpha((float)0.3);
                        checkTheSeqOfButtonPress('0');
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });

        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b2.setAlpha((float)1.0);
                        playB();
                        break;
                    case MotionEvent.ACTION_UP:
                        b2.setAlpha((float)0.3);
                        checkTheSeqOfButtonPress('1');
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });


        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b3.setAlpha((float)1.0);
                        playC();
                        break;
                    case MotionEvent.ACTION_UP:
                        b3.setAlpha((float)0.3);
                        checkTheSeqOfButtonPress('2');
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });



        b4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        b4.setAlpha((float)1.0);
                        playD();
                        break;
                    case MotionEvent.ACTION_UP:
                        b4.setAlpha((float)0.3);
                        checkTheSeqOfButtonPress('3');
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });
        playAgainButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        playAgainButton.setAlpha((float)0.3);

                        break;
                    case MotionEvent.ACTION_UP:
                        playAgainButton.setAlpha((float)1.0);
                        longOp = null;
                        longOp = new LongOperation();
                        longOp.execute();
                        playAgainButton.setVisibility(View.INVISIBLE);
                        playAgainTextView.setVisibility(View.INVISIBLE);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });
    }

    private void checkTheSeqOfButtonPress(char c){
        try {
            if (noOfButtonTap >= stSeq-1) {
                if (c != randomSeqOfSound.charAt(noOfButtonTap)) {
                    infoTextView.setText("Listen...");
                    randomSeqOfSound = "";
                    randomStringSequenceGenerator();
                    stSeq = 0;
                    lvlTextView.setText("" + stSeq);
                    noOfButtonTap = 0;
                    failedSound.start();
                    playAgainButton.setVisibility(View.VISIBLE);
                    playAgainTextView.setVisibility(View.VISIBLE);
                    return;
                }
                infoTextView.setText("Listen...");
                lvlTextView.setText(""+stSeq);
                longOp = null;
                longOp = new LongOperation();
                longOp.execute();
            }
            else if (c != randomSeqOfSound.charAt(noOfButtonTap)) {
                infoTextView.setText("Listen...");
                randomSeqOfSound="";
                randomStringSequenceGenerator();
                stSeq = 0;
                lvlTextView.setText(""+stSeq);
                noOfButtonTap=0;
                speedAmount = 400;
                failedSound.start();
                playAgainButton.setVisibility(View.VISIBLE);
                playAgainTextView.setVisibility(View.VISIBLE);
            }
            noOfButtonTap++;
        }catch (Exception e){
            Log.d("TAG",""+e);
        }
    }

    private void playD() {
        if(pDcount%3==0){
            pianoD.start();
        }else if(pDcount%3==1){
            pianoD1.start();
        }else{
            pianoD2.start();
        }
        pDcount++;
    }

    private void playC() {
        if(pCcount%3==0){
            pianoC.start();
        }else if(pCcount%3==1){
            pianoC1.start();
        }else{
            pianoC2.start();
        }
        pCcount++;
    }

    private void playB() {
        if(pBcount%3==0){
            pianoB.start();
        }else if(pBcount%3==1){
            pianoB1.start();
        }else{
            pianoB2.start();
        }
        pBcount++;
    }

    private void playA() {
        if(pAcount%3==0){
            pianoA.start();
        }else if(pAcount%3==1){
            pianoA1.start();
        }else{
            pianoA2.start();
        }
        pAcount++;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        public LongOperation() {

        }

        @Override
        protected String doInBackground(String... params) {
            if(stSeq==0)
                sleepTime(3000);
            else
                sleepTime(1000);

            for (int i = 0; i <= stSeq && i<randomSeqOfSound.length(); i++) {
                if(isCancelled()){
                    return "Finish";
                }
                playTheSound(randomSeqOfSound.charAt(i));
                highAlpha(randomSeqOfSound.charAt(i));
                sleepTime(speedAmount);
                lowAlpha(randomSeqOfSound.charAt(i));
                sleepTime(speedAmount);
            }
            return "Executed";
        }

        private void sleepTime(int i) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                Log.e("LongOperation", "Interrupted", e);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            stSeq++;
            infoTextView.setText("Your turn..");
            if(speedAmount>230){
                speedAmount-=20;
            }
            noOfButtonTap=0;
        }
    }

    private void playTheSound(char c) {
        switch (c){
            case '0':
                playA();
                break;
            case '1':
                playB();
                break;
            case '2':
                playC();
                break;
            case '3':
                playD();
                break;
            default:
        }
    }

    private void lowAlpha(char c) {
        switch (c){
            case '0':
                b1.setAlpha((float)0.3);;
                break;
            case '1':
                b2.setAlpha((float)0.3);;
                break;
            case '2':
                b3.setAlpha((float)0.3);;
                break;
            case '3':
                b4.setAlpha((float)0.3);;
                break;
            default:
        }
    }

    private void highAlpha(char c) {
        switch (c){
            case '0':
                b1.setAlpha((float)1.0);;
                break;
            case '1':
                b2.setAlpha((float)1.0);;
                break;
            case '2':
                b3.setAlpha((float)1.0);;
                break;
            case '3':
                b4.setAlpha((float)1.0);;
                break;
            default:
        }
    }
}
