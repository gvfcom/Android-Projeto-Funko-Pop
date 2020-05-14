package br.unicamp.ft.g216507_i217956.androidProjeto.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;
import br.unicamp.ft.g216507_i217956.androidProjeto.ui.RecyclerFunko.Funko;
import br.unicamp.ft.g216507_i217956.androidProjeto.ui.RecyclerFunko.MyFirstAdapter;

public class GalleryFragment extends Fragment {
    private TextView textoBoasVindas, texto1, texto2, texto3, texto4, texto5;
    private ImageView imagem1, imagem2, imagem3, imagem4, imagem5;
    private boolean cb1, cb2, cb3, cb4, cb5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        Bundle bundle = getArguments();

        Boolean verificacao[] = { bundle.getBoolean("cb1"), bundle.getBoolean("cb2"), bundle.getBoolean("cb3"), bundle.getBoolean("cb4"), bundle.getBoolean("cb5") };

        textoBoasVindas = root.findViewById(R.id.tela2);
        textoBoasVindas.setText(getResources().getString(R.string.ola) + ", " + bundle.getString("nome"));

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyFirstAdapter myFirstAdapter = new MyFirstAdapter(
                new ArrayList(Arrays.asList(Funko.getFunkos(getContext(), verificacao))));

        MyFirstAdapter.MyFirstAdapterOnItemClickListener listener = new MyFirstAdapter.MyFirstAdapterOnItemClickListener() {
            @Override
            public void myFirstAdapterOnItemClick(String nome) {
                Toast.makeText(getContext(),nome,Toast.LENGTH_SHORT).show();
            }
        };
        myFirstAdapter.setMyFirstAdapterOnItemClickListener(listener);

        recyclerView.setAdapter(myFirstAdapter);

        /*
        texto1 = root.findViewById(R.id.texto1);
        imagem1 = root.findViewById(R.id.imagem1);
        texto2 = root.findViewById(R.id.texto2);
        imagem2 = root.findViewById(R.id.imagem2);
        texto3 = root.findViewById(R.id.texto3);
        imagem3 = root.findViewById(R.id.imagem3);
        texto4 = root.findViewById(R.id.texto4);
        imagem4 = root.findViewById(R.id.imagem4);
        texto5 = root.findViewById(R.id.texto5);
        imagem5 = root.findViewById(R.id.imagem5);

        cb1 = bundle.getBoolean("cb1");
        cb2 = bundle.getBoolean("cb2");
        cb3 = bundle.getBoolean("cb3");
        cb4 = bundle.getBoolean("cb4");
        cb5 = bundle.getBoolean("cb5");

        Log.i("mytag", String.valueOf(cb5));


        LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 320);

        if(cb1){
            texto1.setText(getResources().getString(R.string.essesProdutos) + " da Marvel:");
            imagem1.setImageResource(R.drawable.marvel_tonystark);
            imagem1.setLayoutParams(layoutParams);
        }

        if(cb2){
            texto2.setText(getResources().getString(R.string.essesProdutos) + " da DC:");
            imagem2.setImageResource(R.drawable.dc_flash);
            imagem2.setLayoutParams(layoutParams);
        }

        if(cb3){
            texto3.setText(getResources().getString(R.string.essesProdutos) + " da Disney:");
            imagem3.setImageResource(R.drawable.disney_woody);
            imagem3.setLayoutParams(layoutParams);
        }

        if(cb4){
            texto4.setText(getResources().getString(R.string.essesProdutos) + " do Game of Thrones:");
            imagem4.setImageResource(R.drawable.got_jonsnow);
            imagem4.setLayoutParams(layoutParams);
        }

        if(cb5){
            texto5.setText(getResources().getString(R.string.essesProdutos) + " do Harry Potter:");
            imagem5.setImageResource(R.drawable.hp_harrypotter);
            imagem5.setLayoutParams(layoutParams);
        }
        */

        return root;

    }
}