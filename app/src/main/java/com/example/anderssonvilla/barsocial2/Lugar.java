package com.example.anderssonvilla.barsocial2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class Lugar extends ActionBarActivity {


    Button butonInfo,butonProductos,butonEventos, btnMapa;
    TextView txtName, txtCategoria, txtLocation;
    ParseGeoPoint point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);
        butonInfo = (Button) findViewById(R.id.btnMoreInfo);
        butonEventos = (Button) findViewById(R.id.btnEventos);
        butonProductos = (Button) findViewById(R.id.btnProducto);
        btnMapa = (Button) findViewById(R.id.btnMapas);
        txtName = (TextView) findViewById(R.id.lugarName);
        txtLocation = (TextView) findViewById(R.id.lugarLocation);
        txtCategoria= (TextView) findViewById(R.id.lugarCategoria);
        ponerListener();
        Bundle extras = getIntent().getExtras();
        String value1  =extras.getString("IDLugar");
        getLugar(value1);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lugar, menu);
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

    public void crearDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_more_info, null));
        builder.create().show();
    }
    public void activity(int i){
        if(i==3){
            crearDialogo();
            return;
        }
        Intent intent = null;

        if(i==0){
            intent = new Intent(this, Eventos.class);

        }else if(i==1){
            intent = new Intent(this, Producto.class);

        }else if(i ==2){
            intent = new Intent(this, MapsActivity.class);
            double lat = point.getLatitude();
            double lon=point.getLongitude();
            intent.putExtra("latitud", lat);
            intent.putExtra("longitud",lon);
            intent.putExtra("Name",txtName.getText());
        }
        startActivity(intent);
    }

    public void ponerListener(){
        butonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity(3);
            }
        });
        butonEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity(0);
            }
        });
        butonProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity(1);
            }
        });
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity(2);
            }
        });
    }

    private void getLugar(String id) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
        final ProgressDialog dialog = ProgressDialog.show(Lugar.this, "Cargando Lugar", null, true, true);
        query.getInBackground(id,new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {

                 dialog.dismiss();
                 txtName.setText((String) parseObject.get("Name"));
                 txtCategoria.setText((String) parseObject.get("Categoria"));
                 txtLocation.setText((String) parseObject.get("Direccion"));
                 point = parseObject.getParseGeoPoint("Location");
            }
        });
    }

}
