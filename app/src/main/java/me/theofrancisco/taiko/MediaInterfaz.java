package me.theofrancisco.taiko;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MediaInterfaz extends AppCompatActivity {

    private static MediaPlayer mediaPlayer;
    private ArrayList<Song> songList;
    private int currentSongPos;
    private TextView songTitleView;
    private TextView songArtistView;
    private ImageView songIcon;
    // Used when update audio progress thread send message to progress bar handler.
    private static final int UPDATE_AUDIO_PROGRESS_BAR = 3;
    // Wait update audio progress thread sent message, then update audio play progress.
    private static Handler progressHandler;

    private boolean audioIsPlaying = true;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_interfaz);

        songTitleView = findViewById(R.id.textSongTitle);
        songArtistView = findViewById(R.id.textArtist);
        progressBar = findViewById(R.id.progressBar);
        songIcon = findViewById(R.id.songImage);

        /* Initialize audio progress handler. */
        if (progressHandler == null) {
            progressHandler = new MyHandler(progressBar);
        }


        SongTransfer songTransfer = (SongTransfer) getIntent().getSerializableExtra(TaikoActivity.SONGS);
        currentSongPos = songTransfer.getPosition();
        songList = songTransfer.getSongs();

        // Create the thread.
        Thread progressThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (audioIsPlaying) {
                        if (progressHandler != null) {
                            // Send update audio player progress message to main thread message queue.
                            Message msg = new Message();
                            msg.what = UPDATE_AUDIO_PROGRESS_BAR;
                            progressHandler.sendMessage(msg);
                            Thread.sleep(1000);
                        }
                    }
                } catch (InterruptedException ex) {
                    Log.e("MediaInterfaz", ex.getMessage(), ex);
                }
            }
        };

        progressThread.start();
        playSong(songList.get(currentSongPos));
    }

    //https://stackoverflow.com/questions/18459122/play-sound-on-button-click-android
    private void playSong(Song song) {
        if (progressBar!=null) progressBar.setProgress(0);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(this, (int) song.id);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                btnNextClick(null);
            }
        });

        songTitleView.setText(song.getTitle());
        songArtistView.setText(song.getArtist());
        songIcon.setImageResource(song.getImage());

        mediaPlayer.start();
    }

    public void btnPreviousClick(View view) {
        if (currentSongPos > 0) currentSongPos--;
        playSong(songList.get(currentSongPos));
    }


    public void btnPauseClick(View view) {
        ImageButton imageButton = findViewById(R.id.imageButton2);
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                audioIsPlaying = false;
                imageButton.setImageResource(R.drawable.play);
            } else {
                imageButton.setImageResource(R.drawable.pause);
                audioIsPlaying = true;
                mediaPlayer.start();
            }
        }
    }

    public void btnNextClick(View view) {
        if (currentSongPos == songList.size() - 1) {
            currentSongPos = 0;
        } else {
            currentSongPos++;
        }
        playSong(songList.get(currentSongPos));
    }

    public void backToList(View view) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        finish();
    }

    private static class MyHandler extends Handler {
        ProgressBar pb;
        MyHandler (ProgressBar _progressBar){
            pb = _progressBar;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_AUDIO_PROGRESS_BAR) {

                if (mediaPlayer != null) {
                    // Get current play time. (in milliseconds)
                    int currPlayPosition = mediaPlayer.getCurrentPosition();

                    // Get total play time in milliseconds
                    int totalTime = mediaPlayer.getDuration();

                    // Calculate the percentage.
                    int currProgress = (currPlayPosition * 100) / totalTime;

                    // Update progressbar.
                    pb.setProgress(currProgress);
                }
            }
        }
    }
}
