package Transfer;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class Fila {

    private int id;
    private Medico medico;
    private Paciente paciente;

    public Fila(int id, Medico medico, Paciente paciente) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
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
}
