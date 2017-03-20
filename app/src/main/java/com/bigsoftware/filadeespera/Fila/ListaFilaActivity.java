package com.bigsoftware.filadeespera.Fila;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.bigsoftware.filadeespera.Medico.MedicoAdapter;
import com.bigsoftware.filadeespera.Paciente.PacienteAdapter;
import com.bigsoftware.filadeespera.R;

import java.util.ArrayList;

import NEG.MedicoNEG;
import Transfer.Fila;
import Transfer.Medico;
import Transfer.Paciente;

public class ListaFilaActivity extends AppCompatActivity {

    private ListView listViewPaciente;
   // private FilaAdapter filaAdapter;
    private ArrayList<Paciente> arrayListFilas;
    private EditText edtPesquisar;
    private ArrayList<Medico> arrayspinnerMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fila);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MedicoNEG medicoNEG = new MedicoNEG(getApplicationContext());
        arrayspinnerMedicos = medicoNEG.buscarMedicos();

        //Fazer o adapter para exibir os medicos e guardar o id dos mesmos para adicionar na fila
        Spinner spinnerMedicos = (Spinner) findViewById(R.id.spinnerMedico);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, arrayspinnerMedicos);
        MedicoAdapter medicoAdapter = new MedicoAdapter(this,arrayspinnerMedicos );
        spinnerMedicos.setAdapter(medicoAdapter);

        spinnerMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
