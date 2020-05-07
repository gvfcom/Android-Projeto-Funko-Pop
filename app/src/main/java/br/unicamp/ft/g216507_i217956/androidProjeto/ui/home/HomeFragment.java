package br.unicamp.ft.g216507_i217956.androidProjeto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;



public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private CheckBox checkbox1, checkbox2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        checkbox1 = root.findViewById(R.id.checkbox_1);
        checkbox2 = root.findViewById(R.id.checkbox_2);





        return root;
    }





}
