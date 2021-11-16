package adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemploapi.CadCursoActivity;
import com.example.exemploapi.MainActivity;
import com.example.exemploapi.R;

import java.util.ArrayList;

import holders.CursoHolder;
import models.Curso;

public class CursoAdapter extends RecyclerView.Adapter<CursoHolder> {
    private final ArrayList<Curso> cursos;

    public CursoAdapter(ArrayList<Curso> cursos){
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public CursoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CursoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_curso,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHolder holder, int position) {
        holder.txtNome.setText("Nome: " +cursos.get(position).getNome());
        holder.txtDuracao.setText("Total de horas: " + cursos.get(position).getDuracao());
        holder.txtValor.setText("Valor: R$"+cursos.get(position).getValor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.itemView.getContext(), CadCursoActivity.class);
                i.putExtra("id", cursos.get(holder.getAdapterPosition()).getId());
                holder.itemView.getContext().startActivity(i);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setMessage("Deseja excluir esse item?");
                alert.setTitle("Atenção");
                alert.setIcon(R.mipmap.ic_launcher);
                alert.setNegativeButton("Não",null);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity)holder.itemView.getContext()).excluirCurso(cursos.get(holder.getAdapterPosition()).getId());
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursos != null ? cursos.size() : 0;
    }
}
