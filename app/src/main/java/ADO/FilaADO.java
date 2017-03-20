package ADO;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

import Transfer.Fila;
import Transfer.Medico;
import Transfer.Paciente;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class FilaADO {

    private Paciente paciente;
    private Medico medico;
    private Context context;
    private BancoDados db;
    private ArrayList<Fila> listaDeEspera;

    public FilaADO(Context context) {
        this.context = context;
        criarTabelaFila();
    }

    public void criarTabelaFila (){
        db = new BancoDados(context);
        db.getBanco().execSQL("CREATE TABLE IF NOT EXISTS fila " +
                "                   (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                    id_paciente INTEGER NOT NULL," +
                "                    id_medico INTEGER NOT NULL," +
                "                    data_agendamento DATETIME CURRENT_TIMESTAMP" +
                "                    data_atendimento DATETIME CURRENT_TIMESTAMP" +
                "                    data_chegada DATETIME CURRENT_TIMESTAMP" +
                "                    FOREIGN KEY(id_paciente) REFERENCES paciente(id)" +
                "                    FOREIGN KEY(id_medico) REFERENCES medico(id) )");
    }

    public void inserirFila (Medico medico, Paciente paciente) {
        try {
            db.getBanco().execSQL("INSERT INTO fila VALUES (NULL, ?,?, NULL, NULL, NULL)",
                    new String[]{""+paciente.getId(), ""+medico.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarChegada (Fila fila) {
        try {
            db.getBanco().execSQL("UPDATE fila SET data_chegada = datetime('now') WHERE id = ?",
                    new String[]{""+fila.getId()} );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarAgendamento (Fila fila) {
        try {
            db.getBanco().execSQL("UPDATE fila SET data_agendamento = datetime('now') WHERE id = ?",
                    new String[]{""+fila.getId()} );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarAtendimento (Fila fila) {
        try {
            db.getBanco().execSQL("UPDATE fila SET data_atendimento = datetime('now') WHERE id = ?",
                    new String[]{""+fila.getId()} );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void deletarPaciente (Paciente paciente) {
        try {
            db.getBanco().execSQL("DELETE FROM paciente WHERE id = ?",
                    new String[]{""+paciente.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // deletar TODOS os médicos
    public void deletarPacientes () {
        try {
            db.getBanco().execSQL("DELETE FROM paciente");
//            db.getBanco().execSQL("DROP table paciente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarPaciente(Paciente paciente){
            try {
                db.getBanco().execSQL("UPDATE paciente SET nome = ?, telefone = ?, cpf = ? WHERE id = ?",
                        new String[]{""+paciente.getNome(), paciente.getTelefone(), ""+paciente.getCpf(),
                        ""+paciente.getId()});
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ArrayList<Paciente> buscarPacienteCPF(Paciente paciente){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM paciente WHERE cpf = ?",
                    new String[]{""+paciente.getCpf()});


            listaPacientes = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaPacientes;
        }
    }

    private ArrayList<Paciente> buscarItens(Cursor cursor){
        listaPacientes = new ArrayList<Paciente>();
        //Listar as tarefas
        cursor.moveToFirst();
        while (cursor != null) {
            paciente = new Paciente(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("telefone")),
                    cursor.getString(cursor.getColumnIndex("cpf")));

            listaPacientes.add(paciente);
            cursor.moveToNext();
        }
        return listaPacientes;
    }

    public ArrayList<Paciente> buscarPacienteId(Paciente paciente){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM paciente WHERE id = ?",
                    new String[]{""+paciente.getId()});

            listaPacientes = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaPacientes;
        }
    }

    public ArrayList<Paciente> buscarPacienteNome(Paciente paciente){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM paciente WHERE nome like ?",
                    new String[]{""+paciente.getNome()});

            listaPacientes = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaPacientes;
        }
    }

    public ArrayList<Paciente> buscarPacienteTelefone(Paciente paciente){
        try {
            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM paciente WHERE telefone = ?",
                    new String[]{""+paciente.getTelefone()});

            listaPacientes = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaPacientes;
        }
    }

    public ArrayList<Paciente> buscarPacientes(){
        Cursor cursor = null;
        try {

            cursor = db.getBanco().rawQuery("SELECT * FROM paciente", null);

            listaPacientes = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            cursor.close();
            return listaPacientes;
        }
    }

}
