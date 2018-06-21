package me.theofrancisco.taiko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SongFactory {

    private static Map<Character, Integer> icons;

    public static ArrayList<Song> getList(){
        setIconMap();
       ArrayList<Song> songs = new ArrayList<>();
        songs.add ( new Song (R.raw.all_the_little_lights,"All The Little Lights","Passenger", R.drawable.passenger_1,getIcon("a")));
        songs.add ( new Song (R.raw.doshus_the_only_time_ac_dc,"The Only Time","AC/DC", R.drawable.ac_dc,getIcon("t")));
        songs.add ( new Song (R.raw.circles_passenger,"Circles","Passenger",R.drawable.ac_dc,getIcon("c")));
        songs.add (new Song (R.raw.deep_purple_smoke_on_the_water,"Smoke in the Water","Deep Purple",R.drawable.deep_purple,getIcon("s")));
        songs.add (new Song (R.raw.feather_on_the_clyde,"Feather on The Clyde","Passenger",R.drawable.passenger_3,getIcon("f")));
        songs.add (new Song (R.raw.guns_n_roses_paradise_city,"Paradaise City","Guns and Roses",R.drawable.guns_n_roses,getIcon("p")));
        songs.add (new Song (R.raw.hate_live_from_the_borderline,"I Hate","Passenger",R.drawable.passenger_4,getIcon("i")));

        return songs;
    }

    private static  void setIconMap() {
        //assign every letter and icon
        icons = new HashMap<>();
        icons.put('a', R.drawable.a);
        icons.put('b', R.drawable.b);
        icons.put('c', R.drawable.c);
        icons.put('d', R.drawable.d);
        icons.put('e', R.drawable.e);
        icons.put('f', R.drawable.f);
        icons.put('g', R.drawable.g);
        icons.put('h', R.drawable.h);
        icons.put('i', R.drawable.i);
        icons.put('j', R.drawable.j);
        icons.put('k', R.drawable.k);
        icons.put('l', R.drawable.l);
        icons.put('m', R.drawable.m);
        icons.put('n', R.drawable.n);
        icons.put('o', R.drawable.o);
        icons.put('p', R.drawable.p);
        icons.put('q', R.drawable.q);
        icons.put('r', R.drawable.r);
        icons.put('s', R.drawable.s);
        icons.put('t', R.drawable.t);
        icons.put('u', R.drawable.u);
        icons.put('v', R.drawable.v);
        icons.put('w', R.drawable.w);
        icons.put('x', R.drawable.x);
        icons.put('y', R.drawable.y);
        icons.put('z', R.drawable.z);
        icons.put('?', R.drawable.ten);
    }

    private static int getIcon(String title) {
        int i = 0;
        String t = title.toLowerCase();
        while (!Character.isLetter(t.charAt(i))) {
            i++;
            if (i == t.length()) return icons.get('?');
        }
        return icons.get(t.charAt(i));
    }

}
