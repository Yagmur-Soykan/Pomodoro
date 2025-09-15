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

public class MainActivity extends AppCompatActivity {

    private CircularSeekBar circularSb;
    private TextView txt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button);
        txt = (TextView) findViewById(R.id.textView);
        txt.setText("25:00");

        circularSb = (CircularSeekBar) findViewById(R.id.circularSb);

        circularSb.setEnabled(false);


        circularSb.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(@Nullable CircularSeekBar circularSeekBar, float v, boolean b) {
                updateTime();
            }

            @Override
            public void onStopTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

            }

            @Override
            public void onStartTrackingTouch(@Nullable CircularSeekBar circularSeekBar) {

            }
        });

    }


    public void updateTime()
    {
        int saniye = (int) circularSb.getProgress(); /*getProgress float bir değer döndürüyor*/
        int dakika = saniye / 60;
        int kalanSaniye = saniye % 60;
        String str = "";

        if (dakika <= 9) {
            str += "0" + dakika + ":";
        } else {
            str += dakika + ":";
        }

        if (kalanSaniye <= 9) {
            str += "0" + kalanSaniye;
        } else {
            str += kalanSaniye;
        }

        txt.setText(str);
    }

    CountDownTimer cdt;
    public void buttonClick(View v)

    {
        //attribute kısmında cs_max:1500 saniye (25dk) olarak ayarlandı
        //cs_progress'de 1500 saniye olarak ayarlandı ki en sondan başa azalarak gitsin
        cdt= new CountDownTimer((int) circularSb.getProgress() * 1000, 1000)
            //İlk parametre: Geri sayımın toplam süresi (millisaniye cinsinden)
            //İkinci parametre: Geri sayımın her bir adımının süresi (millisaniye cinsinden).
        {
            @Override
            public void onTick(long l) {
            /// Geri sayım süresi bitene kadar her bir saniyede bir burası çalışır
                circularSb.setProgress(circularSb.getProgress() - 1); //seekbar görünümünü değiştir

            }

            @Override
            public void onFinish() {
                circularSb.setProgress(0); /*son 0.saniyeyi göstermiyordu bu yüzden bu satır eklenildi (1.saniyede bitiyordu) */

                MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.alarm);
                btn.setText("BREAK");

                Intent myIntent = new Intent(MainActivity.this , MainActivity2.class);
                startActivity(myIntent);
                mp.start();
            }
        };

        cdt.start();

    }

}

