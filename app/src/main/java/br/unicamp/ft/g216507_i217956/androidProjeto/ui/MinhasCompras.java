package br.unicamp.ft.g216507_i217956.androidProjeto.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MinhasCompras#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MinhasCompras extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView compras;

    public MinhasCompras() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MinhasCompras.
     */
    // TODO: Rename and change types and number of parameters
    public static MinhasCompras newInstance(String param1, String param2) {
        MinhasCompras fragment = new MinhasCompras();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_minhas_compras, container, false);

        compras = root.findViewById(R.id.compras);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
        Query query = raiz.child("transacoes").orderByChild("UID").equalTo(uid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                compras.setText("");

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.d("TAG", snapshot1.getKey().toString());

                    compras.append(snapshot1.getKey().toString() + "\n");

                    compras.append("Endereço: " + (snapshot1.child("endereco").getValue(String.class)) + "\n");

                    compras.append("Cidade/UF: " + (snapshot1.child("cidadeuf").getValue(String.class)) + "\n");

                    for (int i = 0; (snapshot1.child("produtos/" + String.valueOf(i) + "/nome").getValue(String.class)) != null; i++) {
                        compras.append((snapshot1.child("produtos/" + String.valueOf(i) + "/nome").getValue(String.class)) + " R$");
                        compras.append((snapshot1.child("produtos/" + String.valueOf(i) + "/preco").getValue(String.class)) + "\n");
                    }

                    compras.append("Valor Total: R$" + (snapshot1.child("produtos/valorTotal").getValue(String.class)) + "\n");
                    compras.append("Boleto: " + (snapshot1.child("codigoBoleto").getValue(String.class)) + "\n------------\n");
                }

                //Log.d("TAG", snapshot.child("endereco").getValue().toString());
                //endereco.setText(snapshot.child(snapshot.getKey()).child("endereco").getValue().toString());
                //cidadeuf.setText(snapshot.child(snapshot.getKey()).child("cidadeuf").getValue().toString());
                //produtos.setText(snapshot.child(snapshot.getKey()).child("produtos").getValue().toString());
                //valor_total.setText(snapshot.child(snapshot.getKey()).child("valorTotal").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}