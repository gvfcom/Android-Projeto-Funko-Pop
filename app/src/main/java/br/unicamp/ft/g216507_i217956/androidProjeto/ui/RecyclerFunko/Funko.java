package br.unicamp.ft.g216507_i217956.androidProjeto.ui.RecyclerFunko;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        if (context != null) {
            String[] infos   = context.getResources().getStringArray(R.array.nomes);

            TypedArray fotos = context.getResources().obtainTypedArray(R.array.fotos);
            int tamanhoReal = 5;

            List<Funko> funkosaux= new ArrayList<Funko>();

            /* --------------------------------------------- */
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("info");

            // Attach a listener to read the data at our posts reference
            // Read from the database
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    for(Integer i=0; i<5; i++) {
                        String marca = dataSnapshot.child(i.toString()).child("marca").getValue(String.class);
                        Log.d("TAG", "Nó: " + i.toString());
                        Log.d("TAG", "Marca: " + marca);
                        String nome = dataSnapshot.child(i.toString()).child("nome").getValue(String.class);
                        Log.d("TAG", "Nome: " + nome);
                        String preco = dataSnapshot.child(i.toString()).child("preco").getValue(String.class);
                        Log.d("TAG", "Preco: " + preco);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });
            /* --------------------------------------------- */


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

