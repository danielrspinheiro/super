package Transfer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class Medico extends Pessoa {

    private int crm;

    public Medico (){
        super();
    }

    public Medico(int id, String nome, String telefone, int crm) {
        super(id, nome, telefone);
        this.crm = crm;
    }

    public Medico(String nome, String telefone, int crm) {
        super(nome, telefone);
        this.crm = crm;
    }

    public Medico(String nome, String telefone) {
        super(nome, telefone);
    }

    public Medico(int id) {
        super(id);
    }

    public Medico(String nome) {
        super(nome);
    }

    public Medico(int id, String nome) {
        super(id, nome);
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }


}
