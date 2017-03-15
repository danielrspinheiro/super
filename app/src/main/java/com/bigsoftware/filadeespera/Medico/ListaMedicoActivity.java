package com.bigsoftware.filadeespera.Medico;

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

public class ListaMedicoActivity extends AppCompatActivity {

    private ListView listViewMedico;
    private MedicoAdapter medicoAdapter;
    private ArrayList<Medico> arrayListMedicos;
    private EditText edtPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtPesquisar = (EditText) findViewById(R.id.editTextPesquisarMedico);
        listViewMedico = (ListView) findViewById(R.id.listaMedicos);

//        PreencherTela();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListaMedicoActivity.this, CadastroMedicoActivity.class);
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

                TextView textViewId = (TextView) view.findViewById(R.id.textViewIdMedico);
                TextView textViewNome = (TextView) view.findViewById(R.id.textViewNomeMedico);
                TextView textViewTelefone = (TextView) view.findViewById(R.id.textViewTelMedico);
                TextView textViewCrm = (TextView) view.findViewById(R.id.textViewCRM);

                Intent it = new Intent(ListaMedicoActivity.this, CadastroMedicoActivity.class);
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
        preencherTela();
    }

    private void preencherTela (){
        MedicoNEG medicoNeg = new MedicoNEG(this);
        arrayListMedicos = medicoNeg.buscarMedicos();
        medicoAdapter = new MedicoAdapter(this, arrayListMedicos);
        listViewMedico.setAdapter(medicoAdapter);
    }

}


