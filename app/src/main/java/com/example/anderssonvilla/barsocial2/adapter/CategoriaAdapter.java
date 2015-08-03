package com.example.anderssonvilla.barsocial2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anderssonvilla.barsocial2.Lugar;
import com.example.anderssonvilla.barsocial2.R;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by andersson.villa on 27/07/15.
 */
public class CategoriaAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    String [] nameCategorias = new String[]{"Restaurante", "Bar", "Cafeteria"};
    private Activity context;

    public CategoriaAdapter( Activity context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return nameCategorias.length;
    }

    @Override
    public Object getItem(int position) {
        return nameCategorias[position];
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
        TextView sobrenombre = (TextView) convertView.findViewById(R.id.sobrenombre);
        nombreLugar.setText(nameCategorias[position]);
        sobrenombre.setText("Los mejores "+ nameCategorias[position]+" de la ciudad");
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);
        String r = "src/main/res/drawable-xhdpi/" +nameCategorias[position].toLowerCase();
        int[] bipmap = new int[]{R.drawable.restaurante,R.drawable.bar, R.drawable.cafeteria};
        icon.setImageResource(bipmap[position]);

        return convertView;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
