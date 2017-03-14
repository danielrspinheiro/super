package com.bigsoftware.filadeespera.Paciente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigsoftware.filadeespera.R;
import com.bigsoftware.filadeespera.TelefoneMaskUtil;

import NEG.MedicoNEG;
import NEG.PacienteNEG;

public class CadastroPacienteActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextphone;
    private EditText editTextCPF;
    private Button btnCadastrar;
    private Button btnExcluir;
    private int idPaciente = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);

        editTextNome = (EditText) findViewById(R.id.editTextNomePaciente);
        editTextNome.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        editTextCPF = (EditText) findViewById(R.id.editTextCPF);
        editTextphone = (EditText) findViewById(R.id.editTextTelefonePaciente);
        editTextphone.addTextChangedListener(TelefoneMaskUtil.insert(editTextphone));
        btnCadastrar = (Button) findViewById(R.id.buttonCadastrarPaciente);
        btnExcluir = (Button) findViewById(R.id.buttonExcluirPaciente);

        Intent it = getIntent();
        idPaciente = it.getIntExtra("id", -1);
        if(idPaciente != -1){
            editTextNome.setText(it.getStringExtra("nome"));
            editTextphone.setText(it.getStringExtra("tel"));
            editTextCPF.setText(""+it.getIntExtra("cpf", -1));
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

                    if(idPaciente == -1) {
                        PacienteNEG pacienteNEG = new PacienteNEG(getApplication());
                        pacienteNEG.inserirPaciente(editTextNome.getText().toString(),
                                editTextphone.getText().toString().replaceAll("[^\\d]", ""),
                                editTextCPF.getText().toString().equals("") ? "0" : editTextCPF.getText().toString());

                        Toast.makeText(getApplicationContext(), "O paciente " + editTextNome.getText().toString() + " foi cadastrado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        PacienteNEG pacienteNEG = new PacienteNEG(getApplication());
                        pacienteNEG.atualizarPaciente(idPaciente,
                                editTextNome.getText().toString(),
                                editTextphone.getText().toString().replaceAll("[^\\d]", ""),
                                editTextCPF.getText().toString().equals("") ? "0" : editTextCPF.getText().toString());

                        Toast.makeText(getApplicationContext(), "O paciente " + editTextNome.getText().toString() + " foi atualizado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirPaciente();
            }
        });

    }

    private void excluirPaciente() {
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
                PacienteNEG pacienteNEG = new PacienteNEG(getApplication());
                pacienteNEG.deletarPaciente(""+ idPaciente);
                Toast.makeText(getApplicationContext(), "O paciente " + editTextNome.getText().toString() + " foi excluído com sucesso!",
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
