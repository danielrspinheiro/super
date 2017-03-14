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
   // private List<Medico> itens_exibicao;

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
            convertView = layoutInflater.inflate(R.layout.activity_item_medico, parent, false);
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
        private TextView crm;
        private TextView tel;
        private TextView id;

        public ViewHolder(View view) {
            nome = (TextView) view.findViewById(R.id.textViewNome);
            crm = (TextView) view.findViewById(R.id.textViewCRM);
            tel = (TextView) view.findViewById(R.id.textViewTel);
            id = (TextView) view.findViewById(R.id.textViewId);
        }

        public void setValues(Paciente paciente) {
            nome.setText(paciente.getNome().toUpperCase());
            crm.setText("CRM "+ String.format("%06d",paciente.getCrm()));
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
                    ArrayList<Paciente> itens_filtrados = new ArrayList<Medico>();

                    //percorre toda lista verificando se contem a palavra do filtro na descricao do objeto.
                    for (int i = 0; i < arrayListPacientes.size(); i++) {
                        Medico medico = arrayListPacientes.get(i);

                        filtro = filtro.toString().toLowerCase();

                        if (medico.getNome().contains(filtro) ||
                                medico.getTelefone().contains(filtro) ||
                                (""+medico.getCrm()).contains(filtro) ){
                            //se conter adiciona na lista de itens filtrados.
                            itens_filtrados.add(medico);
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
                lista = (ArrayList<Medico>) results.values; // Valores filtrados.
                notifyDataSetChanged();  // Notifica a lista de alteração
            }

        };
        return filter;
    }

}
