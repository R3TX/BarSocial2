package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Evento extends ActionBarActivity {
    String value;
    Button btnPres;
    TextView name;
    TextView desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        btnPres=(Button) findViewById(R.id.buttonEvento);
        name = (TextView) findViewById(R.id.eventoName);
        desc = (TextView) findViewById(R.id.eventoDescrip);
        Bundle b = getIntent().getExtras();
        value= b.getString("idEvento");
        getLugar();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_evento, menu);
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


    private void getLugar() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Evento");
        final ProgressDialog dialog = ProgressDialog.show(Evento.this, "Cargando Evento", null, true, true);
        query.getInBackground(value,new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject parseObject, ParseException e) {

                dialog.dismiss();
                        name.setText(parseObject.getString("Name"));
                        desc.setText(parseObject.getString("Description"));
                    btnPres.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent map = new Intent(Evento.this, MapsActivity.class);
                        map.putExtra("longitud", parseObject.getParseGeoPoint("Location").getLongitude());
                        map.putExtra("latitud" , parseObject.getParseGeoPoint("Location").getLatitude());
                        map.putExtra("Name", parseObject.getString("Name"));
                        startActivity(map);
                    }
                });

                    }
                });
    }
}
