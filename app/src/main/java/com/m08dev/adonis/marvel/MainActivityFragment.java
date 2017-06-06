package com.m08dev.adonis.marvel;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.m08dev.adonis.marvel.GridAdapter.GridAdapter;
import com.m08dev.adonis.marvel.Retrofit.RetrofitRequester;
import com.m08dev.adonis.marvel.json.Result;

import java.util.ArrayList;


public class MainActivityFragment extends Fragment {

    GridAdapter adptador;
    ArrayList<Result>inicialitzador;

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }


    //Indiquem que agregarem un nou  item (opció al menu)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //Indiquem quin es el xml corresponent al dibuix del menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_main, container, false);

        inicialitzador=new ArrayList<>();
        final Result p = new Result();
        p.setTitle("Sin datos");

        for(int i=0;i<=-1;i++)inicialitzador.add(p);
        GridView graellaPelis = (GridView)rootView.findViewById(R.id.graellaPelis);
        adptador = new GridAdapter(getContext(),R.layout.story_grid,inicialitzador);
        graellaPelis.setAdapter(adptador);

        graellaPelis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result peliSeleccionada = (Result) parent.getItemAtPosition(position);
                Intent invocaPeliActivity = new Intent(getContext(), PeliculaActivity.class);
                invocaPeliActivity.putExtra("serie", peliSeleccionada);
                startActivity(invocaPeliActivity);
            }

        });

        return rootView;
    }

    private void refresh() {
        RetrofitRequester apiSolicitadora = new RetrofitRequester();
        String filter = "title";
        SharedPreferences preferencias= PreferenceManager.getDefaultSharedPreferences(getContext());
        if(preferencias.getString("idioma_peliculas","title").equals("title")){
            filter="title";
        }else if(preferencias.getString("idioma_peliculas","modified").equals("modified")){
            filter="modified";
        }else {
            filter="startYear";
        }
        apiSolicitadora.getStories(adptador,filter);
    }
}
