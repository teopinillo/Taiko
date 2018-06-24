package me.theofrancisco.taiko;

import java.io.File;
import java.io.Serializable;

class Song implements Serializable
{
    final long id;
    private final String title;
    private String album;
    private final int icon;
    private File file;
    private final String artist;
    private final int image;


    public Song(long songID, String songTitle, String songArtist, int image, int icon) {
        id=songID;
        title=songTitle;
        artist=songArtist;
        this.icon = icon;
        this.image = image;
    }

    public long getID(){return id;}

    public String getTitle(){return title;}

    public String getArtist(){return artist;}

    public File getFile() {
        return file;
    }

    public String getAlbum(){
        if (album==null) return "" ; else return album;
    }

    public int getIcon() {
        return icon;
    }

    public int getImage() {return image; }


}
