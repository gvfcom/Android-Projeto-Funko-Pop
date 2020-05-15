package br.unicamp.ft.g216507_i217956.androidProjeto.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

public class SlideshowFragment extends Fragment {

    private TextView textoBoasVindas,preco;
    private String marca;
    private ImageView foto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        Bundle bundle = getArguments();
        marca=bundle.getString("marca");
        foto= root.findViewById(R.id.image_view);
        textoBoasVindas = root.findViewById(R.id.text_slideshow);
        textoBoasVindas.setText(getResources().getString(R.string.compra) + " \n"+ bundle.getString("marca"));
        preco=root.findViewById(R.id.preco);

        String precos [] = getResources().getStringArray(R.array.preco);

        if(marca.contains("Marvel")) {
            foto.setImageResource(R.drawable.marvel_tonystark);
            preco.setText(precos[0]);
        }
        if(marca.contains("DC")) {
            foto.setImageResource(R.drawable.dc_flash);
            preco.setText(precos[1]);
        }
        if(marca.contains("Disney")) {
            foto.setImageResource(R.drawable.disney_woody);
            preco.setText(precos[2]);
        }
        if(marca.contains("Game Of Thrones")) {
            foto.setImageResource(R.drawable.got_jonsnow);
            preco.setText(precos[3]);
        }
        if(marca.contains("Harry Potter")) {
            foto.setImageResource(R.drawable.hp_harrypotter);
            preco.setText(precos[4]);
        }

             return root;
       }

    }


