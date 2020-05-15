package br.unicamp.ft.g216507_i217956.androidProjeto.ui.RecyclerFunko;

        import android.graphics.Color;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

        import br.unicamp.ft.g216507_i217956.androidProjeto.R;

public class MyFirstAdapter extends RecyclerView.Adapter {

    private ArrayList<Funko> funkos;
    public MyFirstAdapter(ArrayList funkos){
        this.funkos = funkos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.funko_layout, parent, false);


        view.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        if (myFirstAdapterOnItemClickListener != null){
                                            TextView txt = v.findViewById(R.id.text_view);
                                            myFirstAdapterOnItemClickListener.myFirstAdapterOnItemClick(txt.getText().toString());
                                        }
                                        // Toast.makeText(parent.getContext(), "ASDF", Toast.LENGTH_SHORT).show();
                                    }
                                }
        );


        return new MyFirstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyFirstViewHolder)holder).bind(funkos.get(position));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   return false;
               }
           }
        );

    }

    @Override
    public int getItemCount() {
        return funkos.size();
    }


    /*
    Definindo a interface para disparar gatilhos de OnItemClick
     */
    public interface MyFirstAdapterOnItemClickListener{
        void myFirstAdapterOnItemClick(String nome);
    }
    // Declarei o atributo
    private MyFirstAdapterOnItemClickListener myFirstAdapterOnItemClickListener;

    // Atribuo a instância para o atributo.
    public void setMyFirstAdapterOnItemClickListener(MyFirstAdapterOnItemClickListener m){
        this.myFirstAdapterOnItemClickListener = m;
    }
    /*  End */




    class MyFirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView  textView;

        public MyFirstViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView  = itemView.findViewById(R.id.text_view);
        }

        public void bind(Funko funko){
            String marca = funko.getMarca();
            String nome = funko.getNome();


            imageView.setImageResource(funko.getFoto());
            textView.setText("Marca: " + marca);
            textView.append(" \n" + "Nome: " + nome);


            imageView.setPaddingRelative(0,15,15,0);

            /*switch (funko.getColor()){
                case 0:textView.setTextColor(Color.parseColor("#1E90FF"));
                    break;
                case 1:textView.setTextColor(Color.parseColor("#4B0082"));
                    break;
                case 2:textView.setTextColor(Color.parseColor("#00FA9A"));
                    break;
                default:textView.setTextColor(Color.parseColor("#CCCCCC"));
                    break;
            }
             */
        }
    }
}
