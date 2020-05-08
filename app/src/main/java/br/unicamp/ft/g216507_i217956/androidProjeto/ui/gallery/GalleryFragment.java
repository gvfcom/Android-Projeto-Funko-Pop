package br.unicamp.ft.g216507_i217956.androidProjeto.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

public class GalleryFragment extends Fragment {
    private TextView texto2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        texto2 = root.findViewById(R.id.tela2);
        Bundle bundle = getArguments();

        texto2.setText("Olá, " + bundle.getString("nome"));




        if(bundle.getBoolean("cb1")==true){



        }
        if(bundle.getBoolean("cb2")==true){



        }
        if(bundle.getBoolean("cb3")==true){



        }
        if(bundle.getBoolean("cb4")==true){



        }
        if(bundle.getBoolean("cb5")==true){



        }



        return root;

    }
}
