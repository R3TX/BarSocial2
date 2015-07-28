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
 * Created by andersson.villa on 27/07/15.
 */
public class LugaresAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private List<ParseObject> lugares;
    private Activity context;

    public LugaresAdapter(List<ParseObject> lugares, Activity context) {
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
                    = inflater.inflate(R.layout.item_lugares, null);
        }
        TextView nombreLugar = (TextView) convertView.findViewById(R.id.nombre);
        ParseObject lugar = lugares.get(position);
        nombreLugar.setText(lugar.getString("Name"));
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(context, Lugar.class);
        ParseObject lugar = lugares.get(i);
        intent.putExtra("IDLugar", lugar.getObjectId());
        context.startActivity(intent);
    }
}
