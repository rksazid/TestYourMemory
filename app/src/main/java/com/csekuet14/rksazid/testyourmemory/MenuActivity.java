package com.csekuet14.rksazid.testyourmemory;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button playButton,instructionButton;
    private MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        buttonSound =  MediaPlayer.create(MenuActivity.this, R.raw.bubble);

        playButton = (Button) findViewById(R.id.play_button);
        instructionButton = (Button) findViewById(R.id.instruction_button);



        final Animation playAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2,20);
        playAnimation.setInterpolator(interpolator);
        playAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                buttonSound.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MenuActivity.this,GameActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        playButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        playButton.startAnimation(playAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });

        final Animation insAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        insAnimation.setInterpolator(interpolator);
        insAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                buttonSound.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MenuActivity.this,InstructionActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        instructionButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        instructionButton.startAnimation(insAnimation);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return false;
            }
        });


    }
}
