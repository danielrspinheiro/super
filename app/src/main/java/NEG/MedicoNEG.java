package NEG;

import android.content.Context;

import java.util.ArrayList;

import ADO.MedicoADO;
import Transfer.Medico;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class MedicoNEG {
    
    private MedicoADO medicoADO;
    private Medico medico;
    
    public MedicoNEG(Context context) {
        medicoADO = new MedicoADO(context);
    }

    
    public void criarTabelaMedico() {
        medicoADO.criarTabelaMedico();
    }

    
    public void inserirMedico(String nome, String telefone, String crm) {
        medico = new Medico(nome, telefone, Integer.parseInt(crm));
        medicoADO.inserirMedico(medico);
    }

    
    public void deletarMedico(String id) {
        medico = new Medico(Integer.parseInt(id));
        medicoADO.deletarMedico(medico);
    }

    public void deletarMedicos(){
        medicoADO.deletarMedicos();
    }
    
    public void atualizarMedico(int id, String nome, String telefone, String crm) {
        medico = new Medico(id, nome, telefone, Integer.parseInt(crm));
        medicoADO.atualizarMedico(medico);
    }

    
    public ArrayList<Medico> buscarMedicoCRM(String crm) {
        medico = new Medico(Integer.parseInt(crm));
        return medicoADO.buscarMedicoCRM(medico);
    }

    
    public ArrayList<Medico> buscarMedicoId(String id) {
        medico = new Medico(Integer.parseInt(id));
        return medicoADO.buscarMedicoId(medico);
    }

    
    public ArrayList<Medico> buscarMedicoNome(String nome) {
        medico = new Medico(nome);
        return medicoADO.buscarMedicoNome(medico);
    }

    
    public ArrayList<Medico> buscarMedicoTelefone(String telefone) {
        medico = new Medico(Integer.parseInt(telefone));
        return medicoADO.buscarMedicoTelefone(medico);
    }

    
    public ArrayList<Medico> buscarMedicos() {
        return medicoADO.buscarMedicos();
    }
 
}
