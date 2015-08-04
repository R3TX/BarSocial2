package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anderssonvilla.barsocial2.adapter.CategoriaAdapter;
import com.example.anderssonvilla.barsocial2.adapter.LugaresAdapter;
import com.example.anderssonvilla.barsocial2.adapter.eventoAdapter;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Eventos extends ActionBarActivity {
    ListView eventos;
    String value;

    List<ParseObject> eventosAEscoger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        eventos = (ListView) findViewById(R.id.listViewEvento);
        Bundle b = getIntent().getExtras();

        value = (String) b.get("idLugar");
        getEventos();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eventos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            TextView textView = (TextView) view.findViewById(R.id.nombre);
            String text = textView.getText().toString();
            // String text = (getItemAtPosition(position));
            Intent intent;
            intent = new Intent(Eventos.this, Evento.class);
            intent.putExtra("idEvento", text);
            startActivity(intent);
        }


    };

    private void getEventos() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
        final ProgressDialog dialog = ProgressDialog.show(Eventos.this, "Cargando Eventos", null, true, true);
        query.getInBackground(value, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {

                dialog.dismiss();
                ParseQuery<ParseObject> moreEvento = parseObject.getRelation("idEvento").getQuery();
                moreEvento.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects2, ParseException e) {
                        eventosAEscoger = parseObjects2;
                        eventoAdapter adapter = new eventoAdapter(parseObjects2,Eventos.this);
                        eventos.setAdapter(adapter);
                        eventos.setOnItemClickListener(adapter);
                    }
                });

            }
        });
    }
}
