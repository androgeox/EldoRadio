package geox.eldoradio;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener, MediaPlayer.OnPreparedListener{

    ImageButton play;
    MediaPlayer mPlayer;
    TextView txtView;

    final String DATA_STREAM = "http://emgspb.hostingradio.ru/eldoradio64.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (ImageButton)findViewById(R.id.playBtn);
        txtView = (TextView)findViewById(R.id.txtView);

        play.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        try{
            switch(v.getId()) {
                case (R.id.playBtn):
                    Log.d("info", "start stream");
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(DATA_STREAM);
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.setOnPreparedListener(this);
                    mPlayer.prepareAsync();
                    txtView.setText("Poehali");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();
    }
}
