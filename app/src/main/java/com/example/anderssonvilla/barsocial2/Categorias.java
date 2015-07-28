package com.example.anderssonvilla.barsocial2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Categorias extends ActionBarActivity {

    private ListView categorias;
    String [] nameCategorias = new String[]{"Restaurante", "Bar", "Cafeteria"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        categorias = (ListView) findViewById(R.id.listViewCategorias);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameCategorias);
        categorias.setAdapter(adapter);
        categorias.setOnItemClickListener(mMessageClickedHandler);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categorias, menu);
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
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            String text = textView.getText().toString();
            // String text = (getItemAtPosition(position));
            Intent intent = new Intent(Categorias.this, Lugares.class);
            intent.putExtra("categoria", text);
            startActivity(intent);
        }


    };
}
