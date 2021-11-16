package com.example.exemploapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import models.Curso;
import services.ServiceApi;

public class CadCursoActivity extends AppCompatActivity {
    int id=0;
    Curso curso;
    TextView textNome,textDuracao, textValor;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_curso);
        textNome = findViewById(R.id.textName);
        textDuracao = findViewById(R.id.textDuracao);
        textValor = findViewById(R.id.textValor);

        if(getIntent().hasExtra("id")) {
            //editando um curso
            id = getIntent().getIntExtra("id",0);
            new CursoAPI("GET").execute("Curso/" + id,"");
        }
    }
    public void carregarCampo() {
        textNome.setText(curso.getNome());
        textDuracao.setText(curso.getDuracao());
        textValor.setText(curso.getValor());
    }

    public void btnSalvarClick(View v) {
        if(id > 0) { //editar
            curso.setNome(textNome.getText().toString());
            curso.setDuracao(textDuracao.getText().toString());
            curso.setValor(textValor.getText().toString());

            new CursoAPI("PUT").execute("Curso/"+id, Curso.parseJson(curso));
        }
        else {
            //inserir
            curso = new Curso();
            curso.setNome(textNome.getText().toString());
            curso.setDuracao(textDuracao.getText().toString());
            curso.setValor(textValor.getText().toString());
            curso.setId(0);
            new CursoAPI("POST").execute("Curso", Curso.parseJson(curso));
        }
    }

    public class CursoAPI extends AsyncTask<String,String,String> {
        private String metodo;
        public CursoAPI(String metodo){
            this.metodo = metodo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(CadCursoActivity.this,"Aguarde","Carregando...");
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = ServiceApi.getService(strings[0],metodo,strings[1]);
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(metodo == "GET") {
                curso = Curso.parseOneObject(s);
                carregarCampo();
                dialog.dismiss();
            }
            else if(s == "OK"){
                Toast.makeText(CadCursoActivity.this,"Operação realizada com sucesso",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
            }
        }
    }
}