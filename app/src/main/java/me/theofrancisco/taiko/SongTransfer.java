package me.theofrancisco.taiko;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SongTransfer implements Serializable {
    private int position;
    private ArrayList<Song> songs;

    public SongTransfer(int position, ArrayList<Song> songs) {
        this.position = position;
        this.songs = songs;
    }

    public int getPosition() {
        return position;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

}
