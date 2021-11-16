package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Curso {
    private int Id;
    private String Nome;
    private String Duracao;
    private String Valor;

    public Curso(int id, String nome, String duracao, String valor) {
        Id = id;
        Nome = nome;
        Duracao = duracao;
        Valor = valor;
    }

    public Curso() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String duracao) {
        Duracao = duracao;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
    public static String parseJson(Curso curso){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nome", curso.getNome());
            jsonObject.put("duracao", curso.getDuracao());
            jsonObject.put("valor", curso.getValor());
            return  jsonObject.toString();
        }
        catch (Exception ex){
            return "";
        }
    }

    public static Curso parseOneObject(String json){
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
                Curso curso = new Curso();
                JSONObject obj = new JSONObject(json);
                curso.setNome(obj.getString("nome"));
                curso.setDuracao(obj.getString("duracao"));
                curso.setValor(obj.getString("valor"));
                curso.setId(obj.getInt("id"));
                cursos.add(curso);

            return curso;
        }
        catch (Exception ex){
            return null;
        }
    }

    public static ArrayList<Curso> parseObject(String json){
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                Curso curso = new Curso();
                JSONObject obj = array.getJSONObject(i);
                curso.setNome(obj.getString("nome"));
                curso.setDuracao(obj.getString("duracao"));
                curso.setValor(obj.getString("valor"));
                curso.setId(obj.getInt("id"));
                cursos.add(curso);
            }
            return cursos;
        }
        catch (Exception ex){
            return cursos;
        }
    }
}
