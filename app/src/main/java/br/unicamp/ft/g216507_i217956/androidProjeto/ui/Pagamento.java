package br.unicamp.ft.g216507_i217956.androidProjeto.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pagamento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pagamento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText endereco, cidadeuf;
    private TextView produtos, valor_total;

    private Button comprar;

    private String datahora;
    private int boleto_valor;
    private String boleto;

    public Pagamento() {
        // Required empty public constructor
    }

    public static Pagamento newInstance(String param1, String param2) {
        Pagamento fragment = new Pagamento();
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
        View root = inflater.inflate(R.layout.fragment_pagamento, container, false);

        endereco = root.findViewById(R.id.endereco);
        cidadeuf = root.findViewById(R.id.cidadeuf);
        produtos = root.findViewById(R.id.produtos);
        valor_total = root.findViewById(R.id.valor_total);
        comprar = root.findViewById(R.id.comprar);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
        Query query = raiz.child("transacoes").orderByChild("UID").equalTo(uid).limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", snapshot.getKey().getClass().toString());

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    datahora = snapshot1.getKey();
                    //Log.d("TAG", snapshot1.child("endereco").getValue(String.class));
                    endereco.setText(snapshot1.child("endereco").getValue(String.class));
                    cidadeuf.setText(snapshot1.child("cidadeuf").getValue(String.class));

                    boleto_valor = Integer.parseInt(snapshot1.child("produtos/valorTotal").getValue(String.class));

                    valor_total.setText("R$" + snapshot1.child("produtos/valorTotal").getValue(String.class));

                    produtos.setText("");
                    for (int i = 0; (snapshot1.child("produtos/" + String.valueOf(i) + "/nome").getValue(String.class)) != null; i++) {
                        produtos.append((snapshot1.child("produtos/" + String.valueOf(i) + "/nome").getValue(String.class)) + " R$");
                        produtos.append((snapshot1.child("produtos/" + String.valueOf(i) + "/preco").getValue(String.class)) + "\n");
                    }
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

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (("".equals(endereco.getText().toString()) || "".equals(cidadeuf.getText().toString())) == true) {
                    Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();

                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef;

                myRef = database.getReference().child("transacoes").child(datahora);
                myRef.child("endereco").setValue(endereco.getText().toString());
                myRef.child("cidadeuf").setValue(cidadeuf.getText().toString());

                Random rand = new Random();
                boleto = "34191.79001 01043.510047 91020.150008 5 " + String.format("%06d", rand.nextInt(100000)) + String.format("%05d", boleto_valor) + "00";

                myRef.child("codigoBoleto").setValue(boleto);

                Bundle bundle = new Bundle();

                bundle.putString("boleto", boleto);

                NavController navController = NavHostFragment.findNavController(Pagamento.this);
                navController.navigate(R.id.finalizado, bundle);
            }
        });

        // Inflate the layout for this fragment
        return root;
    }
}