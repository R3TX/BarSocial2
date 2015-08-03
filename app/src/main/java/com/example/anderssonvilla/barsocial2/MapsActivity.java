package com.example.anderssonvilla.barsocial2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;

import com.example.anderssonvilla.barsocial2.adapter.LugaresAdapter;
import com.example.anderssonvilla.barsocial2.clases.LugarParse;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private double lon, lat;
    private String name;
    List<LugarParse> lugarMaps;
    String value1;
    boolean varios;
    Bundle extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        extra = getIntent().getExtras();

        varios = extra.getBoolean("varios");
        if(!varios) {

            lon = extra.getDouble("longitud");
            lat = extra.getDouble("latitud");
            name = extra.getString("Name");
            setUpMapIfNeeded();
        }else {
            value1 = extra.getString("categoria");
            try {
                getListaLugaresPorCategoria(value1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        varios = extra.getBoolean("varios");
        if(!varios) {

            lon = extra.getDouble("longitud");
            lat = extra.getDouble("latitud");
            name = extra.getString("Name");
            setUpMapIfNeeded();
        }else {
            value1 = extra.getString("categoria");
            try {
                getListaLugaresPorCategoria(value1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //setUpMapIfNeeded();
        //onMapReady(mMap);
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
               //setUpMap();
               //onMapReady(mMap);
                addmarkers();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        //mMap.addMarker(new MarkerOptions()
       //         .position(new LatLng(lat, lon))
       //         .title(name));
    }

    public void addmarkers(){
        for(int i = 0;i<lugarMaps.size();i++){
            ParseGeoPoint p = lugarMaps.get(i).getParseGeoPoint("Location");
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(p.getLatitude(), p.getLongitude()))
                            .title(name));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(p.getLatitude(),p.getLongitude()),10.0f));

        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent produc = new Intent(MapsActivity.this ,Lugar.class);
                Bundle b = new Bundle();

                produc.putExtra("LugarParse", (java.io.Serializable) lugarMaps.get(Integer.valueOf(marker.getId())));
                return true;
            }

        });
    }

    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title(name));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon),10.0f));
    }


    private void getListaLugaresPorCategoria(String categoria) throws ParseException {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lugar");
        query.whereEqualTo("Categoria", categoria);
        final ProgressDialog dialog = ProgressDialog.show(MapsActivity.this, "Buscando "+ categoria, null, true, true);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> lugares, ParseException e) {
                if(e==null){
                    dialog.dismiss();
                   // lugarMaps = lugares;
                    for (int i = 0; i<lugares.size();i++) {
                        LugarParse p = new LugarParse();
                        ParseObject o = lugares.get(i);
                        p.setCategoria(o.getString("Categoria"));
                        p.setDireccion(o.getString("Direccion"));
                        p.setEventos(o.getRelation("idEvento"));
                        p.setInfo(o.getRelation("idInfo"));
                        p.setLocation(o.getParseGeoPoint("Location"));
                        p.setName(o.get("Name").toString());
                        p.setProducto(o.getRelation("idProducto"));
                        lugarMaps.add(p);


                    }




                    setUpMapIfNeeded();
                }else{
                    System.out.println("no di " + e.getMessage());
                }
            }
        });
    }
}
