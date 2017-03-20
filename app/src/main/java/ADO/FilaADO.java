package ADO;

import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Transfer.Fila;
import Transfer.Medico;
import Transfer.Paciente;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class FilaADO {

    private Paciente paciente;
    private Medico medico;
    private Fila fila;
    private Context context;
    private BancoDados db;
    private ArrayList<Fila> listaFilas;

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

    public void deletarFila (Fila fila) {
        try {
            db.getBanco().execSQL("DELETE FROM fila WHERE id = ?",
                    new String[]{""+fila.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // deletar TODOS as filas
    public void deletarFilas () {
        try {
            db.getBanco().execSQL("DELETE FROM fila");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizarAgendamento(Fila fila, Date dataAgendamento){
            try {
                db.getBanco().execSQL("UPDATE fila SET dataAgendamento = ? WHERE id = ?",
                        new String[]{dataAgendamento.toString(), ""+fila.getId() });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public ArrayList<Fila> buscarFila(Fila fila){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM fila WHERE id = ?",
                    new String[]{""+fila.getId()});


            listaFilas = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaFilas;
        }
    }

    private ArrayList<Fila> buscarItens(Cursor cursor){
        listaFilas = new ArrayList<Fila>();
        //Listar as filas
        cursor.moveToFirst();
        while (cursor != null) {
            medico = new Medico();
            medico.setId(cursor.getInt(cursor.getColumnIndex("id_medico")));
            paciente = new Paciente();
            paciente.setId(cursor.getInt(cursor.getColumnIndex("id_paciente")));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
            try {
                fila = new Fila(cursor.getInt(cursor.getColumnIndex("id")),
                        medico,
                        paciente,
                        format.parse(cursor.getString(cursor.getColumnIndex("data_agendamento"))),
                        format.parse(cursor.getString(cursor.getColumnIndex("data_atendimento"))),
                        format.parse(cursor.getString(cursor.getColumnIndex("data_chegada"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            listaFilas.add(fila);
            cursor.moveToNext();
        }
        return listaFilas;
    }

    public ArrayList<Fila> buscarFilaId(Fila fila){
        try {

            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM fila WHERE id = ?",
                    new String[]{""+fila.getId()});

            listaFilas = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaFilas;
        }
    }
/*
* id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "                    id_paciente INTEGER NOT NULL," +
                "                    id_medico INTEGER NOT NULL," +
                "                    data_agendamento DATETIME CURRENT_TIMESTAMP" +
                "                    data_atendimento DATETIME CURRENT_TIMESTAMP" +
                "                    data_chegada DATETIME CURRENT_TIMESTAMP" +
* */

    public ArrayList<Fila> buscarFilaAgendamento(Fila fila){
        try {
            Cursor cursor = db.getBanco().rawQuery("SELECT * FROM " +
                            "                       fila f" +
                            "                       JOIN" +
                            "                       paciente p" +
                            "                       on" +
                            "                       f.id_paciente = p.id    " +
                            "                       WHERE f.id_medico = ? and f.data_agendamento = ?",
                    new String[]{""+fila.getMedico().getId(), fila.getDataAgendamento().toString()});

            listaFilas = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return listaFilas;
        }
    }

    public ArrayList<Fila> buscarFilas(){
        Cursor cursor = null;
        try {

            cursor = db.getBanco().rawQuery("SELECT * FROM fila", null);

            listaFilas = buscarItens(cursor);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            cursor.close();
            return listaFilas;
        }
    }

}
