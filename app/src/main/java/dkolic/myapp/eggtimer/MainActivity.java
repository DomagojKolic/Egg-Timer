package dkolic.myapp.eggtimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int timeNumber;
    SeekBar seekBar;
    TextView timeDisplay;

    public void startTimer(View view) {
        Button startStopButton = findViewById(R.id.startStopButton);
        Log.i("Button is pressed", "idemo");
        //SeekBar seekBar= findViewById(R.id.seekBar);
        int time= seekBar.getProgress()*1000;
        seekBar.setEnabled(false);


        CountDownTimer countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Timer", String.valueOf(millisUntilFinished/1000));
                timeDisplay.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = findViewById(R.id.seekBar);
        timeDisplay=findViewById(R.id.timeDisplay);

        final int startingPosition = 30;
        int max = 60;
        seekBar.setMax(max);
        seekBar.setProgress(startingPosition);
        Log.i("Button is pressed", "woho");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                if (progress < min) {
                    progress = min;
                    seekBar.setProgress(min);
                }
                timeDisplay.setText(String.valueOf(progress));

                Log.i("SeekBar state", String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
