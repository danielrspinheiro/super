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

import NEG.MedicoNEG;
import Transfer.Medico;

public class ListaPacienteActivity extends AppCompatActivity {

    private ListView listViewMedico;
    private PacienteAdapter medicoAdapter;
    private ArrayList<Medico> arrayListMedicos;
    private EditText edtPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtPesquisar = (EditText) findViewById(R.id.editTextPesquisar);
        listViewMedico = (ListView) findViewById(R.id.listaMedicos);

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
                medicoAdapter.getFilter(arrayListMedicos).filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listViewMedico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textViewId = (TextView) view.findViewById(R.id.textViewId);
                TextView textViewNome = (TextView) view.findViewById(R.id.textViewNome);
                TextView textViewTelefone = (TextView) view.findViewById(R.id.textViewTel);
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
        MedicoNEG medicoNeg = new MedicoNEG(this);
        arrayListMedicos = medicoNeg.buscarMedicos();
        medicoAdapter = new PacienteAdapter(this, arrayListMedicos);
        listViewMedico.setAdapter(medicoAdapter);
    }

}


