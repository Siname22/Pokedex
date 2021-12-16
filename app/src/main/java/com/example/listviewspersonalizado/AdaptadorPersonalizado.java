package com.example.listviewspersonalizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends BaseAdapter {
    ArrayList<Pokemon> pokemons;
    Context context;

    public AdaptadorPersonalizado(ArrayList<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int i) {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //Se ejecuta de forma automatica al renderizas cada uno de los views del listado
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Genera un view a partir del layout de "item_pkm" generado
        View viewInflado = LayoutInflater.from(context).inflate(R.layout.item_pkm, null);

        TextView nommbre = viewInflado.findViewById(R.id.nombrePkm);

        ImageView img = viewInflado.findViewById(R.id.imgPkm);

        nommbre.setText(pokemons.get(position).nombre);

        Picasso.get().load(MainActivity.urlsImg.get(position)).into(img);

        return viewInflado;
    }
}
