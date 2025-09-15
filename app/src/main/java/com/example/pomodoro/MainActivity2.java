package com.example.pomodoro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainActivity2 extends AppCompatActivity {

    private Button btn2;
    private CircularSeekBar circularSb2;

    private TextView txt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        txt = (TextView) findViewById(R.id.textView);
        txt.setText("05:00");
        btn2 = (Button) findViewById(R.id.button2);

        circularSb2 = (CircularSeekBar) findViewById(R.id.circularSb2);

        circularSb2.setEnabled(false);

        circularSb2.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(@Nullable CircularSeekBar circularSeekBar, float v, boolean b) {
                updateTime2();
            }

            @Override
            public void onStopTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

            }

            @Override
            public void onStartTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

            }
        });

    }

    public void updateTime2()
    {
        int saniye = (int) circularSb2.getProgress();
        int dakika = saniye/60;
        int kalanSaniye = saniye%60;
        String str ="";

        if(dakika<=9) {
            str = "0"+dakika+":";
        }
        else {
            str = dakika +":";
        }
        if(kalanSaniye<=9) {
            str += "0"+kalanSaniye;
        }
        else{
            str += kalanSaniye;
        }

        txt.setText(str);
    }

    CountDownTimer cdt2;

    public void buttonClick2(View v)
    {


        cdt2 = new CountDownTimer((int) circularSb2.getProgress()*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                circularSb2.setProgress(circularSb2.getProgress() - 1);

            }

            @Override
            public void onFinish() {
                MediaPlayer mp = MediaPlayer.create(MainActivity2.this, R.raw.alarm);
                btn2.setText("START");

                Intent myIntent = new Intent(MainActivity2.this , MainActivity.class);
                startActivity(myIntent);
                mp.start();
            }
        };

        cdt2.start();
    }

}