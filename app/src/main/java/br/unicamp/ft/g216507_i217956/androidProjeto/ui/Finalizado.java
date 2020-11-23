package br.unicamp.ft.g216507_i217956.androidProjeto.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Finalizado#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Finalizado extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String boleto;

    private TextView boletoView;

    public Finalizado() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Finalizado.
     */
    // TODO: Rename and change types and number of parameters
    public static Finalizado newInstance(String param1, String param2) {
        Finalizado fragment = new Finalizado();
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
            boleto = getArguments().getString("boleto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_finalizado, container, false);

        boletoView = root.findViewById(R.id.boleto);

        ClipboardManager clipboard = (ClipboardManager)
                getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Código do boleto", boleto);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this.getContext(), "Boleto copiado!", Toast.LENGTH_LONG).show();

        boletoView.setText(boleto);

        // Inflate the layout for this fragment
        return root;
    }
}