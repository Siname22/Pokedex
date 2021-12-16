package com.example.listviewspersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.sax.Element;
import android.widget.ListView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Variables
    ListView listView;

    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<String> urlsImg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ID a listview
        listView = findViewById(R.id.listView);


        AdaptadorPersonalizado adaptadorPersonalizado = new AdaptadorPersonalizado(pokemons,
                this);

        listView.setAdapter(adaptadorPersonalizado);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document resCompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/")
                            .get();
                    nombres =(ArrayList<String>) resCompleto.select("[href^=/es/pokedex/]")
                            .eachText();
                    nombres.remove(0);//El elemento cero no nos vale

                    for (int i = 0; i < nombres.size(); i++){



                        String numPkm = String.format("%03d", i + 1);
                        urlsImg.add("https://www.pokemon.com/es/pokedex/" + numPkm
                                + ".png/");
                        pokemons.add(new Pokemon(nombres.get(i)));//COnformar las lista de nombres
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AdaptadorPersonalizado adaptadorPersonalizado1 = new AdaptadorPersonalizado
                                (pokemons, MainActivity.this);
                        listView.setAdapter(adaptadorPersonalizado1);
                    }
                });
            }
        }).start();
    }
}