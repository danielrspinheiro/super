package com.bigsoftware.filadeespera.Paciente;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.bigsoftware.filadeespera.R;
import java.util.ArrayList;
import Transfer.Paciente;

/**
 * Created by danielpinheiro on 06/03/17.
 */

public class PacienteAdapter extends BaseAdapter {

    private ArrayList<Paciente> lista;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    public PacienteAdapter(Activity activity, ArrayList<Paciente> lista) {
        this.activity = activity;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Paciente getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            layoutInflater = LayoutInflater.from(activity);
            convertView = layoutInflater.inflate(R.layout.activity_item_paciente, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Paciente paciente = getItem(position);
        viewHolder.setValues(paciente);

        return convertView;
    }


    private class ViewHolder {
        private TextView nome;
        private TextView cpf;
        private TextView tel;
        private TextView id;

        public ViewHolder(View view) {
            nome = (TextView) view.findViewById(R.id.textViewNomePaciente);
            cpf = (TextView) view.findViewById(R.id.textViewCPF);
            tel = (TextView) view.findViewById(R.id.textViewTelPaciente);
            id = (TextView) view.findViewById(R.id.textViewIdPaciente);
            //vai
        }

        public void setValues(Paciente paciente) {
            nome.setText(paciente.getNome().toUpperCase());
            cpf.setText("CPF: "+ String.format("%11d",paciente.getCpf()));
            StringBuilder sb = new StringBuilder(paciente.getTelefone())
                    .insert(0,"(")
                    .insert(3,") ")
                    .insert(10,"-");
            String output = sb.toString();
            tel.setText("Tel.: " +output);
            id.setText(""+paciente.getId());
        }
    }

    /** Método responsável pelo filtro. Utilizaremos em um EditText
     *
     * @return
     */
    public Filter getFilter(final ArrayList<Paciente> arrayListPacientes) {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence filtro) {
                FilterResults results = new FilterResults();
                //se não foi realizado nenhum filtro insere todos os itens.
                if (filtro == null || filtro.length() == 0) {
                    results.count = arrayListPacientes.size();
                    results.values = arrayListPacientes;
                } else {
                    //cria um array para armazenar os objetos filtrados.
                    ArrayList<Paciente> itens_filtrados = new ArrayList<Paciente>();

                    //percorre toda lista verificando se contem a palavra do filtro na descricao do objeto.
                    for (int i = 0; i < arrayListPacientes.size(); i++) {
                        Paciente paciente = arrayListPacientes.get(i);

                        filtro = filtro.toString().toLowerCase();

                        if (paciente.getNome().contains(filtro) ||
                                paciente.getTelefone().contains(filtro) ||
                                (""+paciente.getCpf()).contains(filtro) ){
                            //se conter adiciona na lista de itens filtrados.
                            itens_filtrados.add(paciente);
                        }
                    }
                    // Define o resultado do filtro na variavel FilterResults
                    results.count = itens_filtrados.size();
                    results.values = itens_filtrados;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lista = (ArrayList<Paciente>) results.values; // Valores filtrados.
                notifyDataSetChanged();  // Notifica a lista de alteração
            }

        };
        return filter;
    }

}
