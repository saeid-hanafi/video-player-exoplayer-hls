package ir.fbscodes.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video_player);
        videoView.setVideoPath("https://hajifirouz10.cdn.asset.aparat.com/aparat-video/e45a22ee2397be5af3fcc5bd7dd969a448423121-360p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImFkYmU1M2JkYWFlMmFmYTJiODhkYTdlMDIxYmRmYzY2IiwiZXhwIjoxNjY3MTUwNjMwLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.aBLlaJPQgR3mF8AkbdRPP_xzi627EIbCYwqDLbuq1J4");
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                MediaController mediaController = new MediaController(MainActivity.this);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
                mediaController.setAnchorView(videoView);
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Snackbar.make(videoView, "Error: " + what + "Extra" + extra, Snackbar.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}