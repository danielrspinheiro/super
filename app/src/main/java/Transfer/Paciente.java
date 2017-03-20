package Transfer;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class Paciente extends Pessoa {

    private String cpf;

    public Paciente(){

    }

    public Paciente(int id){
        super(id);
    }

    public Paciente(int id, String nome, String telefone, String cpf) {
        super(id, nome, telefone);
        this.cpf = cpf;
    }

    public Paciente( String nome) {
        super(nome);
    }

    public Paciente(int id, String nome, String cpf) {
        super(id, nome);
        this.cpf = cpf;
    }

    public Paciente(String nome, String telefone, String cpf) {
        super(nome, telefone);
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
