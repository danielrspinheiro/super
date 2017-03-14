package com.bigsoftware.filadeespera.Medico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigsoftware.filadeespera.R;
import com.bigsoftware.filadeespera.TelefoneMaskUtil;

import NEG.MedicoNEG;
import Transfer.Medico;

public class CadastroMedicoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextphone;
    private EditText editTextcrm;
    private Button btnCadastrar;
    private Button btnExcluir;
    private int idMedico = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medico);

        editTextNome = (EditText) findViewById(R.id.editTextNomeMedico);
        editTextNome.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        editTextcrm = (EditText) findViewById(R.id.editTextCrm);
        editTextphone = (EditText) findViewById(R.id.editTextTelefoneMedico);
        editTextphone.addTextChangedListener(TelefoneMaskUtil.insert(editTextphone));
        btnCadastrar = (Button) findViewById(R.id.buttonCadastrarMedico);
        btnExcluir = (Button) findViewById(R.id.buttonExcluirMedico);

        Intent it = getIntent();
        idMedico = it.getIntExtra("id", -1);
        if(idMedico != -1){
            editTextNome.setText(it.getStringExtra("nome"));
            editTextphone.setText(it.getStringExtra("tel"));
            editTextcrm.setText(""+it.getIntExtra("crm", -1));
        }else {
            btnExcluir.setEnabled(false);
            btnExcluir.setVisibility(View.GONE);
        }


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((editTextNome.getText().toString().split(" ").length < 2) ||
                       (editTextNome.getText().toString().split(" ")[0].length() < 2 ||
                       (editTextNome.getText().toString().split(" ")[1].length() < 2))){

                        Toast.makeText(getApplicationContext(), "Informe o Nome e Sobrenome", Toast.LENGTH_SHORT).show();
                }else if (editTextphone.getText().length() < 15){
                    Toast.makeText(getApplicationContext(), "Informe o Telefone Celular", Toast.LENGTH_SHORT).show();
                }else {

                    if(idMedico == -1) {
                        MedicoNEG medicoNEG = new MedicoNEG(getApplication());
                        medicoNEG.inserirMedico(editTextNome.getText().toString(),
                                editTextphone.getText().toString().replaceAll("[^\\d]", ""),
                                editTextcrm.getText().toString().equals("") ? "0" : editTextcrm.getText().toString());

                        Toast.makeText(getApplicationContext(), "O médico " + editTextNome.getText().toString() + " foi cadastrado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        MedicoNEG medicoNEG = new MedicoNEG(getApplication());
                        medicoNEG.atualizarMedico(idMedico,
                                editTextNome.getText().toString(),
                                editTextphone.getText().toString().replaceAll("[^\\d]", ""),
                                editTextcrm.getText().toString().equals("") ? "0" : editTextcrm.getText().toString());

                        Toast.makeText(getApplicationContext(), "O médico " + editTextNome.getText().toString() + " foi atualizado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirMedico();
            }
        });

    }

    private void excluirMedico() {
        AlertDialog alerta;
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Confirmação");
        //define a mensagem
        builder.setMessage("Deseja realmente excluir?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                MedicoNEG medicoNEG = new MedicoNEG(getApplication());
                medicoNEG.deletarMedico(""+idMedico);
                Toast.makeText(getApplicationContext(), "O médico " + editTextNome.getText().toString() + " foi excluído com sucesso!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}
