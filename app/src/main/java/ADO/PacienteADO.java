package ADO;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import Transfer.Paciente;
import Transfer.Paciente;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class PacienteADO {

    private Paciente paciente;
    private Context context;
    private BancoDados db;
    private ArrayList<Paciente> listaPacientes;
    
    public PacienteADO(Context context) {
        this.context = context;
        criarTabelaPaciente();
    }

    public void criarTabelaPaciente (){
        db = new BancoDados(context);
        db.getBanco().execSQL("CREATE TABLE IF NOT EXISTS paciente " +
                "                   (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                    nome VARCHAR," +
                "                    telefone VARCHAR," +
                "                    cpf INTEGER)");
    }

    public void inserirPaciente (Paciente paciente) {
        try {
//            db.getBanco().execSQL("INSERT INTO paciente (nome, telefone, cpf ) VALUES (?,?,?)",
//                    new String[]{paciente.getNome().toString(), ""+paciente.getTelefone(), ""+paciente.getCpf()});

            db.getBanco().execSQL("INSERT INTO paciente VALUES (NULL, ?,?,?)",
                    new String[]{paciente.getNome().toString(), ""+paciente.getTelefone(), ""+paciente.getCpf()});
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
                    cursor.getInt(cursor.getColumnIndex("cpf")));

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
