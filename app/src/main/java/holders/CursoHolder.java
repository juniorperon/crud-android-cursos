package holders;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.exemploapi.R;

public class CursoHolder extends RecyclerView.ViewHolder {
    public TextView txtNome;
    public TextView txtDuracao;
    public TextView txtValor;

    public CursoHolder(View view){
        super(view);
        txtNome = (TextView)view.findViewById(R.id.txtNome);
        txtDuracao = (TextView)view.findViewById(R.id.txtDuracao);
        txtValor = (TextView)view.findViewById(R.id.txtValor);


    }
}
