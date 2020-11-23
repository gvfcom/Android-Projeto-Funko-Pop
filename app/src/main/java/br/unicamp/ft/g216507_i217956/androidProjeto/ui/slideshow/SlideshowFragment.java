package br.unicamp.ft.g216507_i217956.androidProjeto.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

public class SlideshowFragment extends Fragment {

    private TextView textoBoasVindas, preco;
    private CheckBox checkbox_1;
    private CheckBox checkbox_2;
    private CheckBox checkbox_3;
    private CheckBox checkbox_4;
    private CheckBox checkbox_5;
    private EditText nome;
    private EditText endereco;
    private EditText cidadeuf;
    private EditText telefone;
    private RadioButton radio1;
    private RadioButton radio2;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        textoBoasVindas = root.findViewById(R.id.text_slideshow);
        nome = root.findViewById(R.id.nome);
        endereco = root.findViewById(R.id.endereco);
        cidadeuf = root.findViewById(R.id.cidadeuf);
        telefone = root.findViewById(R.id.telefone);
        radio1 = root.findViewById(R.id.radio_1);
        radio2 = root.findViewById(R.id.radio_2);
        button = root.findViewById(R.id.comprar);
        checkbox_1 = root.findViewById(R.id.checkbox_1);
        checkbox_2 = root.findViewById(R.id.checkbox_2);
        checkbox_3 = root.findViewById(R.id.checkbox_3);
        checkbox_4 = root.findViewById(R.id.checkbox_4);
        checkbox_5 = root.findViewById(R.id.checkbox_5);

        String precos[] = getResources().getStringArray(R.array.preco);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        myRef = database.getReference().child("info");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checkbox_1.setText(snapshot.child("0").child("nome").getValue().toString());
                checkbox_1.append(" R$" + snapshot.child("0").child("preco").getValue().toString());

                checkbox_2.setText(snapshot.child("1").child("nome").getValue().toString());
                checkbox_2.append(" R$" + snapshot.child("1").child("preco").getValue().toString());

                checkbox_3.setText(snapshot.child("2").child("nome").getValue().toString());
                checkbox_3.append(" R$" + snapshot.child("2").child("preco").getValue().toString());

                checkbox_4.setText(snapshot.child("3").child("nome").getValue().toString());
                checkbox_4.append(" R$" + snapshot.child("3").child("preco").getValue().toString());

                checkbox_5.setText(snapshot.child("4").child("nome").getValue().toString());
                checkbox_5.append(" R$" + snapshot.child("4").child("preco").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkboxVal = false;
                boolean infos = false;
                String vazio = "";

                if (checkbox_1.isChecked() || checkbox_2.isChecked() || checkbox_3.isChecked() ||
                        checkbox_4.isChecked() || checkbox_5.isChecked()) {
                    checkboxVal = true;
                }

                if ((vazio.equals(nome.getText().toString()) || vazio.equals(endereco.getText().toString()) ||
                        vazio.equals(cidadeuf.getText().toString()) || vazio.equals(telefone.getText().toString())) == true) {
                    infos = true;
                }

                if (checkboxVal == false || infos == true) {
                    Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef;

                String datahora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                myRef = database.getReference().child("transacoes").child(datahora);
                myRef.child("nome").setValue(nome.getText().toString());
                myRef.child("endereco").setValue(endereco.getText().toString());
                myRef.child("cidadeuf").setValue(cidadeuf.getText().toString());
                myRef.child("telefone").setValue(telefone.getText().toString());

                int i = 0;
                int valorTotal = 0;
                int valor = 0;

                if (checkbox_1.isChecked()) {
                    String checkbox = checkbox_1.getText().toString(); // Tony Stark|100

                    String[] checkboxArr = checkbox.split(" R\\$");  // { "Tony Stark", "100" }

                    myRef.child("produtos").child(String.valueOf(i)).child("nome").setValue(checkboxArr[0]); // "0" > "nome":"TonyStark"
                    myRef.child("produtos").child(String.valueOf(i)).child("preco").setValue(checkboxArr[1]); // "0" > "preco":"100"

                    valor = Integer.parseInt(checkboxArr[1]); // "100"

                    valorTotal += valor;
                    i++;
                }

                if (checkbox_2.isChecked()) {
                    String checkbox = checkbox_2.getText().toString(); // Tony Stark|100

                    String[] checkboxArr = checkbox.split(" R\\$");  // { "Tony Stark", "100" }

                    myRef.child("produtos").child(String.valueOf(i)).child("nome").setValue(checkboxArr[0]); // "0" > "nome":"TonyStark"
                    myRef.child("produtos").child(String.valueOf(i)).child("preco").setValue(checkboxArr[1]); // "0" > "preco":"100"

                    valor = Integer.parseInt(checkboxArr[1]); // "100"

                    valorTotal += valor;
                    i++;
                }

                if (checkbox_3.isChecked()) {
                    String checkbox = checkbox_3.getText().toString(); // Tony Stark|100

                    String[] checkboxArr = checkbox.split(" R\\$");  // { "Tony Stark", "100" }

                    myRef.child("produtos").child(String.valueOf(i)).child("nome").setValue(checkboxArr[0]); // "0" > "nome":"TonyStark"
                    myRef.child("produtos").child(String.valueOf(i)).child("preco").setValue(checkboxArr[1]); // "0" > "preco":"100"

                    valor = Integer.parseInt(checkboxArr[1]); // "100"

                    valorTotal += valor;
                    i++;
                }

                if (checkbox_4.isChecked()) {
                    String checkbox = checkbox_4.getText().toString(); // Tony Stark|100

                    String[] checkboxArr = checkbox.split(" R\\$");  // { "Tony Stark", "100" }

                    myRef.child("produtos").child(String.valueOf(i)).child("nome").setValue(checkboxArr[0]); // "0" > "nome":"TonyStark"
                    myRef.child("produtos").child(String.valueOf(i)).child("preco").setValue(checkboxArr[1]); // "0" > "preco":"100"

                    valor = Integer.parseInt(checkboxArr[1]); // "100"

                    valorTotal += valor;
                    i++;
                }

                if (checkbox_5.isChecked()) {
                    String checkbox = checkbox_5.getText().toString(); // Tony Stark|100

                    String[] checkboxArr = checkbox.split(" R\\$");  // { "Tony Stark", "100" }

                    myRef.child("produtos").child(String.valueOf(i)).child("nome").setValue(checkboxArr[0]); // "0" > "nome":"TonyStark"
                    myRef.child("produtos").child(String.valueOf(i)).child("preco").setValue(checkboxArr[1]); // "0" > "preco":"100"

                    valor = Integer.parseInt(checkboxArr[1]); // "100"

                    valorTotal += valor;
                    i++;
                }

                myRef.child("produtos").child("valorTotal").setValue(String.valueOf(valorTotal));

                //produtos
                //0
                //nome
                //preco
                //1
                //nome
                //preco

                //valorTotal
                // adicionar UID <---------------

                String frete;

                if (radio1.isChecked()) {
                    frete = "PAC (7-10 dias)";
                } else {
                    frete = "Sedex (3-4 dias)";
                }

                myRef.child("frete").setValue(frete);
                myRef.child("metodo").setValue("Boleto");

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                myRef.child("UID").setValue(uid);

                Bundle bundle = new Bundle();
                NavController navController = NavHostFragment.findNavController(SlideshowFragment.this);
                navController.navigate(R.id.pagamento, bundle);
            }


        });

        return root;
    }
    }
