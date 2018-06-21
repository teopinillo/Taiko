package me.theofrancisco.taiko;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.MediaController.MediaPlayerControl;


/*
 Create a Service:  MusicService
 */

//1: https://code.tutsplus.com/tutorials/create-a-music-player-on-android-project-setup--mobile-22764
//2: https://code.tutsplus.com/tutorials/create-a-music-player-on-android-user-controls--mobile-22787
//3: https://code.tutsplus.com/tutorials/create-a-music-player-on-android-song-playback--mobile-22778

//https://developer.android.com/reference/android/widget/MediaController#MediaController(android.content.Context)
//https://developer.android.com/guide/topics/media/mediaplayer
//https://www.programcreek.com/java-api-examples/?class=android.widget.MediaController&method=setMediaPlayer


public class TaikoActivity extends AppCompatActivity  {

    //We will store the songs in a list and display them in the ListView instance in the main layout.
    private ArrayList<Song> songList;
    private ListView songView;
    private int currentSongPos;
    public static final String SONGS ="me.theofrancisco.android.TaikoActivity.extra.SONGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taiko);
        final Context context = this;

        songList = SongFactory.getList();
        ArrayAdapter<Song> adapter = new SongAdapter(this, songList);
         songView = (ListView) findViewById(R.id.list);
          songView.setAdapter(adapter);
            // ListView on item selected listener.
            songView.setOnItemClickListener(new AdapterView.OnItemClickListener()            {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Song song  = songList.get(position);
                    Toast.makeText(context, song.getTitle(), Toast.LENGTH_SHORT).show();
                    SongTransfer songTransfer = new SongTransfer(position,songList);
                    Intent intent = new Intent(context,MediaInterfaz.class);
                    intent.putExtra(SONGS,songTransfer);
                    startActivity(intent);
                }
            });
       }
}