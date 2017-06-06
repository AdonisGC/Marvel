package com.m08dev.adonis.marvel.GridAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.m08dev.adonis.marvel.R;
import com.m08dev.adonis.marvel.json.*;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import static android.content.ContentValues.TAG;


public class GridAdapter extends ArrayAdapter<Result> implements Serializable {

    List<Result> stories;
    public GridAdapter(Context context, int resource, List<Result> stories) {
        super(context, resource, stories);
        this.stories = stories;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Result story = getItem(position);
        Log.d(TAG, story.getTitle());
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView =inflater.inflate(R.layout.story_grid, parent, false);
        }

        ImageView poster = (ImageView)convertView.findViewById(R.id.peliPoster);
        TextView titol = (TextView)convertView.findViewById(R.id.Titol);
        Picasso.with(getContext()).load(story.
                getThumbnail().getPath()+"."+story.getThumbnail().getExtension()).networkPolicy(NetworkPolicy.OFFLINE).fit().into(poster);

        titol.setText(story.getTitle());

        return (convertView);
    }
}
