package br.unicamp.ft.g216507_i217956.androidProjeto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;



public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private CheckBox checkbox1, checkbox2, checkbox3, checkbox4,checkbox5;
    private Button button;
    private EditText nome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        checkbox1 = root.findViewById(R.id.checkbox_1);
        checkbox2 = root.findViewById(R.id.checkbox_2);
        checkbox3 = root.findViewById(R.id.checkbox_3);
        checkbox4 = root.findViewById(R.id.checkbox_4);
        checkbox5 = root.findViewById(R.id.checkbox_5);
        button= root.findViewById(R.id.enviar);
        nome = root.findViewById(R.id.nome);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                boolean cb1 = false;
                boolean cb2 = false;
                boolean cb3 = false;
                boolean cb4 = false;
                boolean cb5 = false;
                if(checkbox1.isChecked()){
                    cb1=true;
                }
                if(checkbox2.isChecked()){
                    cb2=true;
                }
                if(checkbox3.isChecked()){
                    cb3=true;
                }
                if(checkbox4.isChecked()){
                    cb4=true;
                }
                if(checkbox5.isChecked()){
                    cb5=true;
                }
                String usuario = nome.getText().toString();
                bundle.putBoolean("cb1",cb1);
                bundle.putBoolean("cb2",cb2);
                bundle.putBoolean("cb3",cb3);
                bundle.putBoolean("cb4",cb4);
                bundle.putBoolean("cb5",cb5);
                bundle.putString("nome",usuario);

                NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                navController.navigate(R.id.nav_gallery, bundle);



            }


        });












        return root;
    }





}
