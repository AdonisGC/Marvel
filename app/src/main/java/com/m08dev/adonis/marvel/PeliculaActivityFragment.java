package com.m08dev.adonis.marvel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m08dev.adonis.marvel.json.*;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by adoni on 05/06/2017.
 */

public class PeliculaActivityFragment extends Fragment{
    private Result serie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pelicula_detalls, container, false);

        // Creamos nuestro intent y recogemos los datos que enviamos desde fragment
        Intent intent = getActivity().getIntent();
        Bundle bd = intent.getExtras();

        if (bd != null) {   // Si hemos recibido algo lo asigna a pelicula
            serie = (Result) intent.getSerializableExtra("serie");
        }

        getActivity().setTitle(serie.getTitle());
        TextView titulo=(TextView) view.findViewById(R.id.titulo);
        TextView fecha=(TextView) view.findViewById(R.id.fecha);
        TextView popularidad=(TextView) view.findViewById(R.id.popularidad);
        TextView valoracion=(TextView) view.findViewById(R.id.valoracion);
        TextView idioma=(TextView) view.findViewById(R.id.idioma);
        TextView catalogacion=(TextView) view.findViewById(R.id.catalogacion);
        TextView descripcion=(TextView) view.findViewById(R.id.descripcion);
        ImageView poster=(ImageView)view.findViewById(R.id.peliPoster);

        titulo.setText(serie.getTitle());
        fecha.setText("Started: "+serie.getStartYear());
        if(serie.getRating() != ""){
            popularidad.setText("Rating: "+serie.getRating());
        }else{
            popularidad.setText("Rating: Without rate");
        }

        if(serie.getEndYear() == 2099){
            valoracion.setText("Status: Ongoing");
        }else{
            valoracion.setText("Status: Ended "+serie.getEndYear());
        }
        String creators = "";
        String coma = " ";
        for(Item c : serie.getCreators().getItems()){
            creators += coma+c.getName();
            coma = ", ";
        }
        if(serie.getType() != "" && serie.getType().length() > 0 && serie.getType() != null){
            catalogacion.setText("Type: "+serie.getType());
        }else{
            catalogacion.setText("Type: Without type");
        }
        if(serie.getDescription() == null){
            descripcion.setText("Description:\n\nWithout description. "+"Creators: "+creators);
        }else{
            descripcion.setText("Description:\n\n"+serie.getDescription()+" Creators: "+creators);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Picasso.with(getContext()).load(serie.getThumbnail().getPath()+"."+serie.getThumbnail().getExtension()).into(poster);//
        }
        return view;

    }
}
