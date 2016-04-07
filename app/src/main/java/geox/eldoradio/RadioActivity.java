package geox.eldoradio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import geox.eldoradio.Station;

/**
 * Created by Georgiy on 11.03.2016.
 */
public class RadioActivity extends Activity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {


    private ImageButton play;
    private ImageButton stop;
    private ImageButton pause;
    private MediaPlayer mPlayer;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private Handler h;
    private int mProgressStatus = 0;

    public static final String EXTRA_STATIONNO = "stationNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_activity);

        play = (ImageButton) findViewById(R.id.playBtn);
        stop = (ImageButton) findViewById(R.id.stopBtn);
        pause = (ImageButton) findViewById(R.id.pauseBtn);
        toggleButton = (ToggleButton) findViewById(R.id.toggleBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        toggleButton.setOnClickListener(this);

        /* Установка картинки на кнопку */
        play.setImageResource(R.drawable.play60);
        stop.setImageResource(R.drawable.stop60);
        pause.setImageResource(R.drawable.pause60);

        /* Указание интенту какую запись брать из массива */

            int stationNo = (Integer) getIntent().getExtras().get(EXTRA_STATIONNO);
            Station station = Station.stations[stationNo];

        /* Получение названия станции */
        TextView  name = (TextView) findViewById(R.id.txtView);
        name.setText(station.getName());

        /* Получение лого станции */
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(station.getImageResourceId());

        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(station.getDataStream());
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        h = new Handler() {
            @Override
            public void close() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mProgressStatus<100){
                    mProgressStatus = doWork();

                    //update the progressBar
                    play.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        }).start();
    }

    private int doWork() {
        //int delta = mPlayer.getCurrentPosition()-mProgressStatus;
        return mPlayer.getCurrentPosition();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.playBtn):
                mPlayer.prepareAsync();
                mPlayer.start();
                break;

            case (R.id.stopBtn):
                if (mPlayer.isPlaying()) {

                        mPlayer.stop();
                    }
                break;
            case (R.id.pauseBtn):
                mPlayer.pause();
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

}