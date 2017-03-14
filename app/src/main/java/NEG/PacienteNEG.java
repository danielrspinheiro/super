package NEG;

import android.content.Context;

import java.util.ArrayList;

import ADO.PacienteADO;
import Transfer.Paciente;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class PacienteNEG {
    
    private PacienteADO pacienteADO;
    private Paciente paciente;
    
    public PacienteNEG(Context context) {
        pacienteADO = new PacienteADO(context);
    }

    
    public void criarTabelaPaciente() {
        pacienteADO.criarTabelaPaciente();
    }

    
    public void inserirPaciente(String nome, String telefone, String cpf) {
        paciente = new Paciente(nome, telefone, cpf);
        pacienteADO.inserirPaciente(paciente);
    }

    
    public void deletarPaciente(String id) {
        paciente = new Paciente(Integer.parseInt(id));
        pacienteADO.deletarPaciente(paciente);
    }

    public void deletarPacientes(){
        pacienteADO.deletarPacientes();
    }
    
    public void atualizarPaciente(int id, String nome, String telefone, String cpf) {
        paciente = new Paciente(id, nome, telefone, cpf);
        pacienteADO.atualizarPaciente(paciente);
    }

    
    public ArrayList<Paciente> buscarPacienteCPF(String cpf) {
        paciente = new Paciente();
        paciente.setCpf(cpf);
        return pacienteADO.buscarPacienteCPF(paciente);
    }

    public ArrayList<Paciente> buscarPacienteNome(String nome) {
        paciente = new Paciente();
        paciente.setNome(nome);
        return pacienteADO.buscarPacienteNome(paciente);
    }

    
    public ArrayList<Paciente> buscarPacienteTelefone(String telefone) {
        paciente = new Paciente(Integer.parseInt(telefone));
        return pacienteADO.buscarPacienteTelefone(paciente);
    }

    
    public ArrayList<Paciente> buscarPacientes() {
        return pacienteADO.buscarPacientes();
    }
 
}
