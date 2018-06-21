package me.theofrancisco.taiko;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

public class Song implements Comparable <Song>, Comparator<Song>, Serializable
{
    long id;
    private String title;
    private String album;
    private int icon;
    private File file;
    private String artist;
    private int image;


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
        return album;
    }

    public int getIcon() {
        return icon;
    }

    public boolean equals(Object aSong) {
        Song s = (Song) aSong;
        return getTitle().equals(s.getTitle());
    }

    public int hashCode() {
        return title.hashCode();
    }

    public int compareTo(Song s)
    {
        return title.compareTo(s.getTitle());
    }

    public int compare (Song a, Song b){
        return a.getTitle().compareTo(b.getTitle());
    }

    public String toString() {
        return "album: " + album + ", title:"+ title+ " [path: "+file + "icon: "+icon+ "]";
    }

    public int getImage() {return image; }


}
