package com.example.mseekbartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.example.mseekbar.MySeekBar;

public class MainActivity extends AppCompatActivity {
    MySeekBar msb1;
    MySeekBar msb2;
    MySeekBar msb3;
    MySeekBar msb4;
    MySeekBar msb5;
    MySeekBar msb6;
    MySeekBar msb7;
    MySeekBar msb8;
    MySeekBar msb9;
    MySeekBar msb10;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msb1 = findViewById(R.id.msb1);
        msb2 = findViewById(R.id.msb2);
        msb3 = findViewById(R.id.msb3);
        msb4 = findViewById(R.id.msb4);
        msb5 = findViewById(R.id.msb5);
        msb6 = findViewById(R.id.msb6);
        msb7 = findViewById(R.id.msb7);
        msb8 = findViewById(R.id.msb8);
        msb9 = findViewById(R.id.msb9);
        msb10 = findViewById(R.id.msb10);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msb1.setProgress(progress);
                msb2.setProgress(progress);
                msb3.setProgress(progress);
                msb4.setProgress(progress);
                msb5.setProgress(progress);
                msb6.setProgress(progress);
                msb7.setProgress(progress);
                msb8.setProgress(progress);
                msb9.setProgress(progress);
                msb10.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        msb1.setProgress(200);
    }
}
