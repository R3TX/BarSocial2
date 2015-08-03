package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anderssonvilla.barsocial2.adapter.CategoriaAdapter;
import com.example.anderssonvilla.barsocial2.adapter.LugaresAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Eventos extends ActionBarActivity {
    ListView eventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        eventos = (ListView) findViewById(R.id.listViewEvento);


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

    private void getListaLugaresPorCategoria(String categoria) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
        query
        final ProgressDialog dialog = ProgressDialog.show(Eventos.this, "Buscando Los proximos eventos", null, true, true);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> eventosList, ParseException e) {
                if(e==null){
                    dialog.dismiss();
                    LugaresAdapter adapter= new LugaresAdapter(eventosList, Eventos.this);
                    eventos.setAdapter(adapter);
                    eventos.setOnItemClickListener(mMessageClickedHandler);
                }else{
                    System.out.println("no di " + e.getMessage());
                }
            }
        });
    }
}
