package com.bigsoftware.filadeespera.Paciente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.bigsoftware.filadeespera.R;
import java.util.ArrayList;
import NEG.PacienteNEG;
import Transfer.Paciente;

public class ListaPacienteActivity extends AppCompatActivity {

    private ListView listViewPaciente;
    private PacienteAdapter pacienteAdapter;
    private ArrayList<Paciente> arrayListPacientes;
    private EditText edtPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paciente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtPesquisar = (EditText) findViewById(R.id.editTextPesquisarPaciente);
        listViewPaciente = (ListView) findViewById(R.id.listaPacientes);

//        PreencherTela();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListaPacienteActivity.this, CadastroPacienteActivity.class);
                startActivity(it);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pacienteAdapter.getFilter(arrayListPacientes).filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listViewPaciente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textViewId = (TextView) view.findViewById(R.id.textViewIdPaciente);
                TextView textViewNome = (TextView) view.findViewById(R.id.textViewNomePaciente);
                TextView textViewTelefone = (TextView) view.findViewById(R.id.textViewTelPaciente);
                TextView textViewCrm = (TextView) view.findViewById(R.id.textViewCRM);

//                Medico medico = new Medico(
//                        Integer.parseInt(textViewId.getText().toString()),
//                        textViewNome.getText().toString(),
//                        textViewTelefone.getText().toString(),
//                        Integer.parseInt(textViewCrm.getText().toString()));

                Intent it = new Intent(ListaPacienteActivity.this, CadastroPacienteActivity.class);
                it.putExtra("id", Integer.parseInt(textViewId.getText().toString()));
                it.putExtra("nome", textViewNome.getText().toString());
                it.putExtra("tel", textViewTelefone.getText().toString().replaceAll("[^\\d]", ""));
                it.putExtra("crm", Integer.parseInt(textViewCrm.getText().toString().replaceAll("CRM ", "")));



                startActivity(it);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        PreencherTela();
    }

    private void PreencherTela (){
        PacienteNEG pacienteNeg = new PacienteNEG(this);
        arrayListPacientes = pacienteNeg.buscarPacientes();
        pacienteAdapter = new PacienteAdapter(this, arrayListPacientes);
        listViewPaciente.setAdapter(pacienteAdapter);
    }

}


