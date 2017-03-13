package ADO;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import Transfer.Medico;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class MedicoADO {

    private Medico medico;
    private Context context;
    private BancoDados db;
    private ArrayList<Medico> listaMedicos;

    public MedicoADO(Context context) {
        this.context = context;
        criarTabelaMedico();
    }

    public void criarTabelaMedico (){
        db = new BancoDados(context);
        db.getBanco().execSQL("CREATE TABLE IF NOT EXISTS medico " +
                "                   (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                    nome VARCHAR," +
                "                    telefone VARCHAR," +
                "                    crm INTEGER)");
    }

    public void inserirMedico (Medico medico) {
        try {
//            db.getBanco().execSQL("INSERT INTO medico (nome, telefone, crm ) VALUES (?,?,?)",
//                    new String[]{medico.getNome().toString(), ""+medico.getTelefone(), ""+medico.getCrm()});

            db.getBanco().execSQL("INSERT INTO medico VALUES (NULL, ?,?,?)",
                    new String[]{medico.getNome(), medico.getTelefone(), ""+medico.getCrm()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarMedico (Medico medico) {
        try {
            db.getBanco().execSQL("DELETE FROM medico WHERE id = ?",
                    new String[]{""+medico.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // deletar TODOS os médicos
    public void deletarMedicos () {
        try {
            db.getBanco().execSQL("DELETE FROM medico");
//            db.getBanco().execSQL("DROP table medico");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarMedico(Medico medico){
            try {
                db.getBanco().execSQL("UPDATE medico SET nome = ?, telefone = ?, crm = ? WHERE id = ?",
                        new String[]{""+medico.getNome(), medico.getTelefone(), ""+medico.getCrm(),
                        ""+medico.getId()});
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ArrayList<Medico> buscarMedicoCRM(Medico medico){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM medico WHERE crm = ?",
                    new String[]{""+medico.getCrm()});


            listaMedicos = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaMedicos;
        }
    }

    private ArrayList<Medico> buscarItens(Cursor cursor){
        listaMedicos = new ArrayList<Medico>();
        //Listar as tarefas
        cursor.moveToFirst();
        while (cursor != null) {
            medico = new Medico(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("telefone")),
                    cursor.getInt(cursor.getColumnIndex("crm")));

            listaMedicos.add(medico);
            cursor.moveToNext();
        }
        return listaMedicos;
    }
    public ArrayList<Medico> buscarMedicoId(Medico medico){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM medico WHERE id = ?",
                    new String[]{""+medico.getId()});

            listaMedicos = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaMedicos;
        }
    }

    public ArrayList<Medico> buscarMedicoNome(Medico medico){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM medico WHERE nome like ?",
                    new String[]{""+medico.getNome()});

            listaMedicos = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaMedicos;
        }
    }

    public ArrayList<Medico> buscarMedicoTelefone(Medico medico){
        try {
            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM medico WHERE telefone = ?",
                    new String[]{""+medico.getTelefone()});

            listaMedicos = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaMedicos;
        }
    }

    public ArrayList<Medico> buscarMedicos(){
        Cursor cursor = null;
        try {

            cursor = db.getBanco().rawQuery("SELECT * FROM medico ORDER BY nome", null);

            listaMedicos = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            cursor.close();
            return listaMedicos;
        }
    }

}
