package Transfer;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class Pessoa {

    private String nome;
    private String telefone;
    private int id;

    public Pessoa(){
    }

    public Pessoa(int id, String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
    }

    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = this.nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
