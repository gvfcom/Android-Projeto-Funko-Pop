package br.unicamp.ft.g216507_i217956.androidProjeto.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
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
        View root2 = inflater.inflate(R.layout.erro_layout, container, false);
        Bundle bundle = getArguments();
        if(bundle==null){
            return root2;
        }
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
                Bundle bundle= new Bundle();
                bundle.putString("marca",nome);
                NavController navController = NavHostFragment.findNavController(GalleryFragment.this);
                navController.navigate(R.id.nav_slideshow, bundle);
            }
        };
        myFirstAdapter.setMyFirstAdapterOnItemClickListener(listener);

        recyclerView.setAdapter(myFirstAdapter);

        return root;

    }
}