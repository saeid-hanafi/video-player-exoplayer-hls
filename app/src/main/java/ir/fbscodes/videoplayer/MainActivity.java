package ir.fbscodes.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private String hlsUrl = "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8";
    private String url = "https://hajifirouz10.cdn.asset.aparat.com/aparat-video/e45a22ee2397be5af3fcc5bd7dd969a448423121-360p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImFkYmU1M2JkYWFlMmFmYTJiODhkYTdlMDIxYmRmYzY2IiwiZXhwIjoxNjY3MTUwNjMwLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.aBLlaJPQgR3mF8AkbdRPP_xzi627EIbCYwqDLbuq1J4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Video Player With ExoPlayer Library
        ExoPlayer player = new ExoPlayer.Builder(this).build();
        StyledPlayerView playerView = findViewById(R.id.exo_player_main);
        playerView.setPlayer(player);

        // Without HLS
//        // Build the media item.
//        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));
//        // Set the media item to be played.
//        player.setMediaItem(mediaItem);
//        // Prepare the player.
//        player.prepare();
//        // Start the playback.
//        player.play();

        // By HLS
        // Create a data source factory.
        DataSource.Factory dataSourceFactory = new DefaultHttpDataSource.Factory();
        // Create a HLS media source pointing to a playlist uri.
                HlsMediaSource hlsMediaSource =
                        new HlsMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(MediaItem.fromUri(Uri.parse(hlsUrl)));
        // Set the media source to be played.
                player.setMediaSource(hlsMediaSource);
        // Prepare the player.
                player.prepare();
                player.play();



        // Video Player With Android VideoView
//        videoView = findViewById(R.id.video_player);
//        videoView.setVideoPath(url);
//        videoView.start();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                MediaController mediaController = new MediaController(MainActivity.this);
//                mediaController.setMediaPlayer(videoView);
//                videoView.setMediaController(mediaController);
//                mediaController.setAnchorView(videoView);
//            }
//        });
//
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                videoView.start();
//            }
//        });
//
//        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                Snackbar.make(videoView, "Error: " + what + "Extra" + extra, Snackbar.LENGTH_LONG).show();
//                return false;
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}