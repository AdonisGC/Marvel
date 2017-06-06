package com.m08dev.adonis.marvel.Retrofit;

import android.util.Log;
import android.widget.Toast;

import com.m08dev.adonis.marvel.RInterface.MarvelAPI;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.*;
import com.m08dev.adonis.marvel.GridAdapter.GridAdapter;
import com.m08dev.adonis.marvel.json.Result;
import com.m08dev.adonis.marvel.json.StoryLM;

import static android.content.ContentValues.TAG;

public class RetrofitRequester {
    private final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";
    private final String apikey = "bd4b2df1486e2321e58f886343a1c223";
    private final String hash = "242d3575a89ccd8240e0bcba7a58ddcd";
    private String limit = "100";
    private String ts = "1";
    private MarvelAPI servei;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public RetrofitRequester(){
        super();
    }

    public void getStories(final GridAdapter adaptador, String filter){
        servei = retrofit.create(MarvelAPI.class);

        Call<StoryLM> llamada = servei.stories(apikey,ts,hash,limit,filter);
        llamada.enqueue(new Callback<StoryLM>() {
            @Override
            public void onResponse(Response<StoryLM> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    StoryLM resultado = response.body();

                    Log.d(TAG,""+resultado.getData().getResults().size());
                    for (Result lista : resultado.getData().getResults()) {
                        adaptador.add(lista);
                    }
                } else {
                    Toast.makeText(adaptador.getContext(), "ERROR: "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }

        });
    }
}
