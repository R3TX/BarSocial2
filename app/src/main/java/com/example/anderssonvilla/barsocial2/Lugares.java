package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.anderssonvilla.barsocial2.adapter.LugaresAdapter;
import com.parse.FindCallback;
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
        getSupportActionBar().setTitle(value1);
        try {
            getListaLugaresPorCategoria(value1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
    private void getListaLugaresPorCategoria(String categoria) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
        query.whereEqualTo("Categoria", categoria);
        final ProgressDialog dialog = ProgressDialog.show(Lugares.this, "Buscando "+ categoria, null, true, true);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> lugares, ParseException e) {
                if(e==null){
                    dialog.dismiss();
                    LugaresAdapter adapter= new LugaresAdapter(lugares, Lugares.this);
                    lugaresAMostrar.setAdapter(adapter);
                    lugaresAMostrar.setOnItemClickListener(adapter);
                }else{
                    System.out.println("no di " + e.getMessage());
                }
            }
        });
    }
}
