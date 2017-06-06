package com.m08dev.adonis.marvel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.m08dev.adonis.marvel.json.Result;

/**
 * Created by adoni on 05/06/2017.
 */

public class PeliculaActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_detalls);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Result serie = (Result) getIntent().getExtras().get("serie");
        toolbar.setTitle(serie.getTitle());
    }
}
