package es.gameapp.multigameapp.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;

public class MediaPController implements MediaController.MediaPlayerControl{
    //-----------------Variables-------------------------------------------------
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private Uri uri;
    private Context context;

    //--------------------Constructors------------------------------------------
    public MediaPController(Context c){
        //This takes context from activity, and sets it. This works better to call initMediaPlayer() several times
        //REMEMBER to CLOSE the media player after.
        this.context=c;
    }

    MediaPController(Context c, Uri resource){
        //this takes context from activity and the uri of the mp3, then starts playing the audio
        //REMEMBER to CLOSE the media player after.
        this.context=c;
        this.uri = resource;
        initMediaPlayer();
    }

    //------------------Custom Methods-------------------------------------------

    private void initMediaPlayer(){
        //Initialize components
        mediaPlayer = new MediaPlayer();
        mediaController = new MediaController(context);
        mediaController.setMediaPlayer(this);
        //Sets datasource for the controler. A song a time.
        try {
            mediaPlayer.setDataSource(context, uri);
        } catch (IOException e) {
            Toast.makeText(context, "error on datasource", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        //Prepares mediaplayer
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(context, "error on prepare", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener( mp -> mediaController.show(20000) );
        //Starts music
        mediaPlayer.start();
    }

    public void initMediaPlayer(Uri resource){
        //Initialize components
        this.uri=resource;
        mediaPlayer = new MediaPlayer();
        mediaController = new MediaController(context);
        mediaController.setMediaPlayer(this);
        //Sets datasource for the controler. A song a time.
        try {
            mediaPlayer.setDataSource(context, uri);
        } catch (IOException e) {
            Toast.makeText(context, "error on datasource", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        //Prepares mediaplayer
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(context, "error on prepare", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener( mp -> mediaController.show(20000) );
        //Starts music
        mediaPlayer.start();
    }

    public void closeMediaPlayer(){
        //REMEMBER TO CLOSE MP AT THE END
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    //-----------------------Default Methods--------------------------------------------
    //This methods must be here because of the 'implements' statement. They can be used as you please.
    @Override
    public void start() {

    }

    @Override
    public void pause() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int i) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
