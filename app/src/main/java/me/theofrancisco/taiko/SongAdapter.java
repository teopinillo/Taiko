package me.theofrancisco.taiko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SongAdapter extends  ArrayAdapter<Song> {
    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, R.layout.listitem, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listitem, parent, false);
        }

        Song song = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.item_text_up);
        nameTextView.setText(song.getAlbum());

        TextView numberTextView = listItemView.findViewById(R.id.item_text_bottom);
        numberTextView.setText(song.getTitle());

        ImageView iconView = listItemView.findViewById(R.id.item_icon);
        iconView.setImageResource(song.getIcon());

        return listItemView;
    }
}
