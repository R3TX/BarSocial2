package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.anderssonvilla.barsocial2.adapter.productosAdapter;
import com.google.android.gms.analytics.ecommerce.Product;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Producto extends ActionBarActivity {
String value;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        Bundle b = getIntent().getExtras();
        value=b.getString("idLugar");
        listview=(ListView)findViewById(R.id.listViewProduct);
        getProductos();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_producto, menu);
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

    private void getProductos() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
        final ProgressDialog dialog = ProgressDialog.show(Producto.this, "Cargando Productos", null, true, true);
        query.getInBackground(value,new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {

                dialog.dismiss();
                ParseQuery<ParseObject> moreInfo = parseObject.getRelation("idProducto").getQuery();
                moreInfo.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects2, ParseException e) {
                        System.out.println("\n\n\n"+parseObjects2.size());
                        productosAdapter ap = new productosAdapter(parseObjects2,Producto.this);
                        listview.setAdapter(ap);
                    }
                });

            }
        });
    }
}
