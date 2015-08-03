package com.example.anderssonvilla.barsocial2.clases;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.io.Serializable;


public class PlaceParse extends ParseObject implements Serializable {

    private static final long serialVersionUID = 1l;

    String name, categoria, direccion;
    ParseGeoPoint location;
    ParseRelation<ParseObject> eventos, Info, producto;

    public PlaceParse(){

    }

    public PlaceParse(Parcel in){

    }



    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ParseGeoPoint getLocation() {
        return location;
    }

    public void setLocation(ParseGeoPoint location) {
        this.location = location;
    }

    public ParseRelation<ParseObject> getEventos() {
        return eventos;
    }

    public void setEventos(ParseRelation<ParseObject> eventos) {
        this.eventos = eventos;
    }

    public ParseRelation<ParseObject> getInfo() {
        return Info;
    }

    public void setInfo(ParseRelation<ParseObject> info) {
        Info = info;
    }

    public ParseRelation<ParseObject> getProducto() {
        return producto;
    }

    public void setProducto(ParseRelation<ParseObject> producto) {
        this.producto = producto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   // @Override
    public int describeContents() {
        return 0;
    }

   // @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static final Parcelable.Creator<PlaceParse> CREATOR
            = new Parcelable.Creator<PlaceParse>()
    {
        public PlaceParse createFromParcel(Parcel in)
        {

            return new PlaceParse(in);
        }

        public PlaceParse[] newArray (int size)
        {
            return new PlaceParse[size];
        }
    };

}
