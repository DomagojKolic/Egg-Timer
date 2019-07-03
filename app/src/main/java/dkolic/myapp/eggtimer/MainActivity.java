package dkolic.myapp.eggtimer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    boolean counterIsActive=false;
    Button startStopButton;
    CountDownTimer countDownTimer;
      public void startTimer(View view){
          if(counterIsActive){
              textView.setText(String.valueOf(seekBar.getProgress()));
              seekBar.setProgress(30);
              seekBar.setEnabled(true);
              countDownTimer.cancel();
              startStopButton.setText("GO!");
              counterIsActive=false;
          }
          else {
              startStopButton = findViewById(R.id.startStopButton);
              counterIsActive = true;
              seekBar.setEnabled(false);
              startStopButton.setText("STOP!");
              countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                  @Override
                  public void onTick(long millisUntilFinished) {
                      long remainginTime = millisUntilFinished / 1000;
                      updateTimer((int) remainginTime);

                  }

                  @Override
                  public void onFinish() {
                      MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                      mediaPlayer.start();

                  }
              }.start();
          }
      }
      public void updateTimer (int secondsLeft){
          int minutes = secondsLeft/60;
          int seconds=secondsLeft-(minutes*60);
          String secondsString = Integer.toString(seconds);
          if(secondsString.equals("0")){
              secondsString="00";
          }

          textView.setText(Integer.toString(minutes)+":"+secondsString);

      }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.timeDisplay);
        textView.setText(String.valueOf(seekBar.getProgress()));

        int max=600;
        seekBar.setMax(max);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateTimer(progress);
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