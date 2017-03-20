package NEG;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import ADO.FilaADO;
import ADO.MedicoADO;
import Transfer.Fila;
import Transfer.Medico;
import Transfer.Paciente;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class FilaNEG {

    private FilaADO filaADO;
    private Medico medico;
    private Paciente paciente;
    private Fila fila;

    public FilaNEG(Context context) {
        filaADO = new FilaADO(context);
    }

    
    public void criarTabelaFila() {
        filaADO.criarTabelaFila();
    }

    
    public void inserirFila(Medico medico, Paciente paciente) {
        filaADO.inserirFila(medico, paciente);
    }

    
    public void deletarFila(String id) {
        fila = new Fila
                (Integer.parseInt(id));
        filaADO.deletarFila(fila);
    }
    
    public void atualizarChegada(Date dataChegada) {
        fila = new Fila(dataChegada);
        filaADO.alterarChegada(fila);
    }

    public void atualizarAgendamento(Date dataAgendamento) {
        fila = new Fila(dataAgendamento);
        filaADO.alterarChegada(fila);
    }

    public void atualizarAtendimento(Date dataAtendimento) {
        fila = new Fila(dataAtendimento);
        filaADO.alterarChegada(fila);
    }

    
   /* public ArrayList<Medico> buscarMedicoCrm(String crm) {
        medico = new Medico(Integer.parseInt(crm));
        fila = new Fila(medico);
        return filaADO.buscarMedicoCrm(fila);
    }


    public ArrayList<Medico> buscarMedicoNome(String nome) {
        medico = new Medico(nome);
        fila = new Fila(medico);
        return filaADO.buscarMedicoNome(fila);
    }


    public ArrayList<Medico> buscarMedicoTelefone(String telefone) {
        medico = new Medico();
        medico.setTelefone(telefone);
        fila = new Fila(medico);
        return filaADO.buscarMedicoTelefone(fila);
    }

    public ArrayList<Medico> buscarPorPacienteCpf(String cpf) {
        paciente = new Paciente(Integer.parseInt(cpf));
        fila = new Fila(paciente);
        return filaADO.buscarPacienteCpf(fila);
    }

    public ArrayList<Medico> buscarPacienteNome(String nome) {
        paciente = new Paciente(nome);
        fila = new Fila(paciente);
        return filaADO.buscarPacienteNome(fila);
    }

    public ArrayList<Medico> buscarPacienteTelefone(String telefone) {
        paciente = new Paciente();
        paciente.setTelefone(telefone);
        fila = new Fila(paciente);
        return filaADO.buscarPacienteNome(fila);
    }

    public ArrayList<Medico> buscarAgendamento(Date dataAgendamento) {
        fila = new Fila();
        fila.setDataAgendamento(dataAgendamento);
        return filaADO.buscarPacienteAgendamento(fila);
    }
 */
}
