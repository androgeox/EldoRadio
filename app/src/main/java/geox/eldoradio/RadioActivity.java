package geox.eldoradio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Georgiy on 11.03.2016.
 */
public class RadioActivity extends Activity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {


    ImageButton play;
    ImageButton stop;
    ImageButton pause;
    MediaPlayer mPlayer;
    boolean bIcon;

    public static final String EXTRA_STATIONNO = "stationNo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_activity);

        try {

            int stationNo = (Integer) getIntent().getExtras().get(EXTRA_STATIONNO);
            Station station = Station.stations[stationNo];

            TextView  name = (TextView) findViewById(R.id.txtView);
            name.setText(station.getName());

            ImageView photo = (ImageView)findViewById(R.id.photo);
            photo.setImageResource(station.getImageResourceId());

        }catch (Exception e){e.printStackTrace();}

        play = (ImageButton) findViewById(R.id.playBtn);
        stop = (ImageButton) findViewById(R.id.stopBtn);
        pause = (ImageButton) findViewById(R.id.pauseBtn);






        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case (R.id.playBtn):
                    Log.d("info", "start play");
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(Station.dataStream);
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.setOnPreparedListener(this);



                    if (bIcon) {
                        play.setImageResource(R.drawable.play60);
                        if(mPlayer.isPlaying()){

                            mPlayer.stop();
                        }else {
                            mPlayer.prepareAsync();
                            mPlayer.start();}



                    } else
                        play.setImageResource(R.drawable.pause60);
                        mPlayer.pause();



                    bIcon = !bIcon;
                    break;

                case (R.id.stopBtn):
                    stop.setImageResource(R.drawable.stop60);
                    mPlayer.stop();
                    mPlayer.release();
                    //mPlayer.reset();
                    //mPlayer.prepare();

                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
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