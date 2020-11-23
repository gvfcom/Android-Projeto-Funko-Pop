package br.unicamp.ft.g216507_i217956.androidProjeto.FirebaseDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import br.unicamp.ft.g216507_i217956.androidProjeto.R;

public class FirebaseAdapter extends FirebaseRecyclerAdapter<Funkos, RecyclerView.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<Funkos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,
                                    int i, @NonNull Funkos resposta) {

        ((RespostaViewHolder) viewHolder).bind(resposta);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_firebase_recycler,
                parent, false);
        return new RespostaViewHolder(view);
    }

    public static class RespostaViewHolder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView marca;
        private TextView nome;

        public RespostaViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            marca = itemView.findViewById(R.id.marca);
            nome = itemView.findViewById(R.id.nome);
        }

        void bind(Funkos resposta) {
            this.marca.setText(resposta.getMarca());
            this.nome.setText(resposta.getNome());
            this.foto.setImageResource(resposta.getFoto());
        }
    }
}
