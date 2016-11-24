package com.example.julia.login;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

//Não tente entender, só aceite.
//Serve para ajustar e acessar os dados que vão entrar na View/Tela do Android
//Parcialmente copiado da internet.
//Parcialmente.
//Sério. Não é caô.
public class ChoppAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Choppada> dataSource;

    public ChoppAdapter(Context context, List<Choppada> items) {
        this.context = context;
        dataSource = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }


    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.adapter_chop, parent, false);

        //Exibirão o nome e local da choppada depois que realizar setText()
        TextView nomeChopp = (TextView) rowView.findViewById(R.id.nome_choppada);
        TextView localChopp = (TextView) rowView.findViewById(R.id.local_choppada);

        Choppada c = (Choppada) getItem(position);

        //Exibe o nome
        nomeChopp.setText(c.getNome());
        localChopp.setText(c.getLocal());

        //Retorna a view
        return rowView;
    }


}