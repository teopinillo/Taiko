package me.theofrancisco.taiko;


import java.io.Serializable;
import java.util.ArrayList;

class SongTransfer implements Serializable {
    private final int position;
    private final ArrayList<Song> songs;

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
