package com.bigsoftware.filadeespera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigsoftware.filadeespera.Medico.ListaMedicoActivity;
import com.bigsoftware.filadeespera.Paciente.ListaPacienteActivity;

import NEG.MedicoNEG;

public class MenuActivity extends AppCompatActivity {

    private ImageView imgMedico;
    private ImageView imgPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgMedico = (ImageView) findViewById(R.id.imgMedico);
        imgPaciente = (ImageView) findViewById(R.id.imgPaciente);

        MedicoNEG medicoNEG = new MedicoNEG(MenuActivity.this);

        medicoNEG.deletarMedicos();
        for (int i = 0; i < 1; i++) {
            medicoNEG.inserirMedico("carlos santos", "79999809876", "23421");
            medicoNEG.inserirMedico("gessica dos santos aragao", "79998123456", "23421");
            medicoNEG.inserirMedico("daniel ramon silva pinheRo", "79990678966", "23421");
            medicoNEG.inserirMedico("willer Sampaio Smith", "11987654544", "23421");
            medicoNEG.inserirMedico("julio cesar", "79999809876", "23421");
            medicoNEG.inserirMedico("joaquim barbosa", "79999809876", "23421");
            medicoNEG.inserirMedico("charles chaplin ", "79999809876", "23421");
        }

//
//        ArrayList<Medico> arrayListmedico;
//
//        arrayListmedico = medicoNEG.buscarMedicos();
//
////        Medico medico = arrayListmedico.get(0);
////        medicoNEG.atualizarMedico(medico.getId(), "jose", ""+medico.getTelefone(), ""+medico.getCrm());
//
////        arrayListmedico = medicoNEG.buscarMedicos();
//
//        int i = 0;
//        for (Medico m : arrayListmedico) {
//            //Medico med = arrayListmedico.get(0);
//            Toast.makeText(getApplicationContext(), i + " - " + m.getId() + " " + m.getNome(), Toast.LENGTH_SHORT).show();
//            i++;
//        }


        imgMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuActivity.this, ListaMedicoActivity.class);
                startActivity(it);
            }
        });

        imgPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuActivity.this, ListaPacienteActivity.class);
                startActivity(it);
            }
        });

    }
}
