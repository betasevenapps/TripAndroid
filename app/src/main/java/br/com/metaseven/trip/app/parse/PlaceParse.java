package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import org.w3c.dom.Comment;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("Place")
public class PlaceParse extends ParseObject {

    public static final String TAG = "tag";
    public static final String TITLE = "title";
    public static final String LAT_LNG = "latLng";
    public static final String ADDRESS = "address";
    public static final String PICTURE = "picture";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";

    public String getTag(){
        return getString(TAG);
    }
    public String getTitle(){
        return getString(TITLE);
    }
    public ParseGeoPoint getLatLng(){
        return getParseGeoPoint(LAT_LNG);
    }
    public String getAddress(){
        return getString(ADDRESS);
    }
    public ParseFile getPicture(){
        return getParseFile(PICTURE);
    }
    public Double getPrice(){
        return getDouble(PRICE);
    }
    public String getDescription(){
        return getString(DESCRIPTION);
    }
    public void setTag(String tag){
        put(TAG, tag);
    }
    public void setTitle(String title){
        put(TITLE, title);
    }
    public void setLatLng(String latLng){
        put(LAT_LNG, latLng);
    }
    public void setAddress(String address){
        put(ADDRESS, address);
    }
    public void setPicture(ParseFile picture){
        put(PICTURE, picture);
    }
    public void setPrice(Double price){
        put(PRICE, price);
    }
    public void setDescription(String description){
        put(DESCRIPTION, description);
    }
}
