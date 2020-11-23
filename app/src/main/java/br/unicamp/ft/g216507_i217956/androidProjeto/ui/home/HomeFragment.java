package br.unicamp.ft.g216507_i217956.androidProjeto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;



public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private CheckBox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5;
    private Button button1;
    private Button button2;
    private EditText nome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        button1 = root.findViewById(R.id.produtos);
        button2 = root.findViewById(R.id.comprar);
        nome = root.findViewById(R.id.nome);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                navController.navigate(R.id.firebaseListRecyclerViewFragment, bundle);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = null;

                NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                navController.navigate(R.id.nav_slideshow, bundle);
            }


        });

        return root;
    }
}