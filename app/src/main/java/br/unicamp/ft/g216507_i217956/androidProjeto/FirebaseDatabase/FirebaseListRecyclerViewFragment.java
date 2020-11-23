package br.unicamp.ft.g216507_i217956.androidProjeto.FirebaseDatabase;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseListRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private View lview;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseAdapter mFirebaseAdapter;

    public FirebaseListRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_firelist, container, false);
        }
        recyclerView = lview.findViewById(R.id.recyclerView);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference auxRef = mFirebaseDatabaseReference.child("info");

        final TypedArray fotos = getResources().obtainTypedArray(R.array.fotos);

        SnapshotParser<Funkos> parser = new SnapshotParser<Funkos>() {
            @NonNull
            @Override
            public Funkos parseSnapshot(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", snapshot.getValue().toString());
                String marca = snapshot.child("marca").getValue().toString();
                String nome = snapshot.child("nome").getValue().toString();
                int foto = fotos.getResourceId(Integer.parseInt(snapshot.getKey()), 0);
                //Log.d("TAG", marca);
                //Log.d("TAG", nome);
                //Log.d("TAG", "Int foto: " + String.valueOf(foto));
                return new Funkos(marca, nome, foto);
            }
        };

        FirebaseRecyclerOptions<Funkos> options =
                new FirebaseRecyclerOptions.Builder<Funkos>()
                        .setQuery(auxRef, parser)
                        .build();

        mFirebaseAdapter = new FirebaseAdapter(options);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mFirebaseAdapter);

        return lview;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFirebaseAdapter.stopListening();
    }
}
















