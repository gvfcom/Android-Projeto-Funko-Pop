package br.unicamp.ft.g216507_i217956.androidProjeto.ui.RecyclerFunko;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

public class Funko {
    private String marca;
    private String nome;
    private int foto;

    public Funko(String marca, String nome, int foto) {
        this.marca = marca;
        this.nome = nome;
        this.foto = foto;
    }

    public String getMarca(){return marca;}
    public String getNome(){return nome;}
    public int getFoto(){return foto;}

    @SuppressLint("ResourceType")
    public static Funko[] getFunkos(Context context, Boolean verificacao[]){
        if (context != null){
            String[] infos   = context.getResources().getStringArray(R.array.nomes);
            TypedArray fotos = context.getResources().obtainTypedArray(R.array.fotos);

            int tamanhoReal = 5;

            /*for(int j = 0; j < infos.length; j++){
                if(verificacao[j] == true) {
                    tamanhoReal++;
                }
            }*/

            Log.i("myTag", "tamanhoReal: " + tamanhoReal);


            List<Funko> funkosaux= new ArrayList<Funko>();

            for (int i = 0; i < tamanhoReal; i++){
                if(verificacao[i] == true) {
                    String[] info = infos[i].split(",");
                    funkosaux.add(new Funko(info[0],
                            info[1],
                            fotos.getResourceId(i, 0)));
                }


            }
                Funko[] funkos = funkosaux.toArray(new Funko[funkosaux.size()]);

            fotos.recycle();
            return funkos;
        }

        return null;
    }
}

