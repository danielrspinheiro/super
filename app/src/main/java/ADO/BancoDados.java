package ADO;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by danielpinheiro on 24/02/17.
 */

public class BancoDados {

    private SQLiteDatabase banco;
    private final String NOMEBANCO = "APPFILAESPERA";
    private final Context context;

    public BancoDados(Context context){
        this.context = context;
        banco = context.openOrCreateDatabase(NOMEBANCO, Context.MODE_PRIVATE, null);
    }

    public SQLiteDatabase getBanco() {
        return banco;
    }

    public void setBanco(SQLiteDatabase banco) {
        this.banco = banco;
    }
}
