package Transfer;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class Paciente extends Pessoa {

    private int cpf;

    public Paciente(){

    }

    public Paciente(int cpf) {
        this.cpf = cpf;
    }

    public Paciente(String nome){
        super(nome);
    }
    public Paciente(int id, String nome, String telefone, int cpf) {
        super(id, nome, telefone);
        this.cpf = cpf;
    }

    public Paciente(int id, String nome, int cpf) {
        super(id, nome);
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
}
