package Transfer;

import java.util.Date;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class Fila {

    private int id;
    private Medico medico;
    private Paciente paciente;
    private Date dataAgendamento;
    private Date dataChegada;
    private Date dataAtendimento;

    public Fila(Date dataChegada) {
        this.dataChegada = dataChegada;
    }
    public Fila() {
    }


    public Fila(int id, Medico medico, Paciente paciente) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Fila(int id, Medico medico, Paciente paciente, Date dataAgendamento, Date dataAtendimento, Date dataChegada) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.dataAgendamento = dataAgendamento;
        this.dataAtendimento = dataAtendimento;
        this.dataChegada = dataChegada;
    }

    public Fila(int id, Paciente paciente) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Fila(int id) {
        this.id = id;
    }

    public Fila(Medico medico) {
        this.medico = medico;
    }

    public Fila(Paciente paciente) {
        this.paciente = paciente;
    }

    public Fila(Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
