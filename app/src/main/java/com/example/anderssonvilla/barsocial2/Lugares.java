package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anderssonvilla.barsocial2.adapter.LugaresAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Lugares extends ActionBarActivity {

ListView lugaresAMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        lugaresAMostrar= (ListView)findViewById(R.id.listViewLugares);
        Bundle extras = getIntent().getExtras();
        String value1  =extras.getString("categoria");
        getListaLugaresPorCategoria(value1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lugares, menu);
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
    private void getListaLugaresPorCategoria(String categoria) {
        ParseObject GameScore = ParseObject.create("Lugar");
        ParseQuery<ParseObject> tumama= ParseQuery.getQuery(GameScore.getClassName());
        LugaresAdapter adapter = new LugaresAdapter(tumama.getFirst(), Lugares.this);
        tumama.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                System.out.println("joder..... "+ parseObjects.size());
            }
        });
       /* ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
       // query.whereEqualTo("Categoria", categoria);
        //query.whereContains("Categoria", categoria);
        final ProgressDialog dialog = ProgressDialog.show(Lugares.this, "Buscando "+ categoria, null, true, true);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                System.out.println("mi tamaño es " + parseObjects.size());
            }
        });








        /*new FindCallback<ParseObject>() {
            public void aVoid
            public void done(List<ParseObject> lugares, ParseException e) {
                System.out.println("entre al done");
             /*   if(e!=null){
                    System.out.println(categoria);
                    System.out.println("asd");
                    System.out.println("s");
                    System.out.println("f");
                    System.out.println("d");
                    System.out.println("g");
                    System.out.println("joder pero que pasa "+e.getMessage()+"  " +e.getCause() );
                    System.out.println("r");
                    System.out.println("h");
                    System.out.println("3");
                    System.out.println("3");
                    System.out.println("5");
                    return;
                }else {
                if (lugares.isEmpty()) {
                    Toast.makeText(Lugares.this, "No has agregado ejrcicios para este dia", Toast.LENGTH_SHORT).show();
                }
                    System.out.println("Voy a dismmis");
                    dialog.dismiss();

                    System.out.println("mi tamaño es " + lugares.size());
                    LugaresAdapter adapter = new LugaresAdapter(lugares, Lugares.this);
                    lugaresAMostrar.setAdapter(adapter);
                    lugaresAMostrar.setOnItemClickListener(adapter);
               // }
            }
        });*/
    }
}
