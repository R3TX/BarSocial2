package com.example.anderssonvilla.barsocial2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anderssonvilla.barsocial2.Evento;
import com.example.anderssonvilla.barsocial2.Lugar;
import com.example.anderssonvilla.barsocial2.R;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by retx_000 on 03/08/2015.
 */
public class eventoAdapter  extends BaseAdapter implements AdapterView.OnItemClickListener{
    private List<ParseObject> lugares;
    private Activity context;

    public eventoAdapter(List<ParseObject> lugares, Activity context) {
        this.lugares = lugares;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lugares.size();
    }

    @Override
    public Object getItem(int position) {
        return lugares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView
                    = inflater.inflate(R.layout.eventos, null);
        }
        TextView nombreLugar = (TextView) convertView.findViewById(R.id.eventosName);
        TextView sobrenombre = (TextView) convertView.findViewById(R.id.eventosDescrip);
        ParseObject lugar = lugares.get(position);
        nombreLugar.setText(lugar.getString("Name"));
        sobrenombre.setText(lugar.getString("Description"));
        return convertView;
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(context, Evento.class);
        ParseObject lugar = lugares.get(i);
        intent.putExtra("idEvento", lugar.getObjectId());
        context.startActivity(intent);
    }
}
