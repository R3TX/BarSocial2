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

import com.example.anderssonvilla.barsocial2.Lugar;
import com.example.anderssonvilla.barsocial2.R;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by retx_000 on 03/08/2015.
 */
public class dialogAdapter extends BaseAdapter {
    private List<ParseObject> lugares;
    private Activity context;

    public dialogAdapter(List<ParseObject> lugares, Activity context) {
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
                    = inflater.inflate(R.layout.dialog_more_info, null);
        }
       // LayoutInflater inflater = context.getLayoutInflater();
      //  View v = inflater.inflate(R.layout.dialog, null);
        ParseObject info = lugares.get(0);
        TextView descrip, direc, hora, tarjeta, outdor, reserva;

        descrip = (TextView) convertView.findViewById(R.id.infoDescriptionValue);
        descrip.setText(info.getString("Description"));

        hora = (TextView) convertView.findViewById(R.id.infoHorarioValue);
        hora.setText(info.getString("Horario"));

        tarjeta = (TextView) convertView.findViewById(R.id.infoCreditCardValue);


        outdor = (TextView) convertView.findViewById(R.id.infoOutDoorValue);
        outdor.setText(info.getString("Outdor"));

        reserva = (TextView) convertView.findViewById(R.id.infoDescriptionValue);
        reserva.setText(info.getString("Reservation"));

        direc = (TextView) convertView.findViewById(R.id.infoAddresValue);
        direc.setText(info.getString("Addres"));

        return convertView;
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(context, Lugar.class);
        ParseObject lugar = lugares.get(i);
        intent.putExtra("IDLugar", lugar.getObjectId());
        context.startActivity(intent);
    }

}
